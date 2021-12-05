package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.dao.UserDAO;
import web.model.User;
import web.service.CarService;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserDAO userDAO;
    private final CarService carService;

    public UserController(UserDAO userDAO, CarService carService) {
        this.userDAO = userDAO;
        this.carService = carService;
    }

    @GetMapping()
    public String getAllUser(Model model) {
        List<User> list = new ArrayList<>();
        list = userDAO.resUsers();
        model.addAttribute("users", list);
        return "/users";
    }

    public String addUser() {
        return null;
    }

}
