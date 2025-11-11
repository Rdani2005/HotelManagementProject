package edu.hotel.administrator.users.application;

import edu.hotel.administrator.users.domain.entity.User;
import edu.hotel.administrator.users.domain.exception.UserDomainException;
import edu.hotel.administrator.users.domain.service.UserAplicationService;
import edu.hotel.administrator.utilities.Console;

public class UserMenu {
    private final Console io;
    private final UserAplicationService userService;

    public UserMenu(Console io, UserAplicationService userService) {
        this.io = io;
        this.userService = userService;
    }

    public void run() {
        while (true) {
            io.header("Users Menu");
            System.out.println("1) List users");
            System.out.println("2) Create user");
            System.out.println("3) Activate user");
            System.out.println("4) Deactivate user");
            System.out.println("0) Back");

            int opt = io.promptInt("Choose: ");
            switch (opt) {
                case 1 -> list();
                case 2 -> create();
                case 3 -> activate();
                case 4 -> deactivate();
                case 0 -> { return; }
                default -> io.err("Invalid option.");
            }
        }
    }

    public User loginFlow() {
        while (true) {
            io.header("Login");
            String u = io.prompt("Username: ");
            String p = io.prompt("Password: ");
            try {
                User logged = userService.login(u, p);
                io.ok("Login successful. Welcome " + logged.getUsername() + "!");
                return logged;
            } catch (UserDomainException e) {
                io.err(e.getMessage());
            } catch (Exception e) {
                io.err("Login error: " + e.getMessage());
            }
        }
    }

    private void list() {
        User[] users = userService.getUsers();
        if (users == null || users.length == 0) {
            io.info("(no users)");
            return;
        }
        for (int i = 0; i < users.length; i++) {
            System.out.printf("[%d] %s%n", i, users[i]);
        }
    }

    private void create() {
        String u = io.prompt("Username: ");
        String p = io.prompt("Password: ");
        try {
            User created = userService.createUser(u, p);
            io.ok("Created: " + created);
        } catch (UserDomainException e) {
            io.err(e.getMessage());
        } catch (Exception e) {
            io.err("Create error: " + e.getMessage());
        }
    }

    private void activate() {
        User[] users = userService.getUsers();
        if (users == null || users.length == 0) { io.info("(no users)"); return; }
        list();
        int idx = io.promptInt("Index to activate: ");
        if (idx < 0 || idx >= users.length) { io.err("Invalid index."); return; }
        boolean ok = userService.activateUser(users[idx]);
        if (ok) io.ok("Activated."); else io.err("Could not activate.");
    }

    private void deactivate() {
        User[] users = userService.getUsers();
        if (users == null || users.length == 0) { io.info("(no users)"); return; }
        list();
        int idx = io.promptInt("Index to deactivate: ");
        if (idx < 0 || idx >= users.length) { io.err("Invalid index."); return; }
        boolean ok = userService.deactivateUser(users[idx]);
        if (ok) io.ok("Deactivated."); else io.err("Could not deactivate.");
    }
    
}
