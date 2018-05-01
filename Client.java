import java.rmi.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.nio.file.*;
import com.google.gson.*;
import com.google.gson.Gson;

public class Client {
    private int port;
    private DFS distributedFileSystem;
    public Client(int port) throws Exception {

        distributedFileSystem = new DFS(port);
        UserInterface userInterface = new UserInterface(distributedFileSystem);

        userInterface.welcomeMessage();
        userInterface.makingSelection();
    }

    //Whenever a client is instantiated, it'll run an instance of the client? Create three clients, and join two of them (overheard)
    public static void main(String args[]) throws Exception {
        if (args.length < 1 ) {
            throw new IllegalArgumentException("Please supply a port parameter: <port>");
        }
        /* 
            To compile, run:
                javac -cp gson-2.8.2.jar Client.java Chord.java ChordMessageInterface.java DFS.java Metadata.java MetaFile.java Page.java UserInterface.java FileStream.java; java -classpath ".:gson-2.8.2.jar" Client 3000
        */
        Client client = new Client( Integer.parseInt(args[0]));
        System.exit(0);
        //Client client2 = new Client(Integer.parseInt(args[1]));
     } 
}
