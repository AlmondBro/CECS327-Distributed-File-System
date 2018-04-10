import java.rmi.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import java.nio.file.*;

public class Client
{
    /*  Questions:
        1. Do we use the DFS class inside the metadata, metafile, and class pages that we create?
            or do we use the Metadata, metafile, and class pages inside the DFS class?
        2. How do we get the GUID? we know it is by doing the md5 checksum on the file
        3.      
    
    */
    DFS dfs;
    public Client(int p) throws Exception {
        dfs = new DFS(p);
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
