package onlineplanner.persistence;

import onlineplanner.entity.Week;
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

public class WeekDAO {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Get Week by id
     */
    public Week getById(int id) {
        Session session = sessionFactory.openSession();
        Week week = session.get(Week.class, id);
        session.close();
        return week;
    }

    /**
     * Update Week
     * @param week Week to be updated
     */
    public void update(Week week) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(week);
        transaction.commit();
        session.close();
    }

    /**
     * Insert a new Week
     * @param week Week to be inserted
     */
    public int insert(Week week) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(week);
        transaction.commit();
        id = week.getId();  // Get the generated id after the insert
        session.close();
        return id;
    }

    /**
     * Delete a Week
     * @param week Week to be deleted
     */
    public void delete(Week week) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(week);
        transaction.commit();
        session.close();
    }

    /**
     * Return a list of all Weeks
     * @return All Weeks
     */
    public List<Week> getAll() {
        Session session = sessionFactory.openSession();

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Week> query = builder.createQuery(Week.class);
        Root<Week> root = query.from(Week.class);
        List<Week> weeks = session.createSelectionQuery(query).getResultList();

        logger.debug("The list of Weeks: " + weeks);
        session.close();

        return weeks;
    }

    /**
     * Get Week by property (exact match)
     * Example: getByPropertyEqual("mon", "2024-10-07")
     */
    public List<Week> getByPropertyEqual(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for Week with " + propertyName + " = " + value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Week> query = builder.createQuery(Week.class);
        Root<Week> root = query.from(Week.class);
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Week> weeks = session.createSelectionQuery(query).getResultList();

        session.close();
        return weeks;
    }

    /**
     * Get Week by property (like)
     * Example: getByPropertyLike("mon", "2024-10")
     */
    public List<Week> getByPropertyLike(String propertyName, String value) {
        Session session = sessionFactory.openSession();

        logger.debug("Searching for Week with {} like {}", propertyName, value);

        HibernateCriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Week> query = builder.createQuery(Week.class);
        Root<Week> root = query.from(Week.class);
        Expression<String> propertyPath = root.get(propertyName);

        query.where(builder.like(propertyPath, "%" + value + "%"));

        List<Week> weeks = session.createQuery(query).getResultList();
        session.close();
        return weeks;
    }
}
