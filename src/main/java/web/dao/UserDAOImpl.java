package web.dao;

import org.springframework.stereotype.Component;
import web.model.User;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    @Override
    public List<User> resUsers() {

        List<User> users = new ArrayList<>();

        users.add(new User(1,"Mike", "Harry", 18));
        users.add(new User(2,"Jarry", "Be", 19));
        users.add(new User(3,"Lusy", "Abram", 20));
        users.add(new User(4,"Tom", "Jackson", 21));
        users.add(new User(5,"Bob", "Marly", 22));
        return users;
    }

    public List<User> index() {
        return resUsers();
    }
    // Метод поиска по id
    public User show(int id) {
        return resUsers().stream().filter(user -> user.getId() ==id).findAny().orElse(null);
    }


}
