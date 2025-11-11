package edu.hotel.administrator.employees.domain.service;

import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;
import edu.hotel.administrator.employees.domain.entity.Employee;

public interface EmployeeDomainService {
    boolean validateEmployeeExists(EmployeeId id);
    Employee getEmployee(EmployeeId id);
    Employee[] getAllEmployees();
    void deactivateEmployee(EmployeeId id);
    void activateEmployee(EmployeeId id);
    void deleteEmployee(EmployeeId id);
}
