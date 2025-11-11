package edu.hotel.administrator.users.domain.exception;

import edu.hotel.administrator.domain.exception.DomainException;

public class UserDomainException extends DomainException {
    public UserDomainException(String message) {
        super(message);
    }

    public UserDomainException(String message, Throwable cause) {
        super(message, cause);
    }
  
}
