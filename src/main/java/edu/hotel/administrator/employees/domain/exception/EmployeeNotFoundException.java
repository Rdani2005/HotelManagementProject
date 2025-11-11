package edu.hotel.administrator.employees.domain.exception;

public class EmployeeNotFoundException extends EmployeeDomainException {
    public EmployeeNotFoundException(String message) {
        super(message);
    }
    
    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
