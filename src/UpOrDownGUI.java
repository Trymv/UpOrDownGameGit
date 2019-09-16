import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpOrDownGUI {
    private JButton startButton;
    private JButton settingsButton;
    private JButton addPlayerButton;
    private JTextField playerToAdd;

    public UpOrDownGUI() {

        //Start
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Settings
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        //Add player
        addPlayerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textFieldValue = playerToAdd.getText();
            }
        });
    }
}
