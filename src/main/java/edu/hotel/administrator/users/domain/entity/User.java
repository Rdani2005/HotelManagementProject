package edu.hotel.administrator.users.domain.entity;

import edu.hotel.administrator.domain.entity.AggregateRoot;
import edu.hotel.administrator.users.domain.valueobject.UserId;
import java.util.Objects;

public class User extends AggregateRoot<UserId> {
    private final String username;
    private final String password;
    private boolean isActive;
    
    private User(UserBuilder builder) {
        super.setId(builder.id);
        this.username = builder.username;
        this.password = builder.password;
        this.isActive = builder.isActive;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public boolean getIsActive() {
        return this.isActive;
    }
    
    public void deactivateAccount() {
        this.isActive = false;
    }
    
    public void activateAccount() {
        this.isActive = true;
    }

    @Override
    public String toString() {
        return this.username;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User that = (User) obj;
        return that.password.equals(this.password) && that.username.equals(this.username);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.username);
        hash = 79 * hash + Objects.hashCode(this.password);
        return hash;
    }
    
    
    public static final class UserBuilder {
        private UserId id;
        private String username;
        private String password;
        private boolean isActive;
        
        private UserBuilder() {
            
        }
        
        public static UserBuilder builder() {
            return new UserBuilder();
        }
        
        public UserBuilder userId(UserId id) {
            this.id = id;
            return this;
        } 
        
        public UserBuilder username(String username) {
            this.username = username;
            return this;
        } 
        
        public UserBuilder password(String password) {
            this.password = password;
            return this;
        } 
        
        public UserBuilder isActive(boolean isActive) {
            this.isActive = isActive;
            return this;
        }
        
        public User build() {
            return new User(this);
        }
    }
    
}
