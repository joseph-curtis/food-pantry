package logic;

public class Person {
    private String firstName;
    private String lastName;
    private String userName;
    private String passwordHash;
    private String email;
    private String role;

    public Person(String firstName, String lastName, String userName, String passwordHash, String email, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public boolean isManager(){
        return role.equals("manager");
    }
}
