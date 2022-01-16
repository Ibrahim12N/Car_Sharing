package carsharing.DAO;

import carsharing.entity.Car;
import carsharing.entity.Company;

import java.util.List;

public interface CarDAO {

    boolean createTable();

    List<Car> getCars(int companyID);

    List<Car> getCarsForCustomer(int companyId);

    boolean addCar(String name, int companyID);

    Car getCar(int carId);


}
