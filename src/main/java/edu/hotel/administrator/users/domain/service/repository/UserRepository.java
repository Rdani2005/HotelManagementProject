package edu.hotel.administrator.users.domain.service.repository;

import edu.hotel.administrator.users.domain.entity.User;
import edu.hotel.administrator.users.domain.valueobject.UserId;

public interface UserRepository {
    public void saveUser(User user);
    
    public User[] getAllUsers();
    
    public User getUser(UserId id);
    
    public User getUserInformation(User user);
    
    public void deactivateUser(User user);
    
    public void activateUser(User user);
}
