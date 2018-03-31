import java.rmi.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.nio.file.*;

public class Client
{
    DFS dfs;
    public Client(int p) throws Exception {
        dfs = new DFS(p);
        
            // User interface:
            // join, ls, touch, delete, read, tail, head, append, move
            // call printDFS() 
            // ask user to give a name to the file

            /* 
                1. Run 3 clients
                2. Join them to the network
            
            */
    }

    //Whenever a client is instantiated, it'll run an instance of the client? Create three clients, and join two of them (overheard)
    static public void main(String args[]) throws Exception
    {
        if (args.length < 1 ) {
            throw new IllegalArgumentException("Parameter: <port>");
        }
        Client client=new Client( Integer.parseInt(args[0]));
        
     } 
}
