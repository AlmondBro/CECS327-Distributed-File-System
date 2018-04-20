import java.rmi.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.nio.file.*;
import com.google.gson.*;
import com.google.gson.Gson;

/*  Questions:
        1. Do we use the DFS class inside the metadata, metafile, and class pages that we create?
            or do we use the Metadata, metafile, and class pages inside the DFS class?
        2. How do we get the GUID? we know it is by doing the md5 checksum on the file
        3.      
    
    */
public class Client
{
    private int port;
    private DFS distributedFileSystem;
    public Client(int port) throws Exception {
            //Use InputStream classes instead 

            // call printDFS() 

            /* 
                1. Run 3 clients
                2. Join them to the network
             */
        //distributedFileSystem.join(Ip, port);

        distributedFileSystem = new DFS(port);
        UserInterface userInterface = new UserInterface(distributedFileSystem);

        userInterface.welcomeMessage();
        //userInterface.connectToDFS();
        userInterface.makingSelection();
    }

    public void hello() {
        System.out.println("Hello");
    }

    //Whenever a client is instantiated, it'll run an instance of the client? Create three clients, and join two of them (overheard)
    public static void main(String args[]) throws Exception
    {
        if (args.length < 1 ) {
            throw new IllegalArgumentException("Please supply a port parameter: <port>");
        }
        /* 
            To compile, run:
                javac -cp gson-2.8.2.jar Client.java Chord.java ChordMessageInterface.java DFS.java Metadata.java MetaFile.java Page.java UserInterface.java FileStream.java; java -classpath ".:gson-2.8.2.jar" Client 3000
        */
        Client client = new Client( Integer.parseInt(args[0]));
        //hello();
        System.exit(0);
        //Client client2 = new Client(Integer.parseInt(args[1]));
     } 
}
