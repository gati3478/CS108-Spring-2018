package seminar24.sockets.chat;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("WeakerAccess")
public class ChatClient implements Runnable {

    private Socket socket = null;
    private Thread thread = null;
    private BufferedReader console = null;
    private PrintWriter streamOut = null;
    private ChatClientThread client = null;

    private String serverName;
    private int serverPort;

    private static final String END_WORD = ".bye";

    public ChatClient(String serverName, int serverPort) {
        this.serverName = serverName;
        this.serverPort = serverPort;
    }

    public void start() {
        System.out.println("Establishing connection. Please wait ...");
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);

            console = new BufferedReader(new InputStreamReader(System.in));
            streamOut = new PrintWriter(socket.getOutputStream(), true);

            if (thread == null) {
                client = new ChatClientThread(this, socket);
                thread = new Thread(this);
                thread.start();
            }
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
    }

    public void run() {
        while (thread != null) {
            try {
                if (thread.isInterrupted()) {
                    break;
                }

                // readLine is deprecated and should be replaced by the BufferedReader
                // class usage, but let's leave it like that for the sake of consistency
                String message = console.readLine();
                streamOut.println(message);
            } catch (IOException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
                stop();
            }
        }
    }

    public void handle(String msg) {
        if (msg.equals(END_WORD)) {
            System.out.println("Good bye. Press RETURN to exit ...");
            stop();
        } else {
            System.out.println(msg);
        }
    }

    public void stop() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
        try {
            if (console != null) {
                console.close();
            }
            if (streamOut != null) {
                streamOut.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error closing ...");
        }
        client.close();
        client.interrupt();
    }

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Usage: java ChatClient host port");
        } else {
            new ChatClient(args[0], Integer.parseInt(args[1])).start();
        }
    }

}
