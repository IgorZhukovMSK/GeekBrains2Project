package chat.server.gui;

import chat.server.core.ChatServer;
import chat.server.core.ChatServerListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler, ChatServerListener {
    private static final int POS_X = 1000;
    private static final int POS_Y = 550;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 500;

    private final ChatServer chatServer = new ChatServer(this);
    private final JButton buttonStart = new JButton("Start");
    private final JButton buttonStop = new JButton("Stop");
    private final JButton buttonDropAll = new JButton("Drop All");
    private final JPanel panelTop = new JPanel(new GridLayout(1, 3));
    private final JTextArea log = new JTextArea();

    ServerGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        log.setEditable(false);
        log.setLineWrap(true);
        JScrollPane scrollLog = new JScrollPane(log);
        buttonStart.addActionListener(this);
        buttonStop.addActionListener(this);

        panelTop.add(buttonStart);
        panelTop.add(buttonStop);
        panelTop.add(buttonDropAll);
        add(panelTop, BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);

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
            chatServer.start(8189);
        } else if (src == buttonStop) {
            chatServer.stop();
//            throw new RuntimeException("Hello from EDT!!");
        }else if (src == buttonDropAll){
            chatServer.dropAllClients();
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

    @Override
    public void onChatServerMessage(String msg) {
        SwingUtilities.invokeLater(() -> {
            log.append(msg + "\n");
            log.setCaretPosition(log.getDocument().getLength());
        });
    }
}
