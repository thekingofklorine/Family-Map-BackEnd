package request;

/**
 * creates a request to register
 */
public class RegisterRequest {
    /**
     * string of username
     */
    String username;
    /**
     * string of password
     */
    String password;
    /**
     * string of email
     */
    String email;
    /**
     * string of first name
     */
    String firstName;
    /**
     * string of last name
     */
    String lastName;
    /**
     * string of gender
     */
    String gender;

    /**
     *
     * @param username: Unique username for user
     * @param password: User’s password
     * @param email: User’s email address
     * @param firstName: User’s first name
     * @param lastName: User’s last name
     * @param gender: User’s gender
     */
    public RegisterRequest(String username, String password, String email, String firstName,
                           String lastName, String gender) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
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
}
