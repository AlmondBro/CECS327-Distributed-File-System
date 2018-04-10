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
            System.out.println("Welcome");
            System.out.println("To join the file distributed system please enter: 0");

            Scanner userInput = new Scanner();
            int x;
            x = userInput.nextInt();
            if(x == 0)
            {
                    String IpAdress;
                    System.out.println("Please enter a Ip address")
                    IpAdress = userInput.nextLine();
                   
                    System.out.println("Please enter a port number");
                    int portNum;
                    portNum = userInput.nextInt();

                    //call join, assume it connects? 
                    dfs.join(IpAdress, portNum);

                    System.out.println("Connecting you to the file distributed system.")
                    String fileName;
                     //call join, assume it connects? 
                     while(true)
                     {
                        System.out.println("")
                        System.out.println("Please make a selection");
                        System.out.println("To ls enter: 1");
                        System.out.println("To touch enter: 2");
                        System.out.println("To delete enter: 3");
                        System.out.println("To read enter: 4");
                        System.out.println("To tail enter: 5");
                        System.out.println("To append enter: 6");
                        System.out.println("To move enter: 7");
                        System.out.println("")
                        x = userInput.nextInt();


                        switch(x)
                        {
                            case 1:
                                String fileList = dfts.ls();
                                System.out.println("The list of files are "+ fileList);
                                System.out.println("");
                                break;
                            case 2:
                                System.out.println("Please enter the file name");
                                fileName = userInput.nextLine();
                                System.out.println("You enter the file name:" + fileName);
                                dfs.touch(fileName);
                                System.out.println("");
                                break;
                            case 3:
                                System.out.println("Please enter the file name");
                                fileName = userInput.nextLine();
                                System.out.println("You enter the file name:" + fileName);
                                dfs.delete(fileName);
                                System.out.println("");
                                break;
                            case 4:
                                System.out.println("Please enter the file name");
                                fileName = userInput.nextLine();
                                System.out.println("You enter the file name:" + fileName);
                                System.out.println("Please enter the page number");
                                int pageNum = userInput.nextInt();
                                System.out.println("You enter the page number: "+ pageNum);
                                dfs.read(fileName, pageNum);
                                system.out.println(""); 
                                break;
                            case 5:
                                System.out.println("Please enter the file name");
                                fileName = userInput.nextLine();
                                System.out.println("You enter the file name:" + fileName);
                                dfs.tail(fileName);
                                break;
                            case 6:
                             System.out.println("Please enter the file name");
                             fileName = userInput.nextLine();
                             System.out.println("You enter the file name:" + fileName);
                         // don't remember what the second agrument is about for append
                         //  System.out.println("Please enter the page number");
                        //   int pageNum = userInput.nextInt();
                          // System.out.println("You enter the page number: "+ pageNum);
                         //  dfs.append(fileName, pageNum);
                             system.out.println(""); 
                             break;
                            case 7:
                              System.out.println("Please enter the file name");
                              fileName = userInput.nextLine();
                              System.out.println("You enter the file name:" + fileName);
                              System.out.println("");
                              System.out.println("Please enter the new file name");
                              String fileName2 = userInput.nextLine();
                              System.out.println("You enter the new file name" + fileName2);
                              dfs.mv(fileName, fileName2);
                              System.out.println("");
                              break;
                            default:
                              break;
                        } //ends switch statement
                    } //ends while (implement way to quit while)
                
            } else //ends if else
                 {
                   System.out.println("You did not wish to join the file distributed system");
                   System.out.println("Good bye");
                 }
            
            // call printDFS() 


            /* 
                1. Run 3 clients
                2. Join them to the network
             */
        //distributedFileSystem.join(Ip, port);

        distributedFileSystem = new DFS(port);
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
        else if (args.length < 2 ) {
            throw new IllegalArgumentException("Please two port numbers for two different clients.");
        }

        /* 
            To compile, run:
                javac -cp gson-2.8.2.jar Client.java Chord.java ChordMessageInterface.java DFS.java Metadata.java MetaFile.java Page.java UserInterface.java FileStream.java
        */
        Client client1 = new Client( Integer.parseInt(args[0]));
        Client client2 = new Client(Integer.parseInt(args[1]));
     } 
}
