package carsharing.DAO;

import carsharing.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO{

    private final Statement stmt;

    public CustomerDAOImpl(Statement stmt) {
        this.stmt = stmt;
    }

    @Override
    public boolean createTable() {
        try {
            String sql =
                    "CREATE TABLE CUSTOMER (" +
                            "ID INT PRIMARY KEY AUTO_INCREMENT," +
                            "NAME VARCHAR(20) UNIQUE NOT NULL," +
                            "RENTED_CAR_ID INT DEFAULT NULL," +
                            "CONSTRAINT fk_car_id FOREIGN KEY (RENTED_CAR_ID)" +
                            "REFERENCES CAR(ID)" +
                            "ON UPDATE CASCADE " +
                            "ON DELETE SET NULL);";
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    @Override
    public boolean rentCar(int customerID, int carID) {
        String sql =
                "UPDATE CUSTOMER SET RENTED_CAR_ID = " +carID+" WHERE ID ="+customerID;
        try {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }


    @Override
    public boolean addCustomer(String name) {
        String sql =
                "INSERT INTO CUSTOMER (NAME, RENTED_CAR_ID) " +
                        "VALUES ('" + name + "', NULL);";
        try {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
           return false;
        }
    }

    @Override
    public List<Customer> getCustomersList() {
        List<Customer> customersList = new ArrayList<>();

        String sql = "SELECT * FROM CUSTOMER ;";
        try {
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()){
                customersList.add(new Customer(resultSet.getInt("ID"),resultSet.getString("NAME"),resultSet.getInt("RENTED_CAR_ID")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customersList;
    }

    @Override
    public boolean returnRentedCar(int customerID) {
        String sql =
                "UPDATE CUSTOMER SET RENTED_CAR_ID = " +null+" WHERE ID ="+customerID;
        try {
            stmt.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public Customer getCustomer(int customerId) {

        String sql =
                "SELECT* FROM CUSTOMER WHERE ID = " + customerId + ";";
        try {
            ResultSet resultSet = stmt.executeQuery(sql);
            Customer customer = null;
            if (resultSet.next()) {
                customer = new Customer(resultSet.getInt("ID"),
                        resultSet.getString("NAME"),
                        resultSet.getInt("RENTED_CAR_ID"));
            }
            return customer;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
