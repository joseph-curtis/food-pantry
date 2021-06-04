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
//    public Template(String name, String subject, String textBody) {
//        new Template(-1, name, subject, textBody);
//    }

    public static ArrayList<Template> getTemplatesList() {
        return Database.getAllTemplatesList();
    }

    public void saveTemplate() throws RuntimeException {
        Database.saveTemplate(name, subject, textBody);
    }

    public void updateTemplate() throws RuntimeException {
        Database.editTemplate(template_id, name, subject, textBody);
    }

    public static void saveTemplate(String tempName, String subj, String body) throws RuntimeException {
        Database.saveTemplate(tempName, subj, body);
    }

    public static void saveTemplate(int templateID, String tempName, String subj, String body)
            throws RuntimeException {
        Database.editTemplate(templateID, tempName, subj, body);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setTextBody(String body) {
        this.textBody = body;
    }
}
