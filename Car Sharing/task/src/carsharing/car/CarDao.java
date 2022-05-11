package carsharing.car;

import carsharing.company.Company;

import java.util.List;

interface CarDao {
    Car createCar(String name, Company company);
    List<Car> getAllCars(Company company);
    List<Car> getAvailableCars(Company company);
    Car getCarById(Long id);
    void updateCar(Car car);
    void deleteCarByName(String name);
}
