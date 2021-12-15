package web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.dao.UserDAO;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserDAO userDAO;

    public UserController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String showUser(Model model) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        model.addAttribute("user", userDAO.findByUsername(name));
        return "/user";
    }
}
