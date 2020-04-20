package Level2.Lesson5_Chat.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/*
1. Сообщение из "tfMessage" при нажатии кнопки "btnSend" (или Enter) должно выводиться в панель "log". // РЕШЕНО
    Вопрос - видимо поле "tfMessage" должно как то очищаться - обновляться после вывода в "log" // РЕШЕНО
    Вопрос - вывод нового сообщения должно быть с новой строки //РЕШЕНО
    Вопрос - разобраться, почему второй раз нельзя отправлять сообщение // РЕШЕНО
    Вопрос - почему "Enter" не всегда срабатывает в панеле "log"  // Потому, что в строке "log.setEnabled(false);" - было true //РЕШЕНО

2. Одновременно с п.1. должно все сохраняться в файл

    Вопрос - где должен быть расположен текстовый фал // РЕЩЕНО
    Вопрос - когда заполняется - Думаю сразу при создании окна должен буть типа аарйлиста стринг - сообщения добавляются как п 1. // РЕШЕНО
 */

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {

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
        log.setWrapStyleWord(false);
        log.setLineWrap(true);
        log.setEnabled(false);
        cbAlwaysOnTop.addActionListener(this);
        tfMessage.addActionListener(this);
        btnSend.addActionListener(this);

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

        List<String> logList = new ArrayList<>();

        btnSend.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                log.append(tfMessage.getText() + "\n");
                logList.add(tfMessage.getText());
                log.grabFocus();
                getRootPane().setDefaultButton(btnSend); // кнопка ентер по умолчанию "btnSend"

            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());

        } else if (src == btnSend || src == tfMessage) {
            sendMessage();
        } else {
            throw new RuntimeException("Unknown sourse: " + src);
        }
    }

    private void sendMessage() {
        String msg = tfMessage.getText();
        String username = tfLogin.getText();
        if ("".equals(msg)) return;
        tfMessage.setText(null);
        tfMessage.requestFocusInWindow();
        putLog(String.format("%s: %s", username, msg));
        wrtMsgToLogFile(msg, username);
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
        String msg;
        StackTraceElement[] ste = e.getStackTrace();
        msg = String.format("Exception in \"%s\" %s: %s\n\t at %s", t.getName(), e.getClass().getCanonicalName(), e.getMessage(), ste[0]);
        JOptionPane.showMessageDialog(this, msg, "Exception", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}

