package logic;

import data.Database;
import java.util.ArrayList;

/*
 * Class to save login state of current (staff) user logged in
 */
public class Person {
    private Integer person_id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(Integer person_id, String firstName, String lastName, String email) {
        this.person_id = person_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static ArrayList<Person> getStudentList() {
        return Database.getStudentList();
    }

    /*
    authenticate username and password
    @param username
    @param password
    @return the user (Person object) if user is staff member and authenticated
     */
    public static Person authenticateStaffUser(String username, String password) {
        return Database.authenticateStaffUser(username, password);
    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Integer getID() {
        return person_id;
    }
    public String getEmail() {
        return email;
    }
    public String toEmailString() {
        return firstName + ' ' + lastName + " <" + email + ">";
    }

}
