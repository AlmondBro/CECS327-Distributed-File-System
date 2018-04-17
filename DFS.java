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
    
    private Gson gson;
    private String json;
    private FileStream filestream;
    private Metadata metadata;
    private String listOfFiles;
    
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
                e.printStackTrace();
                
        }
        return 0;
    } //end md5() method
    
      /**
     * Constructor for the DFS class. Takes
     * an integer as the port number.
     * @param port
     * @throws Exception
     */
    public DFS(int port) throws Exception
    {
        gson = new Gson();
        filestream = new FileStream();
        
       // ArrayList<MetaFile> files = new ArrayList<MetaFile>();
        //listOfFiles = "";

        //json = gson.toJson(metadata);
        
        System.out.println("\nMetadata JSON object (upon creating DFS object):\n\n"+json+"\n");
        
        this.port = port;
        long guid = md5("" + port);
        chord = new Chord(port, guid);
        Files.createDirectories(Paths.get(guid+"/repository"));
    }

    public String getStringOfFiles() {
        return this.listOfFiles;
    }

    public void setStringOfFiles(String stringToConcatenate) {
        this.listOfFiles.concat(stringToConcatenate);
    }   

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

  public Metadata readMetaData() throws Exception
  {
     //Read -- do something and write  
      long guid = md5("Metadata");
      ChordMessageInterface peer = chord.locateSuccessor(guid);

      FileStream metadataraw = peer.get(guid);
      String fileName = "./"+guid+"/metadata.tep" + guidObject;

      FileOutputStream output = new FileOutputStream(fileName);

      while (metadataraw.available() > 0) 
          output.write(metadataraw.read());
      output.close();

      //String fileName = "./"+guid+"/metadata.tep" + guidObject;
      FileReader filereader = new FileReader("jsonFile.json");
      gson.fromJson(); //Reads metadata
      Metadata m = gson.fromJson(filereader, Metadata.class);

      // jsonParser = Json.createParser(metadataraw);
      return m;
  }

    public void writeMetaData(FileStream stream) throws Exception {
        //JsonParser jsonParser _null;
        long process_guid = md5("Metadata"); //which process has that file
        long file_guid = md5(stream.getFile().getName()); 

        ChordMessageInterface process = chord.locateSuccessor(process_guid); //which process has that metadata
        process.put(file_guid, stream);
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
        
        metadata.append(fileName);

        //this.writeMetaData(metadata);


      /*  File newFile = new File(fileName);
        fileStream(newFile.getAbsolutePath());
        filestream.setFile(newFile);
        //filestream.setPath(fileName);

        metadata.createFile(fileName);
        metadata = gson.fromJson(json, Metadata.class);
        
        writeMetaData(filestream);
        json = gson.toJson(metadata);*/ 
        filestream(fileName);
        this.writeMetaData(filestream);
        
    }

     /**
     * Returns a list of the files in the
     * metadata in the form of a string.
     * @return
     * @throws Exception
     */
    public String ls() throws Exception {
       // TODO: returns all the files in the Metadata
        
        Metadata metadata = gson.fromJson(json, Metadata.class);
    	//listOfFiles = metadata.getFileNames();
        setStringOfFiles(metadata.getFileNames());
        return this.getStringOfFiles();
    }
    
     /**
     * Renames the metadata object.
     * @param oldName
     * @param newName
     * @throws Exception
     */
    public void mv(String oldName, String newName) throws Exception {
        // TODO:  Change the name of a file in Metadata
        // Write Metadata
        
    	Metadata metadata = gson.fromJson(json, Metadata.class);
    	metadata.changeName(oldName, newName);
        json = gson.toJson(metadata);
        System.out.println("Json:\t"+json);
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
           Metadata metadata = gson.fromJson(json, Metadata.class);
           metadata.delete(fileName);
           json = gson.toJson(metadata);
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
    	Metadata metadata = gson.fromJson(json, Metadata.class);
    	Page page = metadata.getFile(fileName).getPage(pageNumber);
    	
    	String filepath = fileName + ".txt";
    	PrintWriter writer = new PrintWriter(filepath, "UTF-8");
    	writer.println(page);
    	writer.close();
    	
    	FileStream inputstream = new FileStream(filepath);
    	return inputstream;
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
        // TODO: return the last page of the fileName
    	Metadata metadata = gson.fromJson(json, Metadata.class);
    	Page page = metadata.getFile(fileName).getLastPage();
    	
    	String filepath = fileName + ".txt";
    	PrintWriter writer = new PrintWriter(filepath, "UTF-8");
    	writer.println(page);
    	writer.close();
    	
    	FileStream inputstream = new FileStream(filepath);
    	return inputstream;
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
        // TODO: return the first page of the fileName
    	Metadata metadata = gson.fromJson(json, Metadata.class);
    	Page page = metadata.getFile(fileName).getFirstPage();
    	
    	String filepath = fileName + ".txt";
    	PrintWriter writer = new PrintWriter(filepath, "UTF-8");
    	writer.println(page);
    	writer.close();
    	
    	FileStream inputstream = new FileStream(filepath);
    	return inputstream;
    }

     /**
     * Adds a new file to the end of the array of 
     * files in the metadata.
     * @param filename
     * @param filepath
     * @throws Exception
     */
    public void append(String filename, String filepath) throws Exception
    {
        // TODO: append data to fileName. If it is needed, add a new page.
        // Let guid be the last page in Metadata.filename
        //ChordMessageInterface peer = chord.locateSuccessor(guid);
        //peer.put(guid, data);
        // Write Metadata
    	
    	// Get the file's size
    	File file = new File(filepath);
    	long fileSpace = file.getTotalSpace();
    	
    	// md5 the file
    	long guid = md5(filename);
    	
    	// store the data into a page
    	Page page = new Page(metadata.getFile(filename).getLastPage().getNumber() + 1, guid, fileSpace);
    	
    	// put the page into the metadata
    	metadata.getFile(filename).addPage(page);
    	
    	// Write the file to disk
    	String newFilepath = filename + ".txt";
    	PrintWriter writer = new PrintWriter(newFilepath, "UTF-8");
    	writer.println(page);
    	writer.close();
    	
    	FileStream inputstream = new FileStream(newFilepath);
        
    	// TODO: update the file
    	ChordMessageInterface peer = chord.locateSuccessor(guid);
        peer.put(guid, inputstream);
    }
}
