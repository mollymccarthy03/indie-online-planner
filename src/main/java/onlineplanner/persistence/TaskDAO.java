package onlineplanner.persistence;

import jakarta.persistence.criteria.CriteriaBuilder;
import onlineplanner.entity.Task;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.time.LocalDate;
import java.util.List;

public class TaskDAO {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public TaskDAO(SessionFactory sessionFactory) {
    }

    /**
     * Get Task by id
     */
    public Task getById(int id) {
        Session session = sessionFactory.openSession();
        Task task = session.get(Task.class, id);
        session.close();
        return task;
    }
    /**
     * update Task
     * @param task  Task to be updated
     */
    public void update(Task task) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(task);
        transaction.commit();
        session.close();
    }
    /**
     * insert a new Task
     * @param task  Task to be inserted
     */
    public int insert(Task task) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(task);
        transaction.commit();
        id = task.getId();
        session.close();
        return id;
    }
    /**
     * Delete a Task
     * @param task task to be deleted
     */
    public void delete(Task task) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(task);
        transaction.commit();
        session.close();
    }
    /** Return a list of all Tasks
     *
     * @return All Tasks
     */
    public List<Task> getAll() {

        Session session = sessionFactory.openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> query = builder.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);
        List<Task> tasks = session.createSelectionQuery( query ).getResultList();

        logger.debug("The list of Tasks " + tasks);
        session.close();

        return tasks;
    }
    /**
     * Get Task by property (exact match)
     * sample usage: getByPropertyEqual("title", "Testing Java Application")
     * Future usage: get all tasks with to do date of wednesday
     */
    public List<Task> getByPropertyEqual(String propertyName, Object value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for Task with " + propertyName + " = " + value);

        // Get the CriteriaBuilder and CriteriaQuery
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> query = builder.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);

        // Handle property based on its type
        if (propertyName.equals("user")) {
            // Handle case for 'user' property (association)
            query.select(root).where(builder.equal(root.get("user"), value));
        } else {
            // Handle simple property match
            query.select(root).where(builder.equal(root.get(propertyName), value));
        }

        // Execute the query
        List<Task> tasks = session.createQuery(query).getResultList();

        session.close();
        return tasks;
    }
    /**
     * Get Task by property (like)
     * sample usage: getByPropertyLike("title", "Java")
     * Future usage: get all tasks with title including java keyword
     */
    public List<Task> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for Task with {} = {}",  propertyName, value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> query = builder.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Task> Tasks = session.createQuery( query ).getResultList();
        session.close();
        return Tasks;
    }
    /**
     * Get Tasks for a specific date property
     * @param dateProperty the date property to filter by (e.g., "todoDate" or "dueDate")
     * @param date the date to match
     * @return List of matching tasks
     */
    private List<Task> getTasksByDate(String dateProperty, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Task> query = builder.createQuery(Task.class);
            Root<Task> root = query.from(Task.class);

            query.select(root).where(builder.equal(root.get(dateProperty), date));

            return session.createQuery(query).getResultList();
        }
    }
    /**
     * Get Tasks for a specific todoDate
     * @param date the todoDate to match
     * @return List of matching tasks
     */
    public List<Task> getTasksForTodoDate(LocalDate date) {
        return getTasksByDate("todoDate", date);
    }

    /**
     * Get Tasks for a specific dueDate
     * @param date the dueDate to match
     * @return List of matching tasks
     */
    public List<Task> getTasksForDueDate(LocalDate date) {
        return getTasksByDate("dueDate", date);
    }
}