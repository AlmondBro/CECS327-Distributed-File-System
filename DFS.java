import java.rmi.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.math.BigInteger;
import java.security.*;
// import a json package
import com.google.gson.Gson;


/* JSON Format

 { //use method put in chord to send to the cloud 
    // use md5 of localfile for GUID for page 
    // delete pages then delete the file as well
    //page is an actually physical file in the cloud
    //use put() from Chord to upload to the network
    "metadata" :
    {
        file :
        {
            name  : "File1"
            numberOfPages : "3"
            pageSize : "1024" //dont worry about this
            size : "2291" 
            page :
            {
                number : "1" //dont worry about number, since JSON keeps track this.
                guid   : "22412"
                size   : "1024"
            }
            page :
            {
                number : "2"
                guid   : "46312"
                size   : "1024"
            }
            page :
            {
                number : "3"
                guid   : "93719"
                size   : "243"
            }
        }
    }
}
 */
public class DFS implements Serializable {
    int port;
    Chord chord;
    
    private long guid;
    
    private Gson gson;
    private String json;
    private FileStream filestream;
    private String listOfFiles;

    private File metafile_physicalFile;

    /**
     * Constructor for the DFS class. Takes
     * an integer as the port number.
     * @param port
     * @throws Exception
     */
    public DFS(int port) throws Exception
    {
        gson = new Gson();            
        
        this.port = port;
        long guid = md5("" + port);
        setGuid(guid);
        chord = new Chord(port, guid);
        Files.createDirectories(Paths.get(guid+"/repository/"));

    }
    public void setGuid(long Guid)
    {
        this.guid = Guid;
    }
    public long getGUID() {
        return this.guid;
    }

    public String getStringOfFiles() {
        return this.listOfFiles;
    }

    public void setStringOfFiles(String stringToConcatenate) {
        this.listOfFiles.concat(stringToConcatenate);
    }   

