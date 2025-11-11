package edu.hotel.administrator.employees.domain.entity;

import edu.hotel.administrator.domain.entity.AggregateRoot;
import edu.hotel.administrator.employees.domain.valueobject.Address;
import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;
import java.util.Objects;

public class Employee extends AggregateRoot<EmployeeId> {
    private final String name;
    private final String lastName;
    private final String position;
    private final Address address;
    private boolean isActive;

    private Employee(Builder builder) {
        super.setId(builder.id);
        this.name = builder.name;
        this.lastName = builder.lastName;
        this.position = builder.position;
        this.address = builder.address;
        this.isActive = builder.isActive;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public Address getAddress() {
        return address;
    }

    
    public boolean getIsActive() {
        return isActive;
    }
    
    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
                
    @Override
    public String toString() {
        return String.format("Employee{id=%s, name='%s', lastName='%s', position='%s', address=%s}",
                getId(), name, lastName, position, address);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee that = (Employee) obj;
        return that.getId().equals(this.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.getId());
        return hash;
    }    

    public static class Builder {
        private EmployeeId id;
        private String name;
        private String lastName;
        private String position;
        private Address address;
        private boolean isActive;

        public Builder id(EmployeeId id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }

        public Builder address(Address address) {
            this.address = address;
            return this;
        }
        
        public Builder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        public Employee build() {
            return new Employee(this);
        }
    }
}
