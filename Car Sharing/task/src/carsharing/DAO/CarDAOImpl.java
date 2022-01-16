package carsharing.DAO;

import carsharing.entity.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAOImpl implements CarDAO {

    private final Statement stmt;

    public CarDAOImpl(Statement stmt) {
        this.stmt = stmt;
    }

    @Override
    public boolean createTable() {

        try {
            String sql =
                    "CREATE TABLE CAR (" +
                            "ID INT PRIMARY KEY AUTO_INCREMENT," +
                            "NAME VARCHAR(20) NOT NULL UNIQUE," +
                            "COMPANY_ID INT NOT NULL," +
                            "IS_RENTED BOOLEAN DEFAULT FALSE," +
                            "CONSTRAINT fk_company FOREIGN KEY (COMPANY_ID)" +
                            "REFERENCES COMPANY(ID)" +
                            "ON UPDATE CASCADE " +
                            "ON DELETE SET NULL);";
            stmt.execute(sql);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public List<Car> getCars(int companyId) {
        List<Car> carsList = new ArrayList<>();
        String sql = "SELECT * FROM CAR WHERE COMPANY_ID = " + companyId + ";";
        try {
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                carsList.add(new Car(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("company_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carsList;
    }

    @Override
    public List<Car> getCarsForCustomer(int companyId) {
        List<Car> carsList = new ArrayList<>();
        String sql = "SELECT * FROM CAR LEFT JOIN CUSTOMER ON CAR.id = CUSTOMER.rented_car_id WHERE CUSTOMER.rented_car_id is null;";
        try {
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()) {
                carsList.add(new Car(resultSet.getInt("ID"), resultSet.getString("NAME"), resultSet.getInt("company_id")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carsList;
    }

    @Override
    public boolean addCar(String car, int companyId) {
        String sql =
                "INSERT INTO CAR (NAME, COMPANY_ID) " +
                        "VALUES ('" + car + "', " + companyId + ");";
        try {
            stmt.execute(sql);
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public Car getCar(int carId) {
        String sql =
                "SELECT* FROM CAR WHERE ID = " + carId + ";";
        try {
            ResultSet resultSet = stmt.executeQuery(sql);
            Car car = null;
            if (resultSet.next()) {
                car = new Car(resultSet.getInt("ID"),
                        resultSet.getString("NAME"),
                        resultSet.getInt("COMPANY_ID"));
            }
            return car;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}