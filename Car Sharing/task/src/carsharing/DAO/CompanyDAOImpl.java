package carsharing.DAO;

import carsharing.entity.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAOImpl implements CompanyDAO{

private final Statement stmt;

    public CompanyDAOImpl(Statement stmt) {
        this.stmt = stmt;
    }

    @Override
    public boolean createTable() {

        try {
            String sql =
                    "CREATE TABLE COMPANY (" +
                            "ID INT PRIMARY KEY AUTO_INCREMENT," +
                            "NAME VARCHAR(20) NOT NULL UNIQUE);";
            stmt.execute(sql);
            return true;
        }  catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean createCompany(String company) {
        String sql = "INSERT INTO COMPANY (NAME) VALUES ('" + company + "');";
            try {
                stmt.execute(sql);
                return true;
            } catch (SQLException e) {
                return false;
            }
    }

    @Override
    public Company getCompany(int companyId) {
        String sql = "SELECT * FROM COMPANY WHERE ID = "+companyId+";";
        try {
            ResultSet resultSet=stmt.executeQuery(sql);
            if (resultSet.next()) {
                return new Company(resultSet.getInt("ID"),
                        resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Company> getCompanies() {
        List<Company> companyList = new ArrayList<>();
        String sql = "SELECT * FROM company;";
        try {
            ResultSet resultSet = stmt.executeQuery(sql);
            while (resultSet.next()){
                companyList.add(new Company(resultSet.getInt("ID"),resultSet.getString("NAME")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }

}