import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Insets;

public class ChatClient {

    BufferedReader in;
    PrintWriter out;
    JFrame frame = new JFrame("GitsOutForHarambe");
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(20, 40);

    public ChatClient() {

        // Graphical User Interface
        textField.setEditable(false);
        textField.setBorder(BorderFactory.createCompoundBorder(textField.getBorder(), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        messageArea.setEditable(false);
        messageArea.setMargin(new Insets(10,10,10,10));
        messageArea.append("Welcome to Harambe Chat! Say hi! #GitsOut\n\n");
        frame.getContentPane().add(textField, "South");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        // New listeners
        textField.addActionListener(new ActionListener() {
            // Submit on enter
            public void actionPerformed(ActionEvent e) {
                out.println(textField.getText());
                textField.setText("");
            }
        });
    }
	
	// Ask for server's IP address
    private String getServerAddress() {
        return JOptionPane.showInputDialog(
            frame,
            "Please ChatServer's IP address:",
            "Welcome to Game Chat",
            JOptionPane.QUESTION_MESSAGE);
    }

    // Prompt username
    private String getName() {
        return JOptionPane.showInputDialog(
            frame,
            "Choose a username:",
            "Username selection",
            JOptionPane.PLAIN_MESSAGE);
    }

    private void run() throws IOException {

        String serverAddress = getServerAddress();
        Socket socket = new Socket(serverAddress, 9001);

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        while (true) {

            String line = in.readLine();

            if (line.startsWith("SUBMITNAME")) {
                out.println(getName());
            } else if (line.startsWith("NAMEACCEPTED")) {
                textField.setEditable(true);
            } else if (line.startsWith("MESSAGE")) {
                messageArea.append(line.substring(8) + "\n");
            }

        }
    }
}