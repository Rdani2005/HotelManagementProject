package edu.hotel.administrator.users.domain.valueobject;

import edu.hotel.administrator.domain.valueobject.BaseId;
import java.util.UUID;

public class UserId extends BaseId<UUID> {
    public UserId(UUID value) {
        super(value);
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }
    
    
}
