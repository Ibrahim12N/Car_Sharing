package carsharing.menu;

import carsharing.DAO.*;
import carsharing.entity.Car;
import carsharing.entity.Company;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

public class ManagerMenu {
    private static final Scanner scanner= new Scanner(System.in) ;
    private final CompanyDAO companyDAO;
    private final CarDAO carDAO;


    public ManagerMenu(Statement stmt) {
        this.companyDAO = new CompanyDAOImpl(stmt);
        this.carDAO = new CarDAOImpl(stmt);
    }

    public void managerMenu() throws SQLException {

        while (true){
            showMangerMenu();
            int input=Integer.parseInt(scanner.nextLine());
            switch (input)
            {
                case 1:
                    List<Company> companies = companyDAO.getCompanies();
                    if(companies.isEmpty()){
                        System.out.println("The company list is empty");
                    }else {
                        showCompanies(companies);
                        int companyId =Integer.parseInt(scanner.nextLine());
                        if(companyId!=0){
                        String companyName=companies.get(companyId-1).getName();
                        Company company =companies.get(companyId-1);
                        System.out.println("'"+companyName+"' company:");
                        companySpecificMenu(company);
                        }}
                    break;
                case 2:
                    System.out.println("Enter the company name:");
                    String company = scanner.nextLine();
                    boolean result = companyDAO.createCompany(company);
                    if (result) {
                        System.out.println("The company was created");
                    }
                    break;
                case 0:
                    return;
            }
        }
    }



    public void companySpecificMenu(Company company) {
        while (true){
            showCarMenu();
            int input=Integer.parseInt(scanner.nextLine());
            switch (input)
            {
                case 1:
                    List<Car> carList = carDAO.getCars(company.getId());
                    if(carList.isEmpty()){
                        System.out.println("The car list is empty!");
                    }else {
                        for (int i = 0; i < carList.size(); i++) {
                            System.out.println(i + 1 + ". " + carList.get(i).getName());
                        }}
                    break;
                case 2:
                    System.out.println("Enter the car name:");
                    String car = scanner.nextLine();
                    boolean result = carDAO.addCar(car,company.getId());
                    if (result) {
                        System.out.println("The car was created");
                    }
                    break;
                case 0:
                    return;
            }
        }
    }

    private void showCompanies(List<Company> companies) {
        System.out.println("Choose a company:\n");
        for (int i = 0; i < companies.size(); i++) {
            System.out.println(i + 1 + ". " + companies.get(i).getName());
        }
        System.out.println("0. Back\n");
    }

    public void showMangerMenu() {
        System.out.println("\n1. Company list");
        System.out.println("2. Create a company");
        System.out.println("0. Back\n");
    }
    public void showCarMenu() {
        System.out.println("\n1. Car list");
        System.out.println("2. Create a car");
        System.out.println("0. Back\n");
    }



}
