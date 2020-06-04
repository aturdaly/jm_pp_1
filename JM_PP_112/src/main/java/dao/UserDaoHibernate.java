package dao;

import exception.DBException;
import model.User;
import org.hibernate.*;
import java.util.List;

public class UserDaoHibernate implements UserDao {
    private static UserDaoHibernate userDaoHibernate;
    private SessionFactory sessionFactory;

    private UserDaoHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    public static UserDaoHibernate getInstance(SessionFactory sessionFactory) {
        if (userDaoHibernate == null) {
            userDaoHibernate = new UserDaoHibernate(sessionFactory);
        }
        return userDaoHibernate;
    }

    @Override
    public List<User> getAllUser() {
        Session session = sessionFactory.openSession();
        List<User> user = session.createQuery("from User").list();
        session.close();
        return user;
    }

    @Override
    public boolean validateUser(String name, String password) {
        Session session = sessionFactory.openSession();
        String hql = "from User where name = :paramName AND password = :paramPassword";
        Query query = session.createQuery(hql);
        query.setParameter("paramName", name);
        query.setParameter("paramPassword", password);
        User user = (User) query.uniqueResult();
        session.close();
        return user!=null;
    }

    @Override
    public User getUserById(long id) {
        Session session = sessionFactory.openSession();
        String hql = "from User where id = :paramId";
        Query query = session.createQuery(hql);
        query.setParameter("paramId", id);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User getUserByName(String name) {
        Session session = sessionFactory.openSession();
        String hql = "from User where name = :paramName";
        Query query = session.createQuery(hql);
        query.setParameter("paramName", name);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public void addUser(User user) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public void deleteUser(User user) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }

    @Override
    public void updateUser(User user) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw new DBException(e);
        }
    }
}
