import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.net.ServerSocket;
import java.io.PrintWriter;
import java.util.HashSet;
import java.net.Socket;

public class ChatServer {

    // Port to listen to
    private static final int PORT = 9001;

    // Contains unique names of users in the chat room. Used to avoid duplicates
    private static HashSet<String> names = new HashSet<String>();

    // PrintWriters of all users
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();

    // Main method
    public static void main(String[] args) throws Exception {
        System.out.println("HarambeChat - commencing...");
        ServerSocket listener = new ServerSocket(PORT);

        try {
            while (true) {
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

    private static class Handler extends Thread {
        private String name;
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        // Handler constructor
        public Handler(Socket socket) {
            this.socket = socket;
        }
    }
}