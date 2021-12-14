package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.dao.UserDAO;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

	private final UserDAO userDAO;

	public HomeController(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Hello!");
		messages.add("This app MVC Security + database 2.4.2");
		messages.add("2021");
		model.addAttribute("messages", messages);
		return "index";
	}
	@GetMapping("/admin")
	public String adminPanel(Model model) {
		model.addAttribute("users", userDAO.resUsers());
		return "admin";
	}

}