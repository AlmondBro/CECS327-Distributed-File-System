import java.util.Scanner;
import java.util.InputMismatchException;
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
        System.out.println("To quit, enter 8:\t");
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
                    this.getDFS().read(fileName, pageNum);
                    //user_input.close();
                    break;

                case 5:
                    System.out.println("Please enter the file name");
                    fileName = user_input.nextLine();
                    System.out.println("You enter the file name:" + fileName);
                    this.getDFS().tail(fileName);
                    //user_input.close();
                    break;

                case 6:
                    System.out.println("Please enter file name");
                    fileName =  user_input.nextLine();
                    //System.out.println("You enter the name:" + fileName);
                    System.out.println("You enter the local file path");
                    filePath = user_input.nextLine();
                    filePath = user_input.nextLine();
                    System.out.println("You entered the local file path");

                    this.getDFS().append(fileName, "test" );
                    break;
                       // don't remember what the second agrument is about for append
                //  System.out.println("Please enter the page number");
            //   int pageNum =  user_input.nextInt();
                // System.out.println("You enter the page number: "+ pageNum);
                //  dfs.append(fileName, pageNum);
                    //user_input.close();

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
                    System.out.println("\nExiting...see you later alligator!");
                    flag = false;
                    //user_input.close();
                    break;

                default:
                    break;
            } //ends switch statement
            //user_input.close();
            if (flag == false) {
                return;
            }
          /* try {
        
            } catch (InputMismatchException inputMismatch) {
                System.err.println("\nPlease enter a number for your selection choice.");
            }  */
            
        } //ends while (implement way to quit while)
  
    } //end makingSelection() method
  
} //end UserInterface() class