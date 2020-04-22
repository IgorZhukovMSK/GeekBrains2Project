package Level2.Lesson5_Chat.chat;

import Level2.Lesson5_Chat.network.ServerSocketThread;

public class ChatServer {

    ServerSocketThread server;

    public void start(int port) {
        if (server != null && server.isAlive())
            System.out.println("Alreading running");
        else
            server = new ServerSocketThread("Server", port);

    }

    public void stop() {
        if (server == null || !server.isAlive()) {
            System.out.println("Nothing to stop");
        } else {
            server.interrupt();
        }
    }
}
