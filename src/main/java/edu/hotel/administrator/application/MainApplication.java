package edu.hotel.administrator.application;

import edu.hotel.administrator.contracts.application.ContractsMenu;
import edu.hotel.administrator.employees.application.EmployeesMenu;
import edu.hotel.administrator.services.CompositionRoot;
import edu.hotel.administrator.users.application.UserMenu;
import edu.hotel.administrator.utilities.Console;

public class MainApplication {
    public static void main(String[] args) {
        var root = CompositionRoot.getInstance();
        var io = new Console();

        var usersMenu = new UserMenu(io, root.userService());
        var employeesMenu = new EmployeesMenu(io, root.employeeService(), root.employeeRepository());
        var contractsMenu = new ContractsMenu(io, root.contractService(), root.employeeService());

        usersMenu.loginFlow();

        while (true) {
            io.header("Main Menu");
            System.out.println("1) Users");
            System.out.println("2) Employees");
            System.out.println("3) Contracts");
            System.out.println("4) Logout");
            System.out.println("0) Exit");

            int opt = io.promptInt("Choose: ");
            switch (opt) {
                case 1 -> usersMenu.run();
                case 2 -> employeesMenu.run();
                case 3 -> contractsMenu.run();
                case 4 -> usersMenu.loginFlow();
                case 0 -> { io.info("Bye!"); return; }
                default -> io.err("Invalid option.");
            }
        }
    }
}
