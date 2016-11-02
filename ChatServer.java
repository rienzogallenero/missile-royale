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
    
}