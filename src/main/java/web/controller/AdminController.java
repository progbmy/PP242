package web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;
import web.model.User;

@Controller
@RequestMapping("/admin/users")
public class AdminController {
    private final UserDAO userDAO;

    public AdminController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String getAllUser(Model model) {
        model.addAttribute("users", userDAO.resUsers());
        return "/users";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("users", userDAO.showUser(id));
        return "show";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userDAO.createUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("users", userDAO.showUser(id));
        return ("edit");
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("users") User user, @PathVariable("id") int id) {
        userDAO.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/users";
    }

}
