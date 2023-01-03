package model;

import java.util.Objects;

/**
 *  this creates a user profile
 */
public class User {
    /**
     * string of user username
     */
    String username;
    /**
     * string of user password
     */
    String password;
    /**
     * string of user email
     */
    String email;
    /**
     * string of user first name
     */
    String firstName;
    /**
     * string of user last name
     */
    String lastName;
    /**
     * string of user gender f or m
     */
    String gender;
    /**
     * string of user's person ID
     */
    String personID;

    private User[] users;

    /**
     * @param username: Unique username for user
     * @param password: User’s password
     * @param email: User’s email address
     * @param firstName: User’s first name
     * @param lastName: User’s last name
     * @param gender: User’s gender
     * @param personID: Unique Person ID assigned to this user’s generated Person
     */
    public User(String username, String password, String email, String firstName,
                String lastName, String gender, String personID) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.personID = personID;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users=users;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPersonID() {
        return personID;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(gender, user.gender) && Objects.equals(personID, user.personID);
    }
}
