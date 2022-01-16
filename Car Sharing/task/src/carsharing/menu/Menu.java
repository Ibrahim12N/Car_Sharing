package carsharing.menu;

import carsharing.DAO.*;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner= new Scanner(System.in) ;
    private final CompanyDAO companyDAO;
    private final CarDAO carDAO;
    private final CustomerDAO customerDAO;
    private final ManagerMenu managerMenu;
    private final CustomerMenu customerMenu;

    public Menu(Statement stmt) {
        this.companyDAO = new CompanyDAOImpl(stmt);
        this.carDAO = new CarDAOImpl(stmt);
        this.customerDAO = new CustomerDAOImpl(stmt);
        this.managerMenu = new ManagerMenu(stmt);
        this.customerMenu = new CustomerMenu(stmt);
    }

    public void menu() throws SQLException {
        createTables();
        while (true) {
            showMenu();
            int input = Integer.parseInt(scanner.nextLine());
            switch (input) {
                case 1:
                    managerMenu.managerMenu();
                    break;
                case 2:
                    customerMenu.customerMenu();
                    break;
                case 3:
                    customerMenu.createCustomer();
                    break;
                case 0:
                    return;
            }
        }
    }

    private void createTables() {
        companyDAO.createTable();
        carDAO.createTable();
        customerDAO.createTable();
    }

    private void showMenu() {
        System.out.println("\n1. Log in as a manager");
        System.out.println("2. Log in as a customer");
        System.out.println("3. Create a customer");
        System.out.println("0. Exit\n");
    }



}
