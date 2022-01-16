package carsharing.DAO;

import carsharing.entity.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompanyDAO {
    boolean createTable();

    boolean createCompany(String name);

    Company getCompany(int companyID);

    List<Company> getCompanies() throws SQLException;
}
