package carsharing.car;

import carsharing.company.Company;

public class Car {
    private Long ID;
    private String name;
    private Company company;

    public Car(Long ID, String name,Company company) {
        this.ID = ID;
        this.name = name;
        this.company = company;
    }

    public static Car createCar(String name, Company company) {
        return CarService.createCar(name,company);
    }

    public static Car getCarByID(Long id){
        return CarService.getCarByID(id);
    }
    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public Company getCompany() {
        return company;
    }
}
