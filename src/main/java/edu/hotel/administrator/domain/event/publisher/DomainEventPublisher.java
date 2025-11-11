package edu.hotel.administrator.domain.event.publisher;

public interface DomainEventPublisher<T extends DomainEventPublisher<?>> {
    void publish(T domainEvent);
}
