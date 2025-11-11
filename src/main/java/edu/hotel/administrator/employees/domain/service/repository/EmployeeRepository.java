package edu.hotel.administrator.employees.domain.service.repository;
import edu.hotel.administrator.employees.domain.entity.Employee;
import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;

public interface EmployeeRepository {
    public void saveEmployee(Employee employee);
    
    public Employee[] getAllEmployees();
    
    public Employee getEmployee(EmployeeId id);
    
    public Employee getEmployeeInformation(Employee employee);
    
    public void deactivateEmployeer(Employee employee);
    
    public void activateEmployee(Employee employee);
    
    void deleteEmployee(Employee employee);
}
 