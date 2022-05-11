package carsharing.company;

import carsharing.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDaoImpl implements CompanyDao{



    public static void initCompanyEntity(){
        try (Connection conn = ConnectionFactory.getConnection()) {

            String companyTableCreationStatement =
                    "CREATE TABLE IF NOT EXISTS COMPANY " +
                    "            (ID INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                    "             NAME VARCHAR_IGNORECASE UNIQUE NOT NULL  "+

                    ");";

            Statement stmt = conn.createStatement();
            stmt.execute(companyTableCreationStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Company createCompany(String company) {
        Company companyObj ;
        try (Connection conn = ConnectionFactory.getConnection()) {

            String createCompanyStatement = "INSERT INTO COMPANY (NAME) VALUES (?)";

            PreparedStatement stmt = conn.prepareStatement(createCompanyStatement,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,company);
            int affected =stmt.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Company(generatedKeys.getLong(1),company);
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Company getCompanyByName(String name) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String getCompanyByNameQuery= "SELECT * FROM COMPANY WHERE NAME = (?);";


            PreparedStatement stmt = conn.prepareStatement(getCompanyByNameQuery);
            stmt.setString(1,name);
            ResultSet rs = stmt.executeQuery();

            return new Company(rs.getLong("ID"),rs.getString("NAME"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Company getCompanyByID(Long id) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String getCompanyByNameQuery= "SELECT * FROM COMPANY WHERE ID = (?);";

            PreparedStatement stmt = conn.prepareStatement(getCompanyByNameQuery);
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();

            return new Company(rs.getLong("ID"),rs.getString("NAME"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public List<Company> getAllCompanies() {
        List<Company> allCompanies = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()) {

            String companyTableCreationStatement = "SELECT * FROM COMPANY";

            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery(companyTableCreationStatement);
            while (rs.next()) {
                allCompanies.add(new Company(rs.getLong("ID"),rs.getString("NAME")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCompanies;
    }

    @Override
    public void updateCompany(Company company,String newName) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String companyTableCreationStatement = "UPDATE COMPANY SET NAME=? WHERE NAME =?";

            Statement stmt = conn.prepareStatement(companyTableCreationStatement);
            ResultSet rs = stmt.executeQuery(companyTableCreationStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCompanyByName(String name) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String companyTableCreationStatement = "DELETE FROM COMPANY WHERE NAME =?";

            Statement stmt = conn.prepareStatement(companyTableCreationStatement);
            ResultSet rs = stmt.executeQuery(companyTableCreationStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
