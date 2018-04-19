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

public class DFS {
    int port;
    Chord chord;
    
    private long guid;
    private Gson gson;
    private String json;
    private FileStream filestream;
    //private Metadata metadata;
    private String listOfFiles;

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
        chord = new Chord(port, guid);
        Files.createDirectories(Paths.get("./"+guid+"/repository"));
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
            System.out.println("MD5:\t" + Math.abs(bigInt.longValue()));
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
        chord.Print();
    }
    
  /*  public JSonParser readMetaData() throws Exception
    {
        JsonParser jsonParser _ null;
        long guid = md5("Metadata"); 
        ChordMessageInterface peer = chord.locateSuccessor(guid);
        InputStream metadataraw = peer.get(guid);
        // jsonParser = Json.createParser(metadataraw);
        return jsonParser;
    }
    
    public void writeMetaData(InputStream stream) throws Exception {
        JsonParser jsonParser _ null;
        long guid = md5("Metadata");
        ChordMessageInterface peer = chord.locateSuccessor(guid);
        peer.put(guid, stream);
    }
   */

  public Metadata readMetaData() throws Exception, RemoteException {
    Metadata metadata = null;
      
      try {
        //Read -- do something and write  
        long guid = md5("Metadata"); //Locate metafile via GUID
        ChordMessageInterface peer = chord.locateSuccessor(guid);
        FileStream metadataraw = peer.get(guid);
        
        //String fileName = "./"+guid+"/metadata.tep"; 
         //+ guidObject; javac -cp gson-2.8.2.jar Client.java Chord.java ChordMessageInterface.java DFS.java Metadata.java MetaFile.java Page.java UserInterface.java FileStream.java; java -classpath ".:gson-2.8.2.jar" Client 3000
        String fileName = "./"+guid+"/metadata.tep"; //Not getting path from here
        System.out.println(fileName);
       
        FileOutputStream output = new FileOutputStream(fileName);
        
  
        while (metadataraw.available() > 0) 
            output.write(metadataraw.read());
        output.close();
  
        //FileReader filereader = new FileReader("jsonFile.json");
        FileReader fileReader = new FileReader(fileName);
         metadata =  this.getGsonObject().fromJson(fileReader, Metadata.class); //Reads metadata
  
      } catch(RemoteException e)  { 
         metadata = new Metadata();
      }
     
      // jsonParser = Json.createParser(metadataraw);
      //System.out.println("readMetaData()");
      return metadata;
  }

    public void writeMetaData(Metadata metadata) throws Exception {
        //metadata = readMetaData();
        Gson gson = new Gson();
        
       //Following block is to write to localFile
        System.out.println("GUID:\t" + guid);
        String tempFile =  "./"+guid+"/metadata.tep";
        Writer writer = new FileWriter(new File(tempFile));
        gson.toJson(metadata, writer);
        writer.close();
     
        long guid = md5("Metadata"); //which process has that file
        ChordMessageInterface process = chord.locateSuccessor(guid); //which process has that metadata
        process.put(guid, new FileStream(tempFile));
        System.out.println("File was succesfully posted to FileSystem.");
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
        //this.getGsonObject().toJson(metadata);
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
    public FileStream read(String fileName, int pageNumber) throws Exception {
        // TODO: read pageNumber from fileName
        Metadata metadata = readMetaData(); //always read first when creating
        Page page = metadata.getFile(fileName).getPage(pageNumber);
        
        ChordMessageInterface peer = chord.locateSuccessor(page.getGUID());
        return peer.get(page.getGUID());
    	
    }
    
    
     /**
     * Returns the last page of a file in the metadata
     * in the form of a Filestream.
     * @param fileName
     * @return Filestream
     * @throws Exception
     */
    public FileStream tail(String fileName) throws Exception
    {
        Metadata metadata = readMetaData(); //always read first when creating

        Page page = metadata.getFile(fileName).getLastPage();
        
        ChordMessageInterface peer = chord.locateSuccessor(page.getGUID());
        return peer.get(page.getGUID());
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
        return read(fileName, 0);
    }

     /**
     * Adds a new file to the end of the array of 
     * files in the metadata.
     * @param filename
     * @param filepath
     * @throws Exception
     */
    public void append(String filename, String localfile) throws Exception
    {
        // TODO: append data to fileName. If it is needed, add a new page.
        // Let guid be the last page in Metadata.filename
        //ChordMessageInterface peer = chord.locateSuccessor(guid);
        //peer.put(guid, data);
        // Write Metadata
        Metadata metadata = readMetaData(); //always read first when creating

	    // md5 the file
        long guid = md5(localfile);
        Page page = new Page(0, guid, 0);
        metadata.getFile(filename).addPage(page);
        
        ChordMessageInterface peer = chord.locateSuccessor(guid);
        peer.put(guid, new FileStream(localfile));
        writeMetaData(metadata);
    }

   
}
