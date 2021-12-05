package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.dao.CarDAO;
import web.model.Car;
import web.service.CarService;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarDAO carDAO;
    private final CarService carService;

    public CarController(CarDAO carDAO, CarService carService) {
        this.carDAO = carDAO;
        this.carService = carService;
    }

    @GetMapping()
    public String getCar(@RequestParam(value = "count", required = false, defaultValue = "5") int count, Model model) {
        System.out.println("Введен параметр: " + count);

        List<Car> list = new ArrayList<>();
        list = carService.carsCount(carDAO.resCars(), count);
        model.addAttribute("cars", list);

        return "/cars";
    }
}
