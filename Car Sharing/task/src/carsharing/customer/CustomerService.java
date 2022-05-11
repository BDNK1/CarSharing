package carsharing.customer;

import carsharing.car.Car;
import carsharing.company.Company;

import java.util.List;

public class CustomerService {

    private static CustomerDao customerDao = new CustomerDaoImpl();

    public static void showAllCustomers() {
        List<Customer> allCustomers = customerDao.getAllCustomers();
        if (allCustomers.isEmpty()) {
            System.out.println("The customer list is empty!");
        } else {
            System.out.println("Customer list:");
            int counter = 1;
            for(Customer customer:allCustomers){
                System.out.println(counter+". "+customer.getName());
                counter++;
            }
        }
    }


    public static Customer getCustomerByName(String name){
        return customerDao.getCustomerByName(name);
    }

    public static List<Customer> getListOfCustomers() {
        return customerDao.getAllCustomers();
    }

    public static Customer createCustomer(String name,Car car) {
        Customer customer =  customerDao.createCustomer(name,car);
        if (customer != null) {
            System.out.println("The customer was created!");
        }
        return customer;
    }

    public static void updateCustomer(Customer customer,String newName,Car newCar){
        customerDao.updateCustomer(customer,newName,newCar);

    }

    public static Car getRentedCar(String customerName){
        Customer customer = customerDao.getCustomerByName(customerName);
        return customer.getRentedCar();
    }
}
