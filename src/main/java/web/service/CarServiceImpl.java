package web.service;

import org.springframework.stereotype.Component;
import web.dao.CarDAO;
import web.model.Car;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarServiceImpl implements CarService {
    private final CarDAO carDAO;

    public CarServiceImpl(CarDAO carDAO) {
        this.carDAO = carDAO;
    }

    @Override
    public List<Car> carsCount(List<Car> cars, int count) {
        return carDAO.resCars().stream().limit(count).collect(Collectors.toList());
    }
}
