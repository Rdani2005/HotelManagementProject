package edu.hotel.administrator.contracts.application;

import edu.hotel.administrator.contracts.domain.entity.Contract;
import edu.hotel.administrator.contracts.domain.exception.ContractNotFoundDomainException;
import edu.hotel.administrator.contracts.domain.service.ContractDomainService;
import edu.hotel.administrator.contracts.domain.valueobject.ContractId;
import edu.hotel.administrator.employees.domain.entity.Employee;
import edu.hotel.administrator.employees.domain.service.EmployeeDomainService;
import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;
import edu.hotel.administrator.utilities.Console;
import java.util.UUID;

public class ContractsMenu {

    private final Console io;
    private final ContractDomainService contractService;
    private final EmployeeDomainService employeeService;

    public ContractsMenu(Console io, ContractDomainService contractService, EmployeeDomainService employeeService) {
        this.io = io;
        this.contractService = contractService;
        this.employeeService = employeeService;
    }

    public void run() {
        while (true) {
            io.header("Contracts Menu");
            System.out.println("1) List contracts");
            System.out.println("2) Get contract by employee");
            System.out.println("3) Get contract by id");
            System.out.println("4) Add contract for employee");
            System.out.println("0) Back");

            int opt = io.promptInt("Choose: ");
            switch (opt) {
                case 1 ->
                    list();
                case 2 ->
                    getByEmployee();
                case 3 ->
                    getById();
                case 4 ->
                    addForEmployee();
                case 0 -> {
                    return;
                }
                default ->
                    io.err("Invalid option.");
            }
        }
    }

    private void list() {
        Contract[] cs = contractService.getContracts();
        if (cs == null || cs.length == 0) {
            io.info("(no contracts)");
            return;
        }
        for (int i = 0; i < cs.length; i++) {
            System.out.printf("[%d] %s%n", i, cs[i]);
        }
    }

    private void getByEmployee() {
        Employee[] emps = employeeService.getAllEmployees();
        if (emps == null || emps.length == 0) {
            io.info("(no employees)");
            return;
        }
        for (int i = 0; i < emps.length; i++) {
            System.out.printf("[%d] %s%n", i, emps[i]);
        }
        int idx = io.promptInt("Employee index: ");
        if (idx < 0 || idx >= emps.length) {
            io.err("Invalid index.");
            return;
        }
        try {
            Contract c = contractService.getContractByEmployee(emps[idx].getId());
            io.ok("Contract: " + c);
        } catch (ContractNotFoundDomainException e) {
            io.err(e.getMessage());
        }

    }

    private void getById() {
        try {
            ContractId id = new ContractId(UUID.fromString(io.prompt("Contract UUID: ")));
            Contract c = contractService.getContractById(id);
            io.ok("Contract: " + c);
        } catch (Exception e) {
            io.err("Error: " + e.getMessage());
        }
    }

    private void addForEmployee() {
        Employee[] emps = employeeService.getAllEmployees();
        if (emps == null || emps.length == 0) {
            io.info("(no employees)");
            return;
        }
        for (int i = 0; i < emps.length; i++) {
            System.out.printf("[%d] %s%n", i, emps[i]);
        }
        int idx = io.promptInt("Employee index to add contract: ");
        if (idx < 0 || idx >= emps.length) {
            io.err("Invalid index.");
            return;
        }

        EmployeeId employeeId = emps[idx].getId();

        int hoursPerDay = io.promptInt("Hours per day (1-24): ");
        if (hoursPerDay < 1 || hoursPerDay > 24) {
            io.err("hoursPerDay must be between 1 and 24.");
            return;
        }

        int daysPerWeek = io.promptInt("Days per week (1-7): ");
        if (daysPerWeek < 1 || daysPerWeek > 7) {
            io.err("daysPerWeek must be between 1 and 7.");
            return;
        }

        String endDayStr = io.prompt("End date (yyyy-MM-dd), leave empty for no end: ");
        java.util.Date endDay = null;
        if (!endDayStr.isBlank()) {
            try {
                java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
                sdf.setLenient(false);
                endDay = sdf.parse(endDayStr);
            } catch (java.text.ParseException e) {
                io.err("Invalid date format. Use yyyy-MM-dd.");
                return;
            }
        }

        Contract contract = new Contract.Builder()
                .id(new ContractId(java.util.UUID.randomUUID()))
                .employeeId(employeeId)
                .hoursPerDay(hoursPerDay)
                .daysPerWeek(daysPerWeek)
                .endDay(endDay)
                .build();

        try {
            contractService.addEmployeeContract(employeeId, contract);
            io.ok("Contract added: " + contract);
        } catch (ContractNotFoundDomainException ex) {
            io.err("Could not add contract: " + ex.getMessage());
        }
    }
}
