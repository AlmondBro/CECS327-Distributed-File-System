import java.util.Scanner;
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
          System.out.println("Welcome");
          System.out.println("To join the file distributed system please enter yes: ");
    }

    public void connectToDFS() throws Exception {

        if (this.getUserSelectionValue() == 0) {
            System.out.println("Please enter an IP Address:\t");
            String IP_address = this.getScanner().nextLine();
            this.setIPAddress(IP_address);

            System.out.println("Please enter a port number");
            int port = this.getScanner().nextInt();
            this.setPort(port);

            System.out.println("Connecting you to the distributed file system.");
            this.getDFS().join(this.getIPAddress(), this.getPort());            
        }
    }

    public void getCommandLineInterface() {
        System.out.println("\nPlease make a selection");
        System.out.println("To ls (list the files) enter: 1:\t");
        System.out.println("To touch (create a file) enter 2:\t");
        System.out.println("To delete enter 3:\t");
        System.out.println("To read enter 4:\t");
        System.out.println("To tail enter 5:\t");
        System.out.println("To append enter 6:\t");
        System.out.println("To move enter 7:\t");
        System.out.println("To quit, enter 8:\t");
    } //end getCommandLineInterface()

    public void makingSelection() throws Exception {
        boolean flag = true;
        String fileName;
        while(flag) {
            this.getCommandLineInterface();
            int userChoice = this.getScanner().nextInt(); 
            setUserSelectionValue(userChoice);

            switch(this.getUserSelectionValue()) {
                case 1:
                    String fileList = this.getDFS().ls();
                    System.out.println("The list of files are "+ fileList);
                    break;
                    
                case 2:
                    System.out.println("Please enter the file name");
                    fileName = this.getScanner().nextLine();
                    System.out.println("You enter the file name:" + fileName);
                    this.getDFS().touch(fileName);
                    break;

                case 3:
                    System.out.println("Please enter the file name");
                    fileName = this.getScanner().nextLine();
                    
                    System.out.println("You entered the file name:" + fileName);
                    this.getDFS().delete(fileName);
                    break;

                case 4:
                    System.out.println("Please enter the file name");
                    fileName = this.getScanner().nextLine();

                    System.out.println("You entered the file name:" + fileName);
                    System.out.println("Please enter the page number");
                    
                    int pageNum = this.getScanner().nextInt();
                    System.out.println("You enter the page number: "+ pageNum);
                    this.getDFS().read(fileName, pageNum);
                    break;

                case 5:
                    System.out.println("Please enter the file name");
                    fileName = this.getScanner().nextLine();
                    System.out.println("You enter the file name:" + fileName);
                    this.getDFS().tail(fileName);
                    break;

                case 6:
                    System.out.println("Please enter the file name");
                    fileName =  this.getScanner().nextLine();
                    System.out.println("You enter the file name:" + fileName);
                // don't remember what the second agrument is about for append
                //  System.out.println("Please enter the page number");
            //   int pageNum =  this.getScanner().nextInt();
                // System.out.println("You enter the page number: "+ pageNum);
                //  dfs.append(fileName, pageNum);
                    break;

                case 7:
                    System.out.println("Please enter the file name");
                    fileName =  this.getScanner().nextLine();
                    System.out.println("You enter the file name:" + fileName);

                    System.out.println("Please enter the new file name");
                    String fileName2 = this.getScanner().nextLine();
                    System.out.println("You enter the new file name" + fileName2);
                    this.getDFS().mv(fileName, fileName2);
                    break;
                    
                case 8:
                    System.out.println("Exiting...see you later alligator!");
                    flag = false;
                    break;

                default:
                    break;
            } //ends switch statement
        } //ends while (implement way to quit while)
    } //end makingSelection() method
  
} //end UserInterface() class