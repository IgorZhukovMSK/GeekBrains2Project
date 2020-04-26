package chat.server.core;

import network.ServerSocketThread;
import network.ServerSocketThreadListener;
import network.SocketThread;
import network.SocketThreadListener;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class ChatServer implements ServerSocketThreadListener, SocketThreadListener {

    ServerSocketThread server;
    ChatServerListener listener;
    Vector<SocketThread> clients = new Vector<>();

    public ChatServer (ChatServerListener listener){
        this.listener = listener;
    }

    public void start(int port) {
        if (server != null && server.isAlive())
            putLog("Alreading running");
        else
            server = new ServerSocketThread(this, "Server", port, 2000);

    }

    public void stop() {
        if (server == null || !server.isAlive()) {
            putLog("Nothing to stop");
        } else {
            server.interrupt();
        }
    }

    private void putLog(String msg) {
        listener.onChatServerMessage(msg);
    }




    /**
     * Server Socket Thread Listener metods
     **/

    @Override
    public void onServerStarted(ServerSocketThread thread) {
        putLog("Server thread started");
    }

    @Override
    public void onServerCreated(ServerSocketThread thread, ServerSocket server) {
        putLog("Server socket started");
    }

    @Override
    public void onServerTimeout(ServerSocketThread thread, ServerSocket server) {
    //    putLog("Sever timeout");
    }

    @Override
    public void onSocketAccepted(ServerSocketThread thread, ServerSocket server, Socket socket) {
        putLog("Server connected");
        String name = "socketThread" + socket.getInetAddress() + ": " + socket.getPort();
        new SocketThread(this, name, socket);
    }

    @Override
    public void onServerException(ServerSocketThread thread, Throwable throwable) {
        putLog("Server exception");
        throwable.printStackTrace();
    }

    @Override
    public void onServerStop(ServerSocketThread thread) {
        putLog("Server thread stopped");
    }

    /**
     * Socket Thread Listener metods
     **/


    @Override
    public void onSocketStart(SocketThread thread, Socket socket) {
        putLog("Socket started");
    }


    @Override
    public void onSocketStop(SocketThread thread) {
        putLog("Socket stopped");
        clients.remove(thread);
    }

    @Override
    public void onSocketReady(SocketThread thread, Socket socket) {
        putLog("Socket ready");
        clients.add(thread);
    }

    @Override
    public void onReceiveServer(SocketThread thread, Socket socket, String msg) {

        for (int i = 0; i < clients.size(); i++) {
            SocketThread client = clients.get(i);
            client.sentMessage("echo: " + msg);
            }
        }
    @Override
    public void onSocketException(SocketThread thread, Throwable throwable) {
        throwable.printStackTrace();
    }

    public void dropAllClients() {
    }
}
