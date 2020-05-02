package chat.server.gui.GUIFormServer;

import javax.swing.*;

public class ServerForm extends FormServer {

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        FormServer formServer = new FormServer();



        frame.setContentPane(formServer.getServerChat());
        frame.setTitle("ServerChat");
        frame.setSize(800,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);


    }
}
