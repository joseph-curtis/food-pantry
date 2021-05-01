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

    public ActiveStaffMember(int person_id, String name, String email) {
        this.person_id = person_id;
        this.name = name;
        this.email = email;
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
