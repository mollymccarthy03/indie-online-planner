package onlineplanner.persistence;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import onlineplanner.entity.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.time.LocalDate;
import java.util.List;

public class GenericDAO<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public GenericDAO(Class<T> type) {
        this.type = type;
    }

    /**
     * Get an entity by ID
     * @param id
     * @return
     */
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }
    /**
     *
     * Update entity
     * @param entity entity to be updated
     */
    public void update(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Insert a new entity
     * @param entity entity to be inserted
     * @return generated id
     */
    public int insert(T entity) {
        int id = 0;
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        id = (Integer) session.getIdentifier(entity);
        session.close();
        return id;
    }

    /**
     * Delete an entity
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Return a list of all entities
     *
     * @return All entities
     */
    public List<T> getAll() {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;
    }

    /**
     * Get entities by property (exact match)
     * @param propertyName property name
     * @param value property value
     * @return list of matching entities
     */
    public List<T> getByPropertyEqual(String propertyName, Object value) {
        Session session = getSession();
        logger.debug("Searching for entities with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        query.select(root).where(builder.equal(root.get(propertyName), value));

        List<T> entities = session.createQuery(query).getResultList();
        session.close();
        return entities;
    }

    /**
     * Get entities by property (like match)
     * @param propertyName property name
     * @param value partial value to match
     * @return list of matching entities
     */
    public List<T> getByPropertyLike(String propertyName, String value) {
        Session session = getSession();
        logger.debug("Searching for entities with {} = {}", propertyName, value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        Expression<String> propertyPath = root.get(propertyName);
        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<T> entities = session.createQuery(query).getResultList();
        session.close();
        return entities;
    }

    private Session getSession(){
        return SessionFactoryProvider.getSessionFactory().openSession();
    }
    /**
     *
     * @param userId
     * @return list of tasks based on cetain userID
     */
    public List<Task> getTasksByUserId(int userId) {
        try (Session session = getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Task> query = builder.createQuery(Task.class);
            Root<Task> root = query.from(Task.class);

            // Create the query to filter tasks by user ID
            query.select(root).where(builder.equal(root.get("user").get("id"), userId));

            return session.createQuery(query).getResultList();
        }
    }
    /**
     * Get Tasks for a specific date property
     * @param dateProperty the date property to filter by (e.g., "todoDate" or "dueDate")
     * @param date the date to match
     * @return List of matching tasks
     */
    private List<T> getTasksByDate(String dateProperty, LocalDate date) {
        try (Session session = getSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(type);
            Root<T> root = query.from(type);

            query.select(root).where(builder.equal(root.get(dateProperty), date));

            return session.createQuery(query).getResultList();
        }
    }
    /**
     * Get Tasks for a specific todoDate
     * @param date the todoDate to match
     * @return List of matching tasks
     */
    public List<T> getTasksForTodoDate(LocalDate date) {
        return getTasksByDate("todoDate", date);
    }

    /**
     * Get Tasks for a specific dueDate
     * @param date the dueDate to match
     * @return List of matching tasks
     */
    public List<T> getTasksForDueDate(LocalDate date) {
        return getTasksByDate("dueDate", date);
    }
}