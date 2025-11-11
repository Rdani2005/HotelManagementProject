package edu.hotel.administrator.contracts.domain.exception;

public class ContractNotFoundDomainException extends ContractDomainException {
    public ContractNotFoundDomainException(String message) {
        super(message);
    }

    public ContractNotFoundDomainException(String message, Throwable cause) {
        super(message, cause);
    }    
}
