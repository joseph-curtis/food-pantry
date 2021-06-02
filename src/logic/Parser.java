package logic;

import javax.swing.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    public static ArrayList<String> parseTags(String msgBody) {
        ArrayList<String> tags = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\{([^}]*?)\\}");
        Matcher matcher = pattern.matcher(msgBody);
        while(matcher.find()){
            if(!(tags.contains(matcher.group()))) {
                tags.add(matcher.group());
            }
        }
        return tags;
    }

    public static String tagFields(ArrayList<String> tags, String msgBody){
        ArrayList<LabelTextFieldContainer> fields = new ArrayList<>();
        JPanel tagPanel = new JPanel();
        for(String tag : tags){
            JLabel label = new JLabel(tag);
            JTextField textField = new JTextField();
            fields.add(new LabelTextFieldContainer(label, textField));
        }
        for(LabelTextFieldContainer field : fields){
            tagPanel.add(field.getLabel());
            tagPanel.add(field.textField);
        }
        int result = JOptionPane.showConfirmDialog(null, tagPanel,
                "Please Enter Text to Replace Tags", JOptionPane.OK_CANCEL_OPTION);
        if(result == JOptionPane.OK_OPTION){
            for(LabelTextFieldContainer field : fields){
                msgBody = msgBody.replace(field.getLabel().getText(), field.getTextField().getText());
            }
        }

        return msgBody;
    }
}
