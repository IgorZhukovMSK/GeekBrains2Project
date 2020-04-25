package chat.server.gui;

import chat.server.core.ChatServer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
    private static final int POS_X = 1000;
    private static final int POS_Y = 550;
    private static final int WIDTH = 200;
    private static final int HEIGHT = 100;
    private final ChatServer server;
    private final JButton buttonStart = new JButton("Start");
    private final JButton buttonStop = new JButton("Stop");

    ServerGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        setLayout(new GridLayout(1, 2));
        buttonStart.addActionListener(this);
        buttonStop.addActionListener(this);

        add(buttonStart);
        add(buttonStop);
        server = new ChatServer();
        setVisible(true);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerGUI();
            }
        });
        // throw new RuntimeException("Main died!!!!");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == buttonStart) {
            server.start(8189);
        } else if (src == buttonStop) {
            server.stop();
//            throw new RuntimeException("Hello from EDT!!");
        } else {
            throw new RuntimeException("Unknown sourse: " + src);
        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = String.format("Exception in \"%s\" %s: %s\n\t at %s", t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        JOptionPane.showMessageDialog(this, msg, "Exceptin", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
