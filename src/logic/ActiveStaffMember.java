package logic;

/*
 * Class to save login state of current (staff) user logged in
 *
 * 4.27.2021 hard coded placeholder values
 */
public class ActiveStaffMember {
    private int person_id;
    private String name;
    private String email;

    public ActiveStaffMember() {
        person_id = 1;
        name = "Panther Pantry";
        email = "teamcjklol@gmail.com";
    }

    public String getName() {
        return name;
    }
    public int getID() {
        return person_id;
    }
    public String getEmail() {
        return email;
    }
}