    /**
     * Runs the md5 algorithm on an object and
     * returns its guid
     * @param objectName is a String and the only
     * input
     * @return a guid in the form of a long
     */
    private long md5(String objectName) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(objectName.getBytes());
            BigInteger bigInt = new BigInteger(1,m.digest());
            return Math.abs(bigInt.longValue());
        } catch(NoSuchAlgorithmException e) {
                //e.printStackTrace();
                System.out.println("Caught error in md5() method");
        }
        return 0;
    } //end md5() method

    /** Method to join a process to a Chord network
     * @param ip IP address of the machine's network you wish to join
     * @param port of the network
     * @throws Exception 
     */
    public void join(String ip, int port) throws Exception {
        chord.joinRing(ip, port);
        //chord.Print();
    }
    


  public Metadata readMetaData() throws Exception, RemoteException {
    Metadata metadata = null;
      
      try {
       
        long guid = md5("Metadata"); 
        
        ChordMessageInterface peer = chord.locateSuccessor(guid);
         //locate the processor that has the meatadata

    

        FileStream metadataraw = peer.get(guid);  //locates the file  
        File peerFile = metadataraw.getFile(); //gets the file

        
        String fileName =  peer.getId() + "/repository/" + guid;

        System.out.println(fileName); //check the filepath for debugging
    
       
        File newFile = new File(fileName);
        FileOutputStream output = new FileOutputStream(peerFile);
 
        while (metadataraw.available() > 0)  {
            output.write(metadataraw.read());
        }   
        output.close();

 
        FileReader fileReader = new FileReader(new File(fileName));
         metadata =  this.getGsonObject().fromJson(fileReader, Metadata.class); //Reads metadata

    }catch(RemoteException e)
    {
    //   System.out.println("1"); debugging print
       return  metadata = new Metadata();
    
    }catch(FileNotFoundException e) {
      //  System.out.println("2"); debugging print
        return metadata = new Metadata();
    
    }
     
    
      return metadata;
  }

    public void writeMetaData(Metadata metadata) throws Exception {
        try  {
            long guid = md5("Metadata"); //which process has that file
            ChordMessageInterface process = chord.locateSuccessor(guid); //I know which node has the metadata
            
           //Following block is to write to localFile
          
            String tempFile = process.getId() + "/repository/"+guid;
            //System.out.println("\nHere is the file path:\t" + tempFile);
            File tempFile_file  = new File(tempFile);

           if(tempFile_file.exists()) {
             FileWriter writer = new FileWriter(tempFile_file);
             gson.toJson(metadata, writer);
             writer.close();
           } else {
             FileWriter writer = new FileWriter(tempFile_file);
             gson.toJson(metadata, writer);
             writer.close();
           }
        
           //chords put doesn't seem to put back into the cloud? how to fix
        
            process.put(guid, new FileStream(tempFile));
            System.out.println("\nUpdating metadata by calling the writeMetadata() method.");
        }  catch (FileNotFoundException e) {
            System.out.println("File: Does not exist (DFS.writeMedata");
            e.printStackTrace();;
        }
     
    }

    public Gson getGsonObject() {
        return this.gson;
    }
     /**
     * Creates a new file with the inputted name
     * into the metadata object.
     * @param fileName
     * @throws Exception
     */
    public void touch(String fileName) throws Exception {
         // TODO: Create the file fileName by adding a new entry to the Metadata
         // Write Metadata
        Metadata metadata = readMetaData(); //always read first when creating
        
        metadata.createFile(fileName);
        this.writeMetaData(metadata);
    }

     /**
     * Returns a list of the files in the
     * metadata in the form of a string.
     * @return
     * @throws Exception
     */
    public String ls() throws Exception {
        Metadata metadata = readMetaData(); //always read first when creating

       // TODO: returns all the files in the Metadata
        String listOfFiles = "";
    	listOfFiles = metadata.getFileNames();
        return listOfFiles;
       /*
        Metadata metadata = gson.fromJson(json, Metadata.class);
    	//listOfFiles = metadata.getFileNames();
        setStringOfFiles(metadata.getFileNames());
        return this.getStringOfFiles(); */
    }
    
     /**
     * Renames the metadata object.
     * @param oldName
     * @param newName
     * @throws Exception
     */
    public void mv(String oldName, String newName) throws Exception {
        Metadata metadata = readMetaData(); //always read first when creating
        metadata.changeName(oldName, newName);
        writeMetaData(metadata);
     }

    /**
     * Deletes a file and all of its pages
     * from the metadata.
     * @param fileName
     * @throws Exception
     */
    public void delete(String fileName) throws Exception {
        // TODO: remove all the pages in the entry fileName in the Metadata and then the entry
        // for each page in Metadata.filename
        //     peer = chord.locateSuccessor(page.guid);
        //     peer.delete(page.guid)
        // delete Metadata.filename
           Metadata metadata = readMetaData(); //always read first when creating
           metadata.delete(fileName);
           writeMetaData(metadata);
        // Write Metadata    	
    }
    
      /**
     * Returns a page from a file from the metadata
     * in the form of a FileStream.
     * @param fileName
     * @param pageNumber
     * @return
     * @throws Exception
     */
    public FileStream read(String fileName, int pageNumber) throws Exception, NullPointerException {
        // TODO: read pageNumber from fileName
        FileStream fileStream = null;
        try {
            Metadata metadata = readMetaData(); //always read first when creating
            Page page = metadata.getFile(fileName).getPage(pageNumber-1);
       //     System.out.println("Page number: " +page.getNumberofPage()); debugging prints
       //     System.out.println("Read the file's page " + page.getGUID()); debugging pints
            ChordMessageInterface peer = chord.locateSuccessor(page.getGUID());
            fileStream = peer.get(page.getGUID());
        } catch(NullPointerException e) {
            System.out.println("\nThe file or page you are trying to read does not exist or is null. Returning an empty filestream.");
            fileStream = new FileStream();
        }
       
        return fileStream;
    } //end read() method
    
    
     /**
     * Returns the last page of a file in the metadata
     * in the form of a Filestream.
     * @param fileName
     * @return Filestream
     * @throws Exception
     */
    public FileStream tail(String fileName) throws Exception, RemoteException, IOException, NullPointerException {
        FileStream tail = null;
        try {
            Metadata metadata = readMetaData(); //always read first when creating
        
            Page page = metadata.getFile(fileName).getLastPage();
            System.out.println("\nRead the file's page:\t" + page.getGUID());
            ChordMessageInterface peer = chord.locateSuccessor(page.getGUID());
            //return peer.get(page.getGUID());
            tail = peer.get(page.getGUID());
        }
        catch (NullPointerException e) {
            //TODO: Implement adding a page.
            System.out.println("\nNullPointerException. File does not exist -- creating file instead.");
            this.touch(fileName);

            //File localFile = new File(fileName);
            String localFile = "localFile";
            this.append(fileName, localFile);

            Metadata metadata = readMetaData(); //always read first when creating
        
            Page page = metadata.getFile(fileName).getLastPage();
            System.out.println("\nRead the file's page:\t" + page.getGUID());
            ChordMessageInterface peer = chord.locateSuccessor(page.getGUID());
            tail = peer.get(page.getGUID());
        }
        return tail;
    }

     /**
     * Returns the first page of the file in the
     * metadata.
     * @param fileName
     * @return
     * @throws Exception
     */
    public FileStream head(String fileName) throws Exception
    {
        return read(fileName, 1);
    }

     /**
     * Adds a new file to the end of the array of 
     * files in the metadata.
     * @param filename
     * @param filepath
     * @throws Exception
     */
    public void append(String filename, String localFile) throws Exception {
        Metadata metadata = readMetaData(); //always read first when creating
      
        File local_file  = new File(localFile);
        if (local_file.exists()) {
            System.out.println("\nAttempting to append file:\t "+ localFile);
            
            guid = md5(localFile);
            Page page = new Page(0, guid, 0);
            metadata.getFile(filename).addPage(page);

            ChordMessageInterface peer = chord.locateSuccessor(guid);
            peer.put(guid, new FileStream(localFile));
            writeMetaData(metadata);

        } else {
            System.out.println("\n" + localFile +" does not exist. Creating the file locally.");
            File newFile  = new File(localFile);
            newFile.createNewFile();

            guid = md5(localFile);

            Page page = new Page(0, guid, 0);
            metadata.getFile(filename).addPage(page);

            ChordMessageInterface peer = chord.locateSuccessor(guid);
            peer.put(guid, new FileStream(localFile));
            writeMetaData(metadata);
        }
    } //end append() method
} //end DFS.java class
