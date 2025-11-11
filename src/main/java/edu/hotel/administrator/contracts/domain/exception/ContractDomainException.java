package edu.hotel.administrator.contracts.domain.exception;

import edu.hotel.administrator.domain.exception.DomainException;

public class ContractDomainException extends DomainException {

    public ContractDomainException(String message) {
        super(message);
    }

    public ContractDomainException(String message, Throwable cause) {
        super(message, cause);
    }    
}
