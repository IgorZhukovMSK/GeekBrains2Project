package chat.server.gui.GUIFormServer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormServer {

    private JPanel ServerChat;
    private JButton startButton;
    private JButton stopButton;
    private JButton dropAllButton;


    public FormServer() {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        dropAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public JPanel getServerChat() {
        return ServerChat;
    }
}
