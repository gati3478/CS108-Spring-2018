package seminar24.sockets.chat;

import java.io.*;
import java.net.Socket;

@SuppressWarnings("WeakerAccess")
public class ChatServerThread extends Thread {

    private ChatServer server;
    private Socket socket;
    private int ID;
    private BufferedReader streamIn = null;
    private PrintWriter streamOut = null;

    public ChatServerThread(ChatServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
        ID = this.socket.getPort() + this.socket.hashCode();
    }

    public void run() {
        System.out.println("Server Thread " + ID + " running.");
        while (true) {
            try {
                if (isInterrupted()) {
                    break;
                }

                server.handle(ID, streamIn.readLine());
            } catch (IOException ioe) {
                System.out.println(ID + " ERROR reading: " + ioe.getMessage());
                server.remove(ID);
                break;
            }
        }
    }

    public void send(String msg) {
        streamOut.println(msg);
    }

    public int getID() {
        return ID;
    }

    public void open() throws IOException {
        streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        streamOut = new PrintWriter(socket.getOutputStream(), true);
    }

    public void close() throws IOException {
        if (socket != null) {
            socket.close();
        }
        if (streamIn != null) {
            streamIn.close();
        }
        if (streamOut != null) {
            streamOut.close();
        }
    }

}
