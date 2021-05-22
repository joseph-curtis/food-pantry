package presentation;
// used as template:  https://github.com/PCC-CIS-234A/GraphTV/blob/master/src/main/Controller.java

import javax.swing.*;

public class TabbedPaneForm extends GUIForm {
    private JPanel rootPanel;
    private JTabbedPane tabbedPane;

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    @Override
    public JPanel getRootPanel() {
        return rootPanel;
    }
}
