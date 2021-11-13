package com.revature.quizzard.models;

public class AppUser {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private Role role;

    public AppUser() {
        this.role = Role.LOCKED;
    }

    public AppUser(String firstName, String lastName, String email, String username, String password) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public AppUser(String id, String firstName, String lastName, String email, String username, String password) {
        this(firstName, lastName, email, username, password);
        this.id = id;
    }

    public AppUser(String id, String firstName, String lastName, String email, String username, String password, Role role) {
        this(id, firstName, lastName, email, username, password);
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toFileString() {
        return id + ":" + firstName + ":" + lastName + ":" + email + ":" + username + ":" + password + ":" + role;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id='" + id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                '}';
    }

    public enum Role {
        ADMIN("Admin"),
        DEV("Dev"),
        BASIC_USER("Basic User"),
        PREMIUM_USER("Premium User"),
        LOCKED("Account Locked"),
        BANNED("Banned");

        private String name;

        Role(String name) {
            this.name = name;
        }

        public Role fromString(String name) {
            try {
                return Role.valueOf(name);
            } catch (IllegalArgumentException e) {
                return LOCKED;
            }
        }

        @Override
        public String toString() {
            return name;
        }

    }

}
