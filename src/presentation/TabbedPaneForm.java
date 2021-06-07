package presentation;

import javax.swing.*;

public class TabbedPaneForm implements GUIForm {
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
