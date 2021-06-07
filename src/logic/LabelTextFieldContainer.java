package logic;

import javax.swing.*;

public class LabelTextFieldContainer {
    JLabel label;
    JTextField textField;

    public LabelTextFieldContainer(JLabel label, JTextField textField) {
        this.label = label;
        this.textField = textField;
    }

    public JLabel getLabel() {
        return label;
    }

    public JTextField getTextField() {
        return textField;
    }

}
