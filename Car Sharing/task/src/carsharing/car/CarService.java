package carsharing.car;

import carsharing.company.Company;

import java.util.List;

public class CarService {
    private static CarDaoImpl carDao = new CarDaoImpl();

    public static Car getCarByID(Long id){
        return carDao.getCarById(id);
    }
    public static void showAllCars(Company company) {
        List<Car> allCars = carDao.getAllCars(company);
        if (allCars.isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            System.out.println("Car list:");
            int counter = 1;
            for(Car car:allCars){
                System.out.println(counter+". "+car.getName());
                counter++;
            }
        }
    }

    public static void showAllAvailableCars(Company company) {
        List<Car> allCars = carDao.getAvailableCars(company);
        if (allCars.isEmpty()) {
            System.out.println("The car list is empty!");
        } else {
            System.out.println("Car list:");
            int counter = 1;
            for(Car car:allCars){
                System.out.println(counter+". "+car.getName());
                counter++;
            }
        }
    }
    public static List<Car> getListOfCars(Company company) {

        return  carDao.getAllCars(company);
    }

    public static Car createCar(String name, Company company) {
        Car car = carDao.createCar(name,company);
        if (car != null) {
            System.out.println("The car was created!");
        }
        return car;
    }

}
