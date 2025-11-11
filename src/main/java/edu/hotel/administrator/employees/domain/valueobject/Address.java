package edu.hotel.administrator.employees.domain.valueobject;

public class Address {
    private final String street;
    private final String postalCode;
    private final String city;
    private final String state;
    private final String country;

    private Address(Builder builder) {
        this.street = builder.street;
        this.postalCode = builder.postalCode;
        this.city = builder.city;
        this.state = builder.state;
        this.country = builder.country;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s (%s)",
                street, city, state, country, postalCode);
    }

    public static class Builder {
        private String street;
        private String postalCode;
        private String city;
        private String state;
        private String country;

        public Builder street(String street) {
            this.street = street;
            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder city(String city) {
            this.city = city;
            return this;
        }

        public Builder state(String state) {
            this.state = state;
            return this;
        }

        public Builder country(String country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}