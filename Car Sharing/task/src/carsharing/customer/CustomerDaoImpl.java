package carsharing.customer;

import carsharing.ConnectionFactory;
import carsharing.car.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    public static void initCustomerEntity() {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String customerTableCreationQuery =
                    "CREATE TABLE IF NOT EXISTS CUSTOMER " +
                    "            (ID INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                    "             NAME VARCHAR_IGNORECASE UNIQUE NOT NULL,  " +
                    "             RENTED_CAR_ID INTEGER , " +
                    "FOREIGN KEY (RENTED_CAR_ID) REFERENCES CAR(ID)" +
                    ");";

            Statement stmt = conn.createStatement();
            stmt.execute(customerTableCreationQuery);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Customer createCustomer(String name, Car rentedCar) {
        Customer customer;
        try (Connection conn = ConnectionFactory.getConnection()) {

            String createCustomerQueryWithCar = "INSERT INTO CUSTOMER (NAME,RENTED_CAR_ID) VALUES (?,?)";
            PreparedStatement stmt;
            stmt = conn.prepareStatement(createCustomerQueryWithCar, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, name);
        
            if (rentedCar != null) {
                stmt.setLong(2, rentedCar.getID());
            } else {
                stmt.setNull(2,Types.INTEGER);
            }
            int affected = stmt.executeUpdate();
            if (affected == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return new Customer(generatedKeys.getLong(1), name, rentedCar);
                } else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Customer getCustomerByName(String name) {
        Customer customer = null;
        try (Connection conn = ConnectionFactory.getConnection()) {

            String carTableCreationStatement = "SELECT * FROM CUSTOMER WHERE NAME = (?);";

            Statement stmt = conn.prepareStatement(carTableCreationStatement);
            stmt.execute(carTableCreationStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }


    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> allCustomers = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection()) {
            String getAllCarsFromCompanyStatement = "SELECT * FROM CUSTOMER;";
            PreparedStatement stmt = conn.prepareStatement(getAllCarsFromCompanyStatement);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                allCustomers.add(new Customer(rs.getLong("ID"), rs.getString("NAME"), Car.getCarByID(rs.getLong("RENTED_CAR_ID"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allCustomers;
    }

    @Override
    public void updateCustomer(Customer customer, String newName, Car newCar) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String updateCustomerQuery = "UPDATE CUSTOMER SET NAME=(?),RENTED_CAR_ID=(?) WHERE NAME =?";

            PreparedStatement stmt = conn.prepareStatement(updateCustomerQuery);
            stmt.setString(1, newName);
            if(newCar ==null){
                stmt.setNull(2,Types.INTEGER);
            }else {
                stmt.setLong(2, newCar.getID());
            }
            stmt.setString(3,customer.getName());
            int af=  stmt.executeUpdate();

            if(af==0){
                System.out.println("cant update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomerByName(String customerName) {
        try (Connection conn = ConnectionFactory.getConnection()) {

            String companyTableCreationStatement = "DELETE FROM COMPANY WHERE NAME =?";

            Statement stmt = conn.prepareStatement(companyTableCreationStatement);
            ResultSet rs = stmt.executeQuery(companyTableCreationStatement);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
