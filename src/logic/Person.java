package logic;

import data.Database;
import java.util.ArrayList;

/*
 * Class to save login state of current (staff) user logged in
 *
 * 4.27.2021 hard coded placeholder values
 */
public class Person {
    private int person_id;
    private String firstName;
    private String lastName;
    private String email;

    public Person(int person_id, String firstName, String lastName, String email) {
        this.person_id = person_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public static ArrayList<Person> getStudentList() {
        return Database.getStudentList();
    }

    public static Person getCurrentUser(String username) {
        return Database.getCurrentUser(username);
    }

    public String getFirstName() { return firstName; }
    public String getLastName() {
        return lastName;
    }
    public int getID() {
        return person_id;
    }
    public String getEmail() {
        return email;
    }
    public String toEmailString() {
        return firstName + ' ' + lastName + " <" + email + ">";
    }

}
