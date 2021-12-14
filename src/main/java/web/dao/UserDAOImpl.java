package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Override
    public List<User> resUsers() {
        return entityManager.createQuery("from User", User.class)
                .getResultList();
    }
    @Override
    public List<User> index() {
        return resUsers();
    }

    @Override
    public User showUser(int id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void update(int id, User updatedUser) {
        User user1 = entityManager.find(User.class, id);
        user1.setUserName(updatedUser.getUserName());
        user1.setLastName(updatedUser.getLastName());
        user1.setEmail(updatedUser.getEmail());
        user1.setAge(updatedUser.getAge());
        user1.setPassword(updatedUser.getPassword());
    }

    @Transactional
    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }


    @Override
    public User findByUsername(String username) {
        try {
            User user = entityManager.createQuery("SELECT u FROM User u where u.userName = :name", User.class)
                    .setParameter("name", username)
                    .getSingleResult();
            return user;
        } catch (NoResultException ex) {
            return null;
        }
    }


}
