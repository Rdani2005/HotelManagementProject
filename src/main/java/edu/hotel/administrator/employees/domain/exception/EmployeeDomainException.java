package edu.hotel.administrator.employees.domain.exception;

import edu.hotel.administrator.domain.exception.DomainException;

public class EmployeeDomainException extends DomainException {    
    public EmployeeDomainException(String message) {
        super(message);
    }
    
    public EmployeeDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
