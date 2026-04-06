// Exam: 2255 GCIS 124, Mid Term Exam #3, Question 2
// Filename: KnockKnockServer.java (inside knockknock package)

package mte3.knockknock;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class KnockKnockServer {
    public static int PORT = 54322;

    public static void receiveAndSend(Scanner scanner,String message,PrintWriter writer,boolean concat) {
        
        String received = scanner.nextLine();
        System.out.println("Received: " + received);

        String toSend = concat ? received + " " + message : message;

        writer.println(toSend);
        writer.flush();
        System.out.println("Sent: " + toSend);

    } // receiveAndSend() method closed
    
    public static void main(String args[]) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket client = serverSocket.accept();
             Scanner scanner = new Scanner(client.getInputStream());
             PrintWriter writer = new PrintWriter(client.getOutputStream())) {

            // Step 1: Client says "Knock knock"
            receiveAndSend(scanner, "Who's there?", writer, false);

            // Step 2: Client says "<who>"
            receiveAndSend(scanner, "who?", writer, true);

            // Step 3: Client says punchline
            receiveAndSend(scanner, "", writer, false);

        }
        
    } // main() method closed
}





