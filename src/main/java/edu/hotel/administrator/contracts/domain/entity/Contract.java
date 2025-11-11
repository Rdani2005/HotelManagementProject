package edu.hotel.administrator.contracts.domain.entity;

import edu.hotel.administrator.contracts.domain.valueobject.ContractId;
import edu.hotel.administrator.domain.entity.AggregateRoot;
import edu.hotel.administrator.employees.domain.valueobject.EmployeeId;
import java.util.Date;

public class Contract extends AggregateRoot<ContractId> {

    private final int hoursPerDay;
    private final EmployeeId employeeId;
    private final int daysPerWeek;
    private final Date endDay;

    private Contract(Builder builder) {
        super.setId(builder.id);
        this.hoursPerDay = builder.hoursPerDay;
        this.employeeId = builder.employeeId;
        this.daysPerWeek = builder.daysPerWeek;
        this.endDay = builder.endDay;
    }

    public int getHoursPerDay() {
        return hoursPerDay;
    }

    public EmployeeId getEmployeeId() {
        return employeeId;
    }

    public int getDaysPerWeek() {
        return daysPerWeek;
    }

    public Date getEndDay() {
        return endDay;
    }
    
    @Override
    public String toString() {
        return String.format(
                "Contract{id=%s, employeeId=%s, hoursPerDay=%d, daysPerWeek=%d, endDay=%s}",
                getId(), employeeId, hoursPerDay, daysPerWeek, endDay
        );
    }

    public static class Builder {

        private ContractId id;
        private int hoursPerDay;
        private EmployeeId employeeId;
        private int daysPerWeek;
        private Date endDay;

        public Builder id(ContractId id) {
            this.id = id;
            return this;
        }

        public Builder hoursPerDay(int hoursPerDay) {
            this.hoursPerDay = hoursPerDay;
            return this;
        }

        public Builder employeeId(EmployeeId employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public Builder daysPerWeek(int daysPerWeek) {
            this.daysPerWeek = daysPerWeek;
            return this;
        }

        public Builder endDay(Date endDay) {
            this.endDay = endDay;
            return this;
        }

        public Contract build() {
            return new Contract(this);
        }
    }

}
