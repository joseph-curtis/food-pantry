package logic;

import data.Database;
import java.util.ArrayList;

/**
 * Class to save login state of current (staff) user logged in
 * @author Joseph Curtis
 * @version 2021.05.21
 */
public class Person {
    private Integer person_id;
    private String firstName;
    private String lastName;
    private String username;
    private String role;
    private String email;

    /**
     * Constructor for User logged in (staff member)
     * @param person_id
     * @param firstName
     * @param lastName
     * @param username
     * @param role
     * @param email
     */
    public Person(Integer person_id
            , String firstName
            , String lastName
            , String username
            , String role
            , String email) {
        this.person_id = person_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.role = role;
        this.email = email;
    }

    /**
     * Constructor for student subscribers
     * @param person_id
     * @param firstName
     * @param lastName
     * @param email
     */
    public Person (Integer person_id, String firstName, String lastName, String email) {
        this.person_id = person_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = null;
        this.role = "Student";
        this.email = email;
    }

    public static ArrayList<Person> getStudentList() {
        return Database.getStudentList();
    }

    /**
     * some logic to verify that the user is a legitimate staff member
     * authenticates username and password
     * @param username
     * @param password
     * @return the user (Person object) if user is staff member and authenticated
     */
    public static Person authenticateStaffUser(String username, String password) {
        return Database.login(username, password);
    }

    public Integer getID() {
        return person_id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getEmail() {
        return email;
    }
    public String toEmailString() {
        return firstName + ' ' + lastName + " <" + email + ">";
    }

    public String getRole() {
        return role;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null) return false;
        if (o.getClass() == this.getClass()) {
            Person p = (Person) o;
            if (!person_id.equals(p.getID())) return false;
            if (!firstName.equals(p.getFirstName())) return false;
            if (!lastName.equals(p.getLastName())) return false;
            if (!email.equals(p.getEmail())) return false;
            return true;
        }
        return false;
    }
}
