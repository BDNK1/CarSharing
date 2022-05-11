package carsharing.menu;

import carsharing.company.Company;

import java.util.List;

import static carsharing.car.CarService.createCar;
import static carsharing.car.CarService.showAllCars;
import static carsharing.company.CompanyService.*;
import static carsharing.company.CompanyService.getListOfCompanies;
import static carsharing.menu.Menu.scanner;

public class ManagerMenu {

    private static Company company;

    public static void loginAsManager() {
        int input = -1;
        while (input != 0) {
            System.out.println("\n1. Company list\n" +
                    "2. Create a company\n" +
                    "0. Back");

            input = scanner.nextInt();
            switch (input) {
                case 1:
                    company = chooseCompany();
                    if (company != null) {
                        companyManagerMenu();
                    }
                    break;
                case 2:
                    System.out.println("\nEnter the company name");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    createCompany(name);
                    break;
                case 0:
                    break;

            }
        }
    }

    public static Company chooseCompany() {
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

    public static void companyManagerMenu() {
        System.out.println("'" + company.getName() + "'" + " company");
        int input = -1;
        while (input != 0) {
            System.out.println("\n1. Car list\n" +
                    "2. Create a car\n" +
                    "0. Back");

            input = scanner.nextInt();
            switch (input) {
                case 1:
                    showAllCars(company);
                    break;
                case 2:
                    System.out.println("\nEnter the car name:");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    createCar(name, company);
                    break;
                case 0:
                    break;

            }
        }
    }
}
