import java.rmi.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.nio.file.*;

/*  Questions:
        1. Do we use the DFS class inside the metadata, metafile, and class pages that we create?
            or do we use the Metadata, metafile, and class pages inside the DFS class?
        2. How do we get the GUID? we know it is by doing the md5 checksum on the file
        3.      
    
    */
public class Client
{
    DFS distributedFileSystem;
    public Client(int p) throws Exception {
            //Use InputStream classes instead 
            // User interface:
            // join, ls, touch, delete, read, tail, head, append, move
            // call printDFS() 
            // ask user to give a name to the file

            /* 
                1. Run 3 clients
                2. Join them to the network
             */
        //distributedFileSystem.join(Ip, port);

        distributedFileSystem = new DFS(p);
        UserInterface userInterface = new UserInterface(distributedFileSystem);

        userInterface.welcomeMessage();
        userInterface.connectToDFS();
        userInterface.makingSelection();
    }

    //Whenever a client is instantiated, it'll run an instance of the client? Create three clients, and join two of them (overheard)
    static public void main(String args[]) throws Exception
    {
        if (args.length < 1 ) {
            throw new IllegalArgumentException("Please supply a port parameter: <port>");
        }
        Client client = new Client( Integer.parseInt(args[0]));
        
     } 
}
