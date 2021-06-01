package logic;

import data.Database;

import java.util.ArrayList;

/**
 * Class to save template data and push/pull to/from database
 * @author Joseph Curtis
 * @version 2021.05.28
 */
public class Template {
    private int template_id;
    private String name;
    private String subject;
    private String textBody;

    public Template(int template_id, String name, String subject, String textBody) {
        this.template_id = template_id;
        this.name = name;
        this.subject = subject;
        this.textBody = textBody;
    }
    public Template(String name, String subject, String textBody) {
        new Template(-1, name, subject, textBody);
    }

    public static ArrayList<Template> getTemplatesList() {
        return Database.getAllTemplatesList();
    }

    public int getTemplate_id() {
        return template_id;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public String getTextBody() {
        return textBody;
    }
}