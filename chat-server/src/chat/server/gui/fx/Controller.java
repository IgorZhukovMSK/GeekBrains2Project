package chat.server.gui.fx;

import chat.server.core.ChatServer;
import chat.server.core.ChatServerListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.awt.*;

public class Controller implements ChatServerListener {

    private ChatServer server = new ChatServer(this);

    @FXML
    public TextArea talog;

    public void start(ActionEvent actionEvent) {
        server.start(8189);
    }

    public void stop(ActionEvent actionEvent) {
        server.stop();
    }

    public void drop(ActionEvent actionEvent) {
        server.dropAllClients();
    }

    @Override
    public void onChatServerMessage(String msg) {
        talog.append(msg + System.lineSeparator());

    }
}
