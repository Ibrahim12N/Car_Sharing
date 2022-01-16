package carsharing.DAO;

import carsharing.entity.Customer;

import java.util.List;

public interface CustomerDAO {

    boolean createTable();

    boolean rentCar(int customerID, int carID);

    boolean addCustomer(String name);

    List<Customer> getCustomersList();

    boolean returnRentedCar(int customerID);

    Customer getCustomer(int customerId);
}
