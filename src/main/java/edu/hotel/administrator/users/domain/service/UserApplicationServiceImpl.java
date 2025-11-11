package edu.hotel.administrator.users.domain.service;

import edu.hotel.administrator.users.domain.entity.User;
import edu.hotel.administrator.users.domain.exception.UserDomainException;
import edu.hotel.administrator.users.domain.exception.UserNotFoundException;
import edu.hotel.administrator.users.domain.service.repository.UserRepository;
import edu.hotel.administrator.users.domain.valueobject.UserId;
import java.util.Objects;
import java.util.UUID;

public class UserApplicationServiceImpl implements UserAplicationService {
    private final UserRepository userRepository;
    
    public UserApplicationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    
    @Override
    public User login(String username, String password) {
        Objects.requireNonNull(username, "username must not be null");
        Objects.requireNonNull(password, "password must not be null");

        User probe = User.UserBuilder.builder()
                .userId(new UserId(UUID.randomUUID()))
                .username(username)
                .password(password)
                .isActive(true)
                .build();

        User found = userRepository.getUserInformation(probe);

        if (!found.getIsActive()) {
            throw new UserDomainException("User account is deactivated.");
        }

        return found;
    }
    
    @Override
    public User getUser(User user) {
        Objects.requireNonNull(user, "user must not be null");
        return userRepository.getUserInformation(user);
    }
    
    @Override
    public User[] getUsers() {
        return userRepository.getAllUsers();
    }
    
    @Override
    public User createUser(String username, String password) {
        Objects.requireNonNull(username, "username must not be null");
        Objects.requireNonNull(password, "password must not be null");

        for (User existing : userRepository.getAllUsers()) {
            if (existing != null && username.equals(existing.getUsername())) {
                throw new UserDomainException("Username already exists: " + username);
            }
        }

        User newUser = User.UserBuilder.builder()
                .userId(new UserId(UUID.randomUUID()))
                .username(username)
                .password(password)
                .isActive(true)
                .build();

        userRepository.saveUser(newUser);
        return newUser;
    }
    
    @Override
    public boolean deactivateUser(User user) {
        Objects.requireNonNull(user, "user must not be null");
        try {
            userRepository.deactivateUser(user);
            return true;
        } catch (UserNotFoundException ex) {
            return false;
        }
    }
    
    @Override
    public boolean activateUser(User user) {
        Objects.requireNonNull(user, "user must not be null");
        try {
            userRepository.activateUser(user);
            return true;
        } catch (UserNotFoundException ex) {
            return false;
        }
    }


}
