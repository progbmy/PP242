package web.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
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
        user1.setLastName(updatedUser.getLastName());
        user1.setFirstName(updatedUser.getFirstName());
        user1.setAge(updatedUser.getAge());
    }

    @Transactional
    @Override
    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}
