package carsharing;


import carsharing.car.CarDaoImpl;
import carsharing.company.CompanyDaoImpl;
import carsharing.customer.CustomerDaoImpl;
import carsharing.menu.Menu;

public class Main {


    public static void main(String[] args) {
        if (args.length > 0 && args[0].equals("-databaseFileName")) {
            ConnectionFactory.setDbName(args[1]);
        }
        CompanyDaoImpl.initCompanyEntity();
        CarDaoImpl.initCarEntity();
        CustomerDaoImpl.initCustomerEntity();
        Menu.startMenu();

    }
}