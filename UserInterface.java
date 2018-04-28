import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.nio.file.Path;
public class UserInterface {

    private Scanner userInput;
    private int userSelectionValue;
    private String IP_Address;
    private int port;
    private DFS distributedFileSystem;

    public UserInterface(DFS distributedFileSystem) {
        userInput = new Scanner(System.in);
        this.distributedFileSystem = distributedFileSystem;
    } //end UserInterface() constructor

    public Scanner getScanner() {
        return this.userInput;
    }

    public int getUserSelectionValue() {
        return this.userSelectionValue;
    }

    public String getIPAddress() {
        return this.IP_Address;
    }

    public int getPort() {
        return this.port;
    }

    public DFS getDFS() {
        return this.distributedFileSystem;
    }

    public void setIPAddress(String newIPAddress) {
       this.IP_Address = newIPAddress; 
    }

    public void setPort(int newPort) {
        this.port = newPort;
    }

    public void setUserSelectionValue(int newUserSelectionValue) {
        this.userSelectionValue = newUserSelectionValue;
    }

    public void welcomeMessage() {
          //Use InputStream classes instead 
          System.out.println("\nWelcome");
          //System.out.println("To join the distributed file system.");
    }

    public void connectToDFS() throws InputMismatchException, Exception {
        Scanner user_input = new Scanner(System.in);

        if (this.getUserSelectionValue() == 0) {
            System.out.println("\nPlease enter an IP Address:\t");
            String IP_address = user_input.nextLine();
            this.setIPAddress(IP_address);

            System.out.println("Please enter a port number");
            int port = user_input.nextInt();
            this.setPort(port);

            System.out.println("Connecting you to the distributed file system.");
            this.getDFS().join(this.getIPAddress(), this.getPort());            
        } //end if-statement
    } //end 

    public void getCommandLineInterface() {
        System.out.println("\nPlease make a selection");
        System.out.println("\nTo join the DFS, enter 0:\t");
        System.out.println("To ls (list the files) enter 1:\t");
        System.out.println("To touch (create a file) enter 2:\t");
        System.out.println("To delete enter 3:\t");
        System.out.println("To read enter 4:\t");
        System.out.println("To tail enter 5:\t");
        System.out.println("To append enter 6:\t");
        System.out.println("To move enter 7:\t");
        System.out.println("To head, enter 8:\t");
        System.out.println("To quit, enter 9:\t");
    } //end getCommandLineInterface()

    public void makingSelection() throws Exception {
        Scanner user_input = new Scanner(System.in);
        boolean flag = true;
        String filePath;
        String fileName;

        while(flag = true) {
            /* try {

            } catch (NumberFormatException e) {

            } */
            this.getCommandLineInterface();

            int userChoice = Integer.parseInt(user_input.nextLine()); 
            setUserSelectionValue(userChoice);

            switch(this.getUserSelectionValue()) {
                case 0:
                    this.connectToDFS();
                case 1:
                    String fileList = getDFS().ls();
                    System.out.println("The list of files are "+ fileList);
                    //user_input.close();
                    break;
                    
                case 2:
                    System.out.println("Please enter the file name:\t");
                    fileName = user_input.nextLine();
                    System.out.println("You entered the file name:" + fileName);
                    this.getDFS().touch(fileName);
                    //user_input.close();
                    break;

                case 3:
                    System.out.println("Please enter the file name");
                    fileName = user_input.nextLine();
                    
                    System.out.println("You entered the file name:" + fileName);
                    this.getDFS().delete(fileName);
                    //user_input.close();
                    break;

                case 4: 
                    System.out.println("Please enter the file name");
                    fileName = user_input.nextLine();

                    System.out.println("You entered the file name:" + fileName);
                    System.out.println("Please enter the page number");
                    
                    int pageNum = user_input.nextInt();
                    System.out.println("You enter the page number: "+ pageNum);
                    FileStream in = this.getDFS().read(fileName, pageNum);
                    File File = in.getFile();   
                    File.getAbsolutePath(); 
                    System.out.println("Files path: " + File.getAbsolutePath());
                    String text = "" +      File.getAbsolutePath(); 
                    Path path = Paths.get(text);       
                    byte[] bytes = Files.readAllBytes(path);
                    String  text2 = new String(bytes, StandardCharsets.UTF_8);
                    System.out.println("Files contents: " + text2);
                      
                      in.close();
                      break;

                case 5:
                    System.out.println("Please enter the file name");
                    fileName = user_input.nextLine();
                    System.out.println("You enter the file name:" + fileName);
                    FileStream in2 = this.getDFS().tail(fileName);
                    File File2 = in2.getFile();   
                    File2.getAbsolutePath(); 
                    System.out.println("Files path: " + File2.getAbsolutePath());
                    String text3 = "" +      File2.getAbsolutePath(); 
                    Path path2 = Paths.get(text3);       
                    byte[] bytes2 = Files.readAllBytes(path2);
                    String  text4 = new String(bytes2, StandardCharsets.UTF_8);
                    System.out.println("Files contents: " + text4);
                    in2.close();
                 
                    break;

                case 6:
                    System.out.println("Please enter file name");
                    fileName =  user_input.nextLine();
                    System.out.println("You enter the name:" + fileName);
                    System.out.println("You enter the local file path");
                    filePath = user_input.nextLine();
                    System.out.println("You entered the local file path:"+ filePath);
                    this.getDFS().append(fileName, filePath );
                    break;
          
                case 7:
                    System.out.println("Please enter the file name");
                    fileName =  user_input.nextLine();
                    System.out.println("You enter the file name:" + fileName);
                    System.out.println("Please enter the new file name");
                    String fileName2 = user_input.nextLine();
                    System.out.println("You enter the new file name" + fileName2);
                    this.getDFS().mv(fileName, fileName2);
                    //user_input.close();
                    break;
                case 8:  
                    System.out.println("Please enter the file name");
                    fileName = user_input.nextLine();
                    System.out.println("You enter the file name:" + fileName);
                    FileStream in3 = this.getDFS().head(fileName);
                    File File3 = in3.getFile();   
                    File3.getAbsolutePath(); 
                    System.out.println("Files path: " + File3.getAbsolutePath());
                    String text5 = "" +      File3.getAbsolutePath(); 
                    Path path3 = Paths.get(text5);       
                    byte[] bytes3 = Files.readAllBytes(path3);
                    String  text6 = new String(bytes3, StandardCharsets.UTF_8);
                    System.out.println("Files contents: " + text6);
                    in3.close();
                break;
                
                case 9:
                    System.out.println("\nExiting...see you later alligator!");
                    flag = false;
                    break;

                default:
                    break;
            } //ends switch statement

            if (flag == false) {
                return;
            }
            
        } //ends while (implement way to quit while)
  
    } //end makingSelection() method
  
} //end UserInterface() class