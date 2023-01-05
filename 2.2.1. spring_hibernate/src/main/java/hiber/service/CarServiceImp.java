package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import hiber.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarServiceImp implements CarService {


    private CarDao carDao;

    @Autowired
    public CarServiceImp(CarDao carDao) {
        this.carDao = carDao;
    }

    @Override
    @Transactional
    public void add(Car car) {
        carDao.add(car);
    }

    @Override
    @Transactional(readOnly = true)
    public User selectUser(String model, int series) {
        return carDao.selectUser(model, series);
    }
}
