package client;

import network.SocketThread;
import network.SocketThreadListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler, SocketThreadListener {

    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    private final JTextArea log = new JTextArea();
    private final JPanel panelTop = new JPanel(new GridLayout(2, 3));
    private final JTextField tfIPAdress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8189");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Always on top");
    private final JTextField tfLogin = new JTextField("Igor");
    private final JPasswordField tfPassword = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel panelBottom = new JPanel(new BorderLayout());
    private final JButton btnDisconnect = new JButton("<html><b>Disconnect</b></html>");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList<>();
    private boolean shownIoErrors = false;
    private SocketThread socketThread;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    ClientGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(WIDTH, HEIGHT);
        setAlwaysOnTop(true);
        userList.setListData(new String[]{"user1", "user2", "user3", "user4", "user5",
                "user6", "user7", "user8", "user9", "user0",});

        JScrollPane scrlUser = new JScrollPane(userList);
        JScrollPane scrlPane = new JScrollPane(log);
        scrlUser.setPreferredSize(new Dimension(100, 0));
        log.setLineWrap(true);
        log.setWrapStyleWord(true);
        log.setEditable(false);
        cbAlwaysOnTop.addActionListener(this);
        tfMessage.addActionListener(this);
        btnSend.addActionListener(this);
        btnLogin.addActionListener(this);
        btnDisconnect.addActionListener(this);

        panelTop.add(tfIPAdress);
        panelTop.add(tfPort);
        panelTop.add(cbAlwaysOnTop);
        panelTop.add(tfLogin);
        panelTop.add(tfPassword);
        panelTop.add(btnLogin);
        panelBottom.add(btnDisconnect, BorderLayout.WEST);
        panelBottom.add(tfMessage, BorderLayout.CENTER);
        panelBottom.add(btnSend, BorderLayout.EAST);

        add(scrlUser, BorderLayout.EAST);
        add(scrlPane, BorderLayout.CENTER);
        add(panelTop, BorderLayout.NORTH);
        add(panelBottom, BorderLayout.SOUTH);

        setVisible(true);
        panelBottom.setVisible(false);

//        List<String> logList = new ArrayList<>();
//
//        btnSend.addActionListener(new ActionListener() {
//
//            public void actionPerformed(ActionEvent e) {
//
//                log.append(tfMessage.getText() + "\n");
//                logList.add(tfMessage.getText());
//                log.grabFocus();
//                getRootPane().setDefaultButton(btnSend); // кнопка ентер по умолчанию "btnSend"
//
//            }
//        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        } else if (src == btnSend || src == tfMessage) {
            sendMessage();
        } else if (src == btnLogin) {
            connect();
            panelTop.setVisible(false);
            panelBottom.setVisible(true);

        }else if (src == btnDisconnect){
            disconnect();
            panelTop.setVisible(true);
            panelBottom.setVisible(false);

        } else {
            throw new RuntimeException("Unknown sourse: " + src);
        }
    }

    private void connect() {
        try {
            Socket socket = new Socket(tfIPAdress.getText(), Integer.parseInt(tfPort.getText()));
            socketThread = new SocketThread(this, "Client", socket);
                   } catch (IOException e) {
            showException(Thread.currentThread(), e);
        }
    }

    private void disconnect(){                              //Что то не понимаю как сделать, что бы сервер услышал, что мы отключились
            socketThread.interrupt();
            System.out.println("Disconnect " + socketThread.getName() + " " + tfLogin.getText());
    }

    private void sendMessage() {
        String msg = tfMessage.getText();
        String username = tfLogin.getText();
        if ("".equals(msg)) return;
        tfMessage.setText(null);
        tfMessage.requestFocusInWindow();
        socketThread.sentMessage(msg);
//        putLog(String.format("%s: %s", username, msg));
//        wrtMsgToLogFile(msg, username);
    }

    private void wrtMsgToLogFile(String msg, String username) {
        try (FileWriter out = new FileWriter("log.txt", true)) {
            out.write(username + ": " + msg + System.lineSeparator());
            out.flush();
        } catch (IOException e) {
            if (!shownIoErrors) {
                shownIoErrors = true;
                showException(Thread.currentThread(), e);
            }
        }
    }

    private void putLog(String msg) {
        if ("".equals(msg)) return;
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                log.append(msg + System.lineSeparator());
                log.setCaretPosition(log.getDocument().getLength());
            }
        });
    }

    private void showException(Thread t, Throwable e) {
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        if (ste.length == 0)
            msg = "Empty StackTrace";
        else {
            msg = String.format("Exception in \"%s\" %s: %s\n\t at %s",
                    t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
            JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);

        }
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();
        showException(t, e);
        System.exit(1);

//        String msg;
//        StackTraceElement[] ste = e.getStackTrace();
//        msg = String.format("Exception in \"%s\" %s: %s\n\t at %s", t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
//        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
//        System.exit(1);
    }





    /**
     * Socket Thread Listener metods
     */

    @Override
    public void onSocketStart(SocketThread thread, Socket socket) {
        putLog("Start");
    }

    @Override
    public void onSocketStop(SocketThread thread) {
        putLog("Stop");
    }

    @Override
    public void onSocketReady(SocketThread thread, Socket socket) {
        putLog("Ready");
    }

    @Override
    public void onReceiveServer(SocketThread thread, Socket socket, String msg) {
        putLog(msg);
    }

    @Override
    public void onSocketException(SocketThread thread, Throwable throwable) {
        showException(thread, throwable);
    }
}


