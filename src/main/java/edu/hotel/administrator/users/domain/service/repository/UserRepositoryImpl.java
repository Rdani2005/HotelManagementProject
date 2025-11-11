package edu.hotel.administrator.users.domain.service.repository;

import edu.hotel.administrator.users.domain.entity.User;
import edu.hotel.administrator.users.domain.exception.UserNotFoundException;
import edu.hotel.administrator.users.domain.valueobject.UserId;
import java.util.Objects;
import java.util.UUID;

public final class UserRepositoryImpl implements UserRepository {

    private User[] users;
    private int size;

    public UserRepositoryImpl() {
        this.users = new User[1];
        this.size = 0;

        User root = User.UserBuilder.builder()
                .userId(new UserId(UUID.randomUUID()))
                .username("ROOT")
                .password("ROOT")
                .isActive(true)
                .build();

        this.saveUser(root);
    }

    @Override
    public void saveUser(User user) {
        Objects.requireNonNull(user, "user must not be null");

        int idx = indexOf(user.getId());
        if (idx >= 0) {
            users[idx] = user;
            return;
        }

        if (size == users.length) {
            growByOne();
        }

        users[size++] = user;
    }

    @Override
    public User[] getAllUsers() {
        User[] snapshot = new User[size];
        System.arraycopy(users, 0, snapshot, 0, size);
        return snapshot;
    }

    @Override
    public User getUser(UserId id) {
        if (id == null) {
            return null;
        }
        int idx = indexOf(id);
        return (idx >= 0) ? users[idx] : null;
    }

    @Override
    public User getUserInformation(User user) {
        Objects.requireNonNull(user, "User parameter cannot be null.");

        int idx = indexOf(user);
        if (idx >= 0) {
            return users[idx];
        }

        throw new UserNotFoundException("User was not found.");
    }

    @Override
    public void deactivateUser(User user) {
        Objects.requireNonNull(user, "User parameter cannot be null.");

        int idx = indexOf(user);
        if (idx < 0) {
            throw new UserNotFoundException("User was not found.");
        }

        users[idx].deactivateAccount();
    }

    @Override
    public void activateUser(User user) {
        Objects.requireNonNull(user, "User parameter cannot be null.");

        int idx = indexOf(user);
        if (idx < 0) {
            throw new UserNotFoundException("User was not found.");
        }

        users[idx].activateAccount();
    }

    private void growByOne() {
        User[] bigger = new User[users.length + 1];
        System.arraycopy(users, 0, bigger, 0, users.length);
        users = bigger;
    }

    private int indexOf(UserId id) {
        for (int i = 0; i < size; i++) {
            User u = users[i];
            if (u != null && u.getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    private int indexOf(User target) {
        for (int i = 0; i < size; i++) {
            User current = users[i];
            if (current != null && current.equals(target)) {
                return i;
            }
        }
        return -1;
    }
}
