package edu.hotel.administrator.employees.domain.valueobject;

import edu.hotel.administrator.domain.valueobject.BaseId;
import java.util.UUID;

public class EmployeeId extends BaseId<UUID> {

    public EmployeeId(UUID id) {
        super(id);
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }
}
