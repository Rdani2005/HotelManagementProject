package edu.hotel.administrator.contracts.domain.valueobject;

import edu.hotel.administrator.domain.valueobject.BaseId;
import java.util.UUID;

public class ContractId extends BaseId<UUID> {
    public ContractId(UUID value) {
        super(value);
    }
    
    @Override
    public String toString() {
        return this.getValue().toString();
    }
}
