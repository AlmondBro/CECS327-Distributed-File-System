import java.rmi.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.nio.file.*;
import com.google.gson.*;
import java.util.Random;

public class Client
{
    private int port;
    private DFS distributedFileSystem;
    private UserInterface userInterface;

    public Client(int port) throws Exception {
            //Use InputStream classes instead 

            // call printDFS() 

            /* 
                1. Run 3 clients
                2. Join them to the network
             */
        //distributedFileSystem.join(Ip, port);
        this.port = port;
        distributedFileSystem = new DFS(this.getClientPort());
        userInterface = new UserInterface(distributedFileSystem);
    }

    public int getClientPort() {
        return this.port;
    }

    public UserInterface getUserInterface() {
        return this.userInterface;
    }

    public static void main(String args[]) throws Exception {
    /* To compile and run:
        javac -cp gson-2.8.2.jar Client.java Chord.java ChordMessageInterface.java DFS.java Metadata.java MetaFile.java Page.java UserInterface.java FileStream.java; java -classpath ".:gson-2.8.2.jar" Client 3000
    */

        Random random = new Random();
        int randomPort = random.nextInt(5000) + 1026;

        try {
            Client client = new Client(randomPort);
            
            client.getUserInterface().welcomeMessage();
            client.getUserInterface().makingSelection();

            System.exit(0);
        } catch(IllegalArgumentException e) {
            System.out.println("\nPlease supply a port parameter: <port> upon next compilation and run. Defaulting to port #:\t" + randomPort);
            Client client = new Client(randomPort);
            
            client.getUserInterface().welcomeMessage();
            client.getUserInterface().makingSelection();

            System.exit(0);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("\nPlease supply a port parameter: <port> upon next compilation and run. Defaulting to port #:\t" + randomPort);
            Client client = new Client(randomPort);
            
            client.getUserInterface().welcomeMessage();
            client.getUserInterface().makingSelection();

            System.exit(0);
        } //end catch() block
     } //end main() method
} //end Client class()

