package carsharing.menu;

import carsharing.car.Car;
import carsharing.company.Company;
import carsharing.customer.Customer;

import java.util.List;

import static carsharing.car.CarService.*;
import static carsharing.company.CompanyService.getListOfCompanies;
import static carsharing.company.CompanyService.showAllCompanies;
import static carsharing.customer.CustomerService.*;
import static carsharing.menu.Menu.scanner;

public class CustomerMenu {


    public static void loginAsCustomer() {
        showAllCustomers();
        System.out.println("0. Back");
        if (!getListOfCustomers().isEmpty()) {
            int customerNumber = scanner.nextInt();
            if (customerNumber == 0) {
                return;
            }
            List<Customer> companies = getListOfCustomers();
            customerMenu(companies.get(--customerNumber));
        }
    }


    public static void customerMenu(Customer customer) {
        int input = -1;
        while (input != 0) {
            System.out.println(
                    "1. Rent a car\n" +
                            "2. Return a rented car\n" +
                            "3. My rented car\n" +
                            "0. Back");

            input = scanner.nextInt();
            switch (input) {
                case 1:
                    if (customer.getRentedCar() != null) {
                        System.out.println("You've already rented a car!");
                        break;
                    }
                    Car car = chooseCar(chooseCompany());
                    if (car != null) {
                        customer.rentCar(car);
                    }
                    break;
                case 2:
                    if (customer.getRentedCar() == null) {
                        System.out.println("You didn't rent a car!");
                        break;

                    }
                    customer.returnCar();
                    break;

                case 3:
                    Car rentedCar = customer.getRentedCar();
                    System.out.println(rentedCar != null ?
                            "Your rented car:\n" + rentedCar.getName() + "\nCompany:\n" + rentedCar.getCompany().getName()
                            : "You didn't rent a car!");
                case 0:
                    break;

            }
        }
    }

    private static Company chooseCompany() {
        System.out.println("Choose the company");
        showAllCompanies();
        System.out.println("0. Back");
        if (!getListOfCompanies().isEmpty()) {
            int companyNumber = scanner.nextInt();
            if (companyNumber == 0) {
                return null;
            }
            List<Company> companies = getListOfCompanies();
            return companies.get(--companyNumber);
        }
        return null;
    }

    public static Car chooseCar(Company company) {
        System.out.println("Choose a car");
        showAllAvailableCars(company);
        System.out.println("0. Back");
        if (!getListOfCars(company).isEmpty()) {
            int carNumber = scanner.nextInt();
            if (carNumber == 0) {
                return null;
            }
            List<Car> cars = getListOfCars(company);
            return cars.get(--carNumber);
        }
        return null;
    }


}
