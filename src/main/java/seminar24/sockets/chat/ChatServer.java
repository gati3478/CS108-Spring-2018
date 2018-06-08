package seminar24.sockets.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("WeakerAccess")
public class ChatServer implements Runnable {

    private Map<Integer, ChatServerThread> clients = new HashMap<>();
    private ServerSocket server = null;
    private Thread thread = null;

    @SuppressWarnings("FieldCanBeLocal")
    private int maxClients = 32;
    private int clientCount = 0;
    private int port;

    private static final String END_WORD = ".bye";

    public ChatServer(int port) {
        this.port = port;
    }

    public void start() {
        try {
            System.out.println("Binding to port " + port + ", please wait  ...");
            server = new ServerSocket(port);

            System.out.println("Server started: " + server);
            if (thread == null) {
                thread = new Thread(this);
                thread.start();
            }
        } catch (IOException ioe) {
            System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
        }
    }

    public void run() {
        while (thread != null) {
            try {
                System.out.println("Waiting for a client ...");
                if (thread.isInterrupted()) {
                    break;
                }
                createHandler(server.accept());
            } catch (IOException ioe) {
                System.out.println("Server accept error: " + ioe);
                stop();
            }
        }
    }

    public void stop() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }

    private void createHandler(Socket socket) {
        if (clientCount < maxClients) {
            System.out.println("Client accepted: " + socket);
            ChatServerThread newClientHandler = new ChatServerThread(this, socket);
            clients.put(newClientHandler.getID(), newClientHandler);
            try {
                newClientHandler.open();
                newClientHandler.start();
                ++clientCount;
            } catch (IOException ioe) {
                System.out.println("Error opening thread: " + ioe);
            }
        } else {
            System.out.println("Client refused: maximum " + maxClients + " reached.");
        }
    }

    public synchronized void handle(int ID, String input) {
        if (input.equals(END_WORD)) {
            // say goodbye to the client
            clients.get(ID).send(END_WORD);
            remove(ID);
        } else {
            for (int clientId : clients.keySet()) {
                clients.get(clientId).send(ID + ": " + input);
            }
        }
    }

    public synchronized void remove(int ID) {
        ChatServerThread toTerminate = clients.get(ID);
        if (toTerminate != null) {
            System.out.println("Removing client thread " + ID);
            clients.remove(ID);
            --clientCount;
            try {
                toTerminate.close();
            } catch (IOException ioe) {
                System.out.println("Error closing thread: " + ioe);
            }
            toTerminate.interrupt();
        }
    }

    public static void main(String args[]) {
        if (args.length != 1) {
            System.out.println("Usage: java ChatServer port");
        } else {
            new ChatServer(Integer.parseInt(args[0])).start();
        }
    }

}
