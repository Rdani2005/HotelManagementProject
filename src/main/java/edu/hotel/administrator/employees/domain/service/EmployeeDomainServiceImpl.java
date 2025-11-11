package edu.hotel.administrator.employees.domain.service;

import edu.hotel.administrator.employees.domain.exception.EmployeeNotFoundException;
import edu.hotel.administrator.employees.domain.service.repository.EmployeeRepository;
import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;
import edu.hotel.administrator.employees.domain.entity.Employee;
import java.util.Objects;

public class EmployeeDomainServiceImpl implements EmployeeDomainService {
    private final EmployeeRepository repository;
    
    public EmployeeDomainServiceImpl(EmployeeRepository repository) {
        this.repository = Objects.requireNonNull(repository, "repository must not be null");
    }
    
    @Override
    public boolean validateEmployeeExists(EmployeeId id) {
        Objects.requireNonNull(id, "id must not be null");
        return repository.getEmployee(id) != null;
    }

    @Override
    public Employee getEmployee(EmployeeId id) {
        Objects.requireNonNull(id, "id must not be null");
        Employee e = repository.getEmployee(id);
        if (e == null) {
            throw new EmployeeNotFoundException("Employee was not found.");
        }
        return e;
    }

    @Override
    public Employee[] getAllEmployees() {
        return repository.getAllEmployees();
    }

    @Override
    public void deactivateEmployee(EmployeeId id) {
        Employee e = getEmployee(id);
        repository.deactivateEmployeer(e);
    }

    @Override
    public void activateEmployee(EmployeeId id) {
        Employee e = getEmployee(id);
        repository.activateEmployee(e);
    }

    @Override
    public void deleteEmployee(EmployeeId id) {
        Employee e = getEmployee(id);
        repository.deleteEmployee(e);
    }
}
