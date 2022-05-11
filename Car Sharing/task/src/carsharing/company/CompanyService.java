package carsharing.company;


import java.util.List;

public class CompanyService {

    private static CompanyDaoImpl companyDao = new CompanyDaoImpl();

    public static Company getCompanyByName(String name){
       return companyDao.getCompanyByName(name);
    }
    public static Company getCompanyById(Long id){
        return companyDao.getCompanyByID(id);
    }
    public static void showAllCompanies() {
        List<Company> allCompanies = companyDao.getAllCompanies();
        if (allCompanies.isEmpty()) {
            System.out.println("The company list is empty!");
        } else {
            System.out.println("Company list:");
            int counter = 1;
            for(Company company:allCompanies){
                System.out.println(counter+". "+company.getName());
                counter++;
            }
        }
    }

    public static List<Company> getListOfCompanies() {
        return companyDao.getAllCompanies();
    }

    public static Company createCompany(String name) {
        Company company = companyDao.createCompany(name);
        if (company != null) {
            System.out.println("The company was created!");
        }
        return company;
    }
}


