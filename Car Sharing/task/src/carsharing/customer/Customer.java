package carsharing.customer;

import carsharing.car.Car;

public class Customer {
    private Long ID;
    private String name;
    private Car rentedCar;

    public Customer(Long id, String name, Car rentedCar) {
        this.ID = id;
        this.name = name;
        this.rentedCar = rentedCar;
    }

    public static Customer createCustomer(String name, Car rentedCar) {
        return CustomerService.createCustomer(name, rentedCar);
    }


    public void rentCar(Car newCar) {
        CustomerService.updateCustomer(this, name, newCar);
        this.rentedCar = newCar;
        System.out.println("You rented " + "'" + newCar.getName() + "'");

    }

    public void returnCar() {
        CustomerService.updateCustomer(this, name, null);
        this.rentedCar = null;
        System.out.println("You've returned a rented car!");
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Car getRentedCar() {
        return rentedCar;
    }


}
