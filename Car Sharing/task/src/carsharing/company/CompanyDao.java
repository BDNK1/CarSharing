package carsharing.company;

import java.util.List;

public interface CompanyDao {
    Company createCompany(String company);
    List<Company> getAllCompanies();
    Company getCompanyByName(String name);
    Company getCompanyByID(Long id);
    void updateCompany(Company company,String newName);
    void deleteCompanyByName(String name);
}
