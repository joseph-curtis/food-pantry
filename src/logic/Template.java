package logic;

import data.Database;

import java.util.ArrayList;

/**
 * Class to save template data and push/pull to/from database
 * @author Joseph Curtis
 * @version 2021.05.28
 */
public class Template {
    private String name;
    private String subject;
    private String textBody;

    public Template(String name, String subject, String textBody) {
        this.name = name;
        this.subject = subject;
        this.textBody = textBody;
    }

    public static ArrayList<Template> getTemplatesList() {
        return Database.getAllTemplatesList();
    }
}
