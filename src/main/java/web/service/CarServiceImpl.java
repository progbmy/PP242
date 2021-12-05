package web.service;

import org.springframework.stereotype.Component;
import web.dao.UserDAO;
import web.model.User;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarServiceImpl implements CarService {
    private final UserDAO userDAO;

    public CarServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> usersCount(List<User> cars) {
        return userDAO.resUsers();
    }
}
