package web.service;

import web.model.User;

import java.util.List;

public interface CarService {
    List<User> usersCount(List<User> cars);
}
