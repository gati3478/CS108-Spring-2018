package seminar24.sockets.chat;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@SuppressWarnings("WeakerAccess")
public class ChatClientThread extends Thread {

    private Socket socket;
    private ChatClient client;
    private BufferedReader streamIn = null;

    public ChatClientThread(ChatClient client, Socket socket) {
        this.client = client;
        this.socket = socket;
        open();
        start();
    }

    public void open() {
        try {
            streamIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));;
        } catch (IOException ioe) {
            System.out.println("Error getting input stream: " + ioe);
            client.stop();
        }
    }

    public void close() {
        try {
            if (streamIn != null) {
                streamIn.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error closing input stream: " + ioe);
        }
    }

    public void run() {
        while (true) {
            if (isInterrupted()) {
                break;
            }
            try {
                String message = streamIn.readLine();
                client.handle(message);
            } catch (IOException ioe) {
                System.out.println("Listening error: " + ioe.getMessage());
                client.stop();
            }
        }
    }

}
