package edu.hotel.administrator.users.domain.service;

import edu.hotel.administrator.users.domain.entity.User;

public interface UserAplicationService {
    public User login(String username, String password);
    
    public User getUser(User user);
    
    public User[] getUsers();
    
    public User createUser(String username, String password);
    
    public boolean deactivateUser(User user);
    
    public boolean activateUser(User user);
}
