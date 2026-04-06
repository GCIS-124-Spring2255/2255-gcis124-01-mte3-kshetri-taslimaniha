// Exam: 2255 GCIS 124, Mid Term Exam #3, Question 3
// Filename: Client.java (inside knockknock2 package)

package mte3.knockknock2;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import mte3.knockknock2.Duplexer;

public class Client extends Duplexer implements Runnable {
    public Client(Socket socket) throws IOException {    super(socket);    }

    @Override
    public void run() {
        receive();                // question
        send("Who's there?");    // answer
        String setup = receive();   // setup
        send(setup + " who?");     // response
        receive();                // punchline
        close();
    } // run() method closed 

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of clients: ");
        int number = scanner.nextInt();
        scanner.close();
        
        String host = "localhost";
        for(int i=0; i<number; i++) {
            Client client = new Client(new Socket(host, Server.PORT));
            Thread thread = new Thread(client);
            thread.start();   
        }
        // Hint: after done, please run Server first and then run Client
    
    } // main ( ) method closed
} // Client { } class closed

