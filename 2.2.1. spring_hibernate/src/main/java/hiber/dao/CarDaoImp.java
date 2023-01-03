package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CarDaoImp implements CarDao {

    public SessionFactory sessionFactory;

    @Autowired
    public CarDaoImp(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public User selectUser(String model, int series) {
        String HQL = "FROM User WHERE id = (SELECT id FROM Car WHERE model = :model AND series = :series)";
        Query q = sessionFactory.getCurrentSession().createQuery(HQL);
        q.setParameter("model", model);
        q.setParameter("series", series);
        return (User) q.getSingleResult();
    }
}
