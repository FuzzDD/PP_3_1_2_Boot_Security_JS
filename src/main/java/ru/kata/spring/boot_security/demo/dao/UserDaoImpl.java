package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getUsersList() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }
    @Override
    public User show(Long id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public User findByName(String email) {
        User user = null;
        try {
            user= (User)entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                    .setParameter("email", email).getSingleResult();
        } catch (NoResultException e) {
        }
        return user;
    }
    @Override
    public void add(User user) {
        //entityManager.persist(user);
        entityManager.merge(user);
    }

    @Override
    public void update(Long id, User updatetUser) {
        entityManager.merge(updatetUser);
    }
    @Override
    public void delete(Long id) {
        entityManager.remove(show(id));
    }
    @Override
    public User getUserByLogin(String username) {
        TypedQuery<User> q = (entityManager.createQuery("select u from User u " +
                "join fetch u.roles where u.firstName = :username",User.class));
        q.setParameter("username",username);
        return q.getResultList().stream().findFirst().orElse(null);
    }
}
