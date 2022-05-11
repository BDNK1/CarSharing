package carsharing.company;


public class Company {
    private Long ID;
    private String name;


    public Company(Long ID, String name) {
        this.ID = ID;
        this.name = name;
    }
    public static Company getCompanyByName(String name){
        return CompanyService.getCompanyByName(name);
    }
    public static Company getCompanyById(Long id){
        return CompanyService.getCompanyById(id);
    }
    public static Company createCompany(String name){
        return  CompanyService.createCompany(name);
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
