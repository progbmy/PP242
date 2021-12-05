package web.dao;

import org.springframework.stereotype.Component;
import web.model.Car;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarDAOImpl implements CarDAO{

    @Override
    public List<Car> resCars() {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car("BMW", "X6", 2021));
        cars.add(new Car("OPEL", "ZAFIRA", 2020));
        cars.add(new Car("AUDI", "A8", 2019));
        cars.add(new Car("MERCEDES", "ML200", 2018));
        cars.add(new Car("MITSUBISHI", "LANCER10", 2017));
        return cars;
    }
}
