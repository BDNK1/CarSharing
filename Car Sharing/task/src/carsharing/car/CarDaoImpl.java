package carsharing.car;

import carsharing.ConnectionFactory;
import carsharing.company.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CarDaoImpl implements CarDao{

    public static void initCarEntity(){
        try (Connection conn = ConnectionFactory.getConnection()) {
            String companyTableCreationStatement =
                    "CREATE TABLE IF NOT EXISTS CAR " +
                    "            (ID INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                    "             NAME VARCHAR_IGNORECASE UNIQUE NOT NULL,  "+
                    "             COMPANY_ID INTEGER NOT NULL, "+
                    "FOREIGN KEY (COMPANY_ID) REFERENCES COMPANY(ID)"+
                    ");";

            Statement stmt = conn.createStatement();
            stmt.execute(companyTableCreationStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Car createCar(String name,Company company){
        Car car;
        try (Connection conn = ConnectionFactory.getConnection()) {

            String createCarCreationQuery = "INSERT INTO CAR (NAME,COMPANY_ID) VALUES (?,?)";

            PreparedStatement stmt = conn.prepareStatement(createCarCreationQuery,Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1,name);
            stmt.setLong(2,company.getID());

            int affected =stmt.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Creating car failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Car(generatedKeys.getLong(1),name,company);
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
    public Car getCarById(Long id ) {
        Car car = null;
        try (Connection conn = ConnectionFactory.getConnection()) {

            String getCarByIdStatement ="SELECT * FROM CAR WHERE ID = (?); ";

            PreparedStatement stmt = conn.prepareStatement(getCarByIdStatement);
            stmt.setLong(1,id);
            ResultSet rs = stmt.executeQuery();
            return new Car(rs.getLong("ID"),rs.getString("NAME"),Company.getCompanyById(rs.getLong("COMPANY_ID")));
        } catch (SQLException e) {
            return null;
        }
    }




    @Override
    public List<Car> getAllCars(Company company) {
        List<Car> allCars = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection()) {

            String getAllCarsFromCompanyStatement ="SELECT * FROM CAR WHERE COMPANY_ID = (SELECT ID FROM COMPANY WHERE NAME = (?))";

            PreparedStatement stmt = conn.prepareStatement(getAllCarsFromCompanyStatement);
            stmt.setString(1,company.getName());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                allCars.add(new Car(rs.getLong("ID"),rs.getString("NAME"),company));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCars;
    }

    @Override
    public List<Car> getAvailableCars(Company company) {
        List<Car> allAvailableCars = getAllCars(company);
        try (Connection conn = ConnectionFactory.getConnection()) {

            String getAllRentedCarsQuery ="SELECT CAR.ID,CAR.NAME FROM CAR " +
                    "                    JOIN CUSTOMER AS CUST" +
                    "                    ON (CUST.RENTED_CAR_ID = CAR.ID) " +
                    "                    WHERE COMPANY_ID = (?)";

            PreparedStatement stmt = conn.prepareStatement(getAllRentedCarsQuery);
            stmt.setLong(1,company.getID());
            ResultSet rs = stmt.executeQuery();
            List<Long> allRentedCarsIds = new ArrayList<>();
            while (rs.next()) {
                allRentedCarsIds.add(rs.getLong("ID"));
            }
            allAvailableCars=allAvailableCars.stream().filter(c->!allRentedCarsIds.contains(c.getID())).collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allAvailableCars;
    }

    @Override
    public void updateCar(Car car) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String companyTableCreationStatement = "UPDATE CAR SET NAME=? WHERE NAME =?";

            Statement stmt = conn.prepareStatement(companyTableCreationStatement);
            ResultSet rs = stmt.executeQuery(companyTableCreationStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCarByName(String name) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String companyTableCreationStatement = "DELETE FROM COMPANY WHERE NAME =?";

            Statement stmt = conn.prepareStatement(companyTableCreationStatement);
            ResultSet rs = stmt.executeQuery(companyTableCreationStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
