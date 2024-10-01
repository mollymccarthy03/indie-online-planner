package onlineplanner;

import onlineplanner.Task;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

import java.util.List;

public class TaskDAO {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

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
     * sample usage: getByPropertyEqual("lastname", "Curry")
     */
    public List<Task> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for Task with " + propertyName + " = " + value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Task> query = builder.createQuery(Task.class);
        Root<Task> root = query.from(Task.class);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Task> tasks = session.createSelectionQuery( query ).getResultList();

        session.close();
        return tasks;
    }

    /**
     * Get Task by property (like)
     * sample usage: getByPropertyLike("lastname", "C")
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

}