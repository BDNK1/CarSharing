package carsharing.menu;


import java.util.Scanner;

import static carsharing.customer.CustomerService.*;
import static carsharing.menu.CustomerMenu.loginAsCustomer;
import static carsharing.menu.ManagerMenu.loginAsManager;


public class Menu {

    static Scanner scanner = new Scanner(System.in);

    public static void startMenu() {
        int input = -1;
        while (input != 0) {
            System.out.println("\n1. Log in as a manager\n" +
                    "2. Log in as a customer\n"+
                    "3. Create a customer\n"+
                    "0. Exit");
            input = scanner.nextInt();
            switch (input) {
                case 1:
                    loginAsManager();
                    break;
                case 2:
                    loginAsCustomer();
                    break;
                case 3:
                    System.out.println("Enter the customer name:");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    createCustomer(name,null);
                    break;

            }
        }

    }




    }


