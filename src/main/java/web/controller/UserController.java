package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    //
    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", userDAO.show(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("person") User user) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userDAO.save(user);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", userDAO.show(id));
        return ("edit");
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") User user, @PathVariable("id") int id) {
        userDAO.update(id, user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/users";
    }

}
