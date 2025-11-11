package edu.hotel.administrator.employees.application;

import edu.hotel.administrator.employees.domain.entity.Employee;
import edu.hotel.administrator.employees.domain.service.EmployeeDomainService;
import edu.hotel.administrator.employees.domain.service.repository.EmployeeRepository;
import edu.hotel.administrator.employees.domain.valueobject.Address;
import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;
import edu.hotel.administrator.utilities.Console;
import java.util.UUID;

public class EmployeesMenu {

    private final Console io;
    private final EmployeeDomainService employeeService;
    private final EmployeeRepository employeeRepository;

    public EmployeesMenu(Console io, EmployeeDomainService employeeService, EmployeeRepository employeeRepository) {
        this.io = io;
        this.employeeService = employeeService;
        this.employeeRepository = employeeRepository;
    }

    public void run() {
        while (true) {
            io.header("Employees Menu");
            System.out.println("1) List employees");
            System.out.println("2) Create employee");
            System.out.println("3) Activate employee");
            System.out.println("4) Deactivate employee");
            System.out.println("5) Delete employee");
            System.out.println("0) Back");

            int opt = io.promptInt("Choose: ");
            switch (opt) {
                case 1 ->
                    list();
                case 2 ->
                    create();
                case 3 ->
                    activate();
                case 4 ->
                    deactivate();
                case 5 ->
                    deleteEmp();
                case 0 -> {
                    return;
                }
                default ->
                    io.err("Invalid option.");
            }
        }
    }

    private void list() {
        Employee[] emps = employeeService.getAllEmployees();
        if (emps == null || emps.length == 0) {
            io.info("(no employees)");
            return;
        }
        for (int i = 0; i < emps.length; i++) {
            System.out.printf("[%d] %s | active=%s%n", i, emps[i], emps[i].getIsActive());
        }
    }

    private void create() {
        String name = io.prompt("Name: ");
        String last = io.prompt("Last Name: ");
        String pos = io.prompt("Position: ");
        String street = io.prompt("Address - Street: ");
        String postal = io.prompt("Address - Postal: ");
        String city = io.prompt("Address - City: ");
        String state = io.prompt("Address - State: ");
        String country = io.prompt("Address - Country: ");

        Address address = new Address.Builder()
                .street(street).postalCode(postal).city(city).state(state).country(country).build();

        Employee e = new Employee.Builder()
                .id(new EmployeeId(UUID.randomUUID()))
                .name(name).lastName(last).position(pos)
                .address(address).isActive(true).build();

        employeeRepository.saveEmployee(e);
        io.ok("Employee created: " + e);
    }

    private void activate() {
        Employee[] emps = employeeService.getAllEmployees();
        if (emps == null || emps.length == 0) {
            io.info("(no employees)");
            return;
        }
        list();
        int idx = io.promptInt("Index to activate: ");
        if (idx < 0 || idx >= emps.length) {
            io.err("Invalid index.");
            return;
        }
        employeeService.activateEmployee(emps[idx].getId());
        io.ok("Employee activated.");
    }

    private void deactivate() {
        Employee[] emps = employeeService.getAllEmployees();
        if (emps == null || emps.length == 0) {
            io.info("(no employees)");
            return;
        }
        list();
        int idx = io.promptInt("Index to deactivate: ");
        if (idx < 0 || idx >= emps.length) {
            io.err("Invalid index.");
            return;
        }
        employeeService.deactivateEmployee(emps[idx].getId());
        io.ok("Employee deactivated.");
    }

    private void deleteEmp() {
        Employee[] emps = employeeService.getAllEmployees();
        if (emps == null || emps.length == 0) {
            io.info("(no employees)");
            return;
        }
        list();
        int idx = io.promptInt("Index to delete: ");
        if (idx < 0 || idx >= emps.length) {
            io.err("Invalid index.");
            return;
        }
        employeeService.deleteEmployee(emps[idx].getId());
        io.ok("Employee deleted.");
    }
}
