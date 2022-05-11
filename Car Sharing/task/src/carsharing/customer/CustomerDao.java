package carsharing.customer;

import carsharing.car.Car;

import java.util.List;

public interface CustomerDao {
    Customer createCustomer(String name, Car rentedCar);
    Customer getCustomerByName(String name);
    List<Customer> getAllCustomers();
    void updateCustomer(Customer customer,String newName,Car newCar);
    void deleteCustomerByName(String customerName);
}
