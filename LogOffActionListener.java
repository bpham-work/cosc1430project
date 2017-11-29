import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOffActionListener implements ActionListener {
    private JPanel parentPanel;
    private CardLayout parentLayout;

    public LogOffActionListener(JPanel parentPanel, CardLayout parentLayout) {
        this.parentPanel = parentPanel;
        this.parentLayout = parentLayout;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        parentLayout.show(parentPanel, LayoutNameConstants.USER_SELECT);
    }
}
