package edu.hotel.administrator.employees.domain.service.repository;

import edu.hotel.administrator.employees.domain.entity.Employee;
import edu.hotel.administrator.employees.domain.exception.EmployeeNotFoundException;
import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;
import java.util.Objects;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    private Employee[] employees;
    private int size;

    public EmployeeRepositoryImpl() {
        this.employees = new Employee[1];
        this.size = 0;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Objects.requireNonNull(employee, "Employee must not be null");

        int idx = indexOf(employee.getId());
        if (idx >= 0) {
            employees[idx] = employee;
            return;
        }

        if (size == employees.length) {
            growByOne();
        }

        employees[size++] = employee;
    }

    @Override
    public Employee[] getAllEmployees() {
        Employee[] snapshot = new Employee[size];
        System.arraycopy(employees, 0, snapshot, 0, size);
        return snapshot;
    }

    @Override
    public Employee getEmployee(EmployeeId id) {
        if (id == null) {
            return null;
        }
        int idx = indexOf(id);
        return (idx >= 0) ? employees[idx] : null;
    }

    @Override
    public Employee getEmployeeInformation(Employee employee) {
        Objects.requireNonNull(employee, "Employee parameter cannot be null.");

        int idx = indexOf(employee);
        if (idx >= 0) {
            return employees[idx];
        }

        throw new EmployeeNotFoundException("Employee was not found.");
    }

    @Override
    public void deactivateEmployeer(Employee employee) {
        Objects.requireNonNull(employee, "Employee parameter cannot be null.");

        int idx = indexOf(employee);
        if (idx < 0) {
            throw new EmployeeNotFoundException("Employee was not found.");
        }

        employees[idx].setIsActive(false);
    }

    @Override
    public void activateEmployee(Employee employee) {
        Objects.requireNonNull(employee, "Employee parameter cannot be null.");

        int idx = indexOf(employee);
        if (idx < 0) {
            throw new EmployeeNotFoundException("Employee was not found.");
        }

        employees[idx].setIsActive(true);
    }

    @Override
    public void deleteEmployee(Employee employee) {
        java.util.Objects.requireNonNull(employee, "Employee parameter cannot be null.");

        int idx = indexOf(employee);
        if (idx < 0) {
            throw new EmployeeNotFoundException("Employee was not found.");
        }

        int numMoved = size - idx - 1;
        if (numMoved > 0) {
            System.arraycopy(employees, idx + 1, employees, idx, numMoved);
        }
        employees[--size] = null; // ayudar al GC
    }

    private void growByOne() {
        Employee[] bigger = new Employee[employees.length + 1];
        System.arraycopy(employees, 0, bigger, 0, employees.length);
        employees = bigger;
    }

    private int indexOf(EmployeeId id) {
        for (int i = 0; i < size; i++) {
            Employee e = employees[i];
            if (e != null && e.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private int indexOf(Employee target) {
        for (int i = 0; i < size; i++) {
            Employee current = employees[i];
            if (current != null && current.equals(target)) {
                return i;
            }
        }
        return -1;
    }

}
