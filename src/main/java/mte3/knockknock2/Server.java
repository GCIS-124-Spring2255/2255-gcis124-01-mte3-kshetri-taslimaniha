// Exam: 2255 GCIS 124, Mid Term Exam #3, Question 3
// Filename: Server.java (inside knockknock2 package) 

package mte3.knockknock2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

import mte3.knockknock2.Duplexer;

public class Server extends Duplexer implements Runnable {
    
    public static final int PORT = 54321;

    private static final Joke[] JOKES = {
        new Joke("Goliath", "Goliath down, you looketh tired!"),
        new Joke("Broccoli", "Broccoli doesn't have a last name, silly."),
        new Joke("Wooden shoe", "Wooden shoe like to hear another joke?"),
        new Joke("Amish", "Really?  You don't look like a shoe!"),
        new Joke("Boo", "Why are you crying?"),
        new Joke("Atch", "Bless you!"),
        new Joke("A little old lady", "I didn't know you could yodel!"),
        new Joke("Cows go", "No silly, cows go MOO!"),
        new Joke("Harry", "Harry up and answer the door!"),
        new Joke("Cash", "No thanks, but I'll take a peanut if you have one!")
    };

    private static int JOKE_NUMBER = new Random().nextInt(JOKES.length);

    public Server(Socket client) throws IOException {    super(client);    }

    @Override
    public void run() {
        Joke joke;
        synchronized(JOKES) {  joke = JOKES[JOKE_NUMBER];  JOKE_NUMBER = (JOKE_NUMBER+1) % JOKES.length; }

        send("Knock, knock!");
        String answer = receive();
        boolean allGood = answer.equals("Who's there?");
        if(allGood) {
            send(joke.getSetup());
            String response = receive();
            allGood = joke.isResponseValid(response);
            if(allGood) {    send(joke.getPunchline());    } 
        }
        
        if(!allGood) {    send("What's the matter? Never heard of a knock, knock joke?");    }
        close();
    } // run() method closed

    public static void main(String[] args) throws IOException {
        try(ServerSocket server = new ServerSocket(PORT)) {
            while(true) {
                System.out.println("Waiting for clients...");
                Socket client = server.accept();
                System.out.println("Client connected!");
                Server handler = new Server(client);
                Thread thread = new Thread(handler);
                thread.start();
            }
        } // try { } block closed 
    } // main ( ) method closed
} // Server { } class closed