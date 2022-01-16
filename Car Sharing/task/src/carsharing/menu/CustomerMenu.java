package carsharing.menu;

import carsharing.DAO.*;
import carsharing.entity.Car;
import carsharing.entity.Company;
import carsharing.entity.Customer;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    private static final Scanner scanner= new Scanner(System.in) ;
    private final CompanyDAO companyDAO;
    private final CarDAO carDAO;
    private final CustomerDAO customerDAO;


    public CustomerMenu(Statement stmt) {
        this.companyDAO = new CompanyDAOImpl(stmt);
        this.carDAO = new CarDAOImpl(stmt);
        this.customerDAO = new CustomerDAOImpl(stmt);
    }

    public void customerMenu() throws SQLException {

        List<Customer> customerList = customerDAO.getCustomersList();

        if(customerList.isEmpty()){
            System.out.println("The customer list is empty!");
        }
        else {
            showCustomers(customerList);
            int customerId =Integer.parseInt(scanner.nextLine());
            if(customerId!=0){
            Customer customer =customerList.get(customerId-1);
            customerSpecificMenu(customer);}}

    }

    public void customerSpecificMenu(Customer customer) throws SQLException {
        while (true){
//          rentedCardId = 0 if the car is not rented and >0 if it's rented
            int rentedCarId = customerDAO.getCustomer(customer.getId()).getRentedCarId();
            showCustomerMenu();
            int input=Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    if(rentedCarId!=0){
                        System.out.println("You've already rented a car!");
                        break;
                    }
                    List<Company> companyList = companyDAO.getCompanies();
                    showCompanies(companyList);
                    int companyId = Integer.parseInt(scanner.nextLine());

                    List<Car> carList = carDAO.getCarsForCustomer(companyId);
                    showCars(carList);
                    int carId = Integer.parseInt(scanner.nextLine());

                    if(carId!=0){
                    String carName=carList.get(carId-1).getName();
                    Car car =carList.get(carId-1);
                    customerDAO.rentCar(customer.getId(),car.getId());
                    System.out.println("You rented '"+carName+"'");
                    }
                    break;
                case 2:
                    if(rentedCarId==0){
                        System.out.println("You didn't rent a car!");
                        break;
                    }
                    boolean result= customerDAO.returnRentedCar(customer.getId());
                    if(result){
                        System.out.println("You've returned a rented car!");
                    }
                    break;
                case 3:
                    if(rentedCarId==0){
                        System.out.println("You didn't rent a car!");
                        break;
                    }
                    Car myCar= carDAO.getCar(rentedCarId);
                    Company myCompany=companyDAO.getCompany(myCar.getCompanyID());
                    System.out.printf("Your rented car %s \nCompany: \n%s", myCar.getName(),myCompany.getName());
                    break;
                case 0:
                    return;
            }
        }
    }

    private void showCars(List<Car> carList) {
        if(carList.isEmpty()){
            System.out.println("The car list is empty!");
            return;}
        System.out.println("Choose a car: ");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(i + 1 + ". " + carList.get(i).getName());
        }
        System.out.println("0. Back\n");
    }


    public void createCustomer() {
        System.out.println("Enter the customer name:");
        String customer = scanner.nextLine();
        boolean result = customerDAO.addCustomer(customer);
        if (result) {
            System.out.println("The customer was created");
        }
    }

    private void showCompanies(List<Company> companyList) {
        if(companyList.isEmpty()){
            System.out.println("The company list is empty!");
            return;}
        System.out.println("Choose a company: ");
        for (int i = 0; i < companyList.size(); i++) {
            System.out.println(i + 1 + ". " + companyList.get(i).getName());
        }
        System.out.println("0. Back\n");
    }

    private void showCustomers(List<Customer> customerList) {
        System.out.println("Choose a customer:\n");
        for (int i = 0; i < customerList.size(); i++) {
            System.out.println(i + 1 + ". " + customerList.get(i).getName());
        }
        System.out.println("0. Back\n");
    }

    public void showCustomerMenu() {
        System.out.println("\n1. Rent a car");
        System.out.println("2. Return a rented car");
        System.out.println("3. My rented car");
        System.out.println("0. Back\n");
    }

}
