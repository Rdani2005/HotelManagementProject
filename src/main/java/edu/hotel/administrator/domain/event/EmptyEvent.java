package edu.hotel.administrator.domain.event;

public final class EmptyEvent implements DomainEvent<Void> {
    private static EmptyEvent instance;
    
    private EmptyEvent() {
    }

    public static synchronized EmptyEvent getInstance() {
        if (instance == null) {
            instance = new EmptyEvent();
        }
        return instance;
    }
}
