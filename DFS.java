import java.rmi.*;
import java.net.*;
import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.math.BigInteger;
import java.security.*;
// import a json package
import com.google.gson.*;


/* JSON Format

 {
    "metadata" :
    {
        file :
        {
            name  : "File1"
            numberOfPages : "3"
            pageSize : "1024"
            size : "2291"
            page :
            {
                number : "1"
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


public class DFS
{
    int port;
    Chord chord;
    private Gson gson;
    private String json;
    
    private long md5(String objectName)
    {
        try
        {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(objectName.getBytes());
            BigInteger bigInt = new BigInteger(1,m.digest());
            return Math.abs(bigInt.longValue());
        }
        catch(NoSuchAlgorithmException e)
        {
                e.printStackTrace();
                
        }
        return 0;
    }
    
    
    
    public DFS(int port) throws Exception
    {
        gson = new Gson();
        
        //ArrayList<Page> pages = new ArrayList<Page>();
        //MetaFile file = new MetaFile("New-name", 0, 0, 0, pages);
        ArrayList<MetaFile> files = new ArrayList<MetaFile>();
        Metadata metadata = new Metadata("New-name", files);
        
        json = gson.toJson(metadata);
        
        System.out.println(json);
        
        this.port = port;
        long guid = md5("" + port);
        chord = new Chord(port, guid);
        Files.createDirectories(Paths.get(guid+"/repository"));
    }
    
    public  void join(String Ip, int port) throws Exception
    {
        chord.joinRing(Ip, port);
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
    
    public void writeMetaData(InputStream stream) throws Exception
    {
        JsonParser jsonParser _ null;
        long guid = md5("Metadata");
        ChordMessageInterface peer = chord.locateSuccessor(guid);
        peer.put(guid, stream);
    }
   */
    public void mv(String oldName, String newName) throws Exception
    {
        // TODO:  Change the name in Metadata
        // Write Metadata
        
    	Metadata metadata = gson.fromJson(json, Metadata.class);
    	metadata.changeName(newName);
    	json = gson.toJson(metadata);
    }

    
    public String ls() throws Exception
    {
        String listOfFiles = "";
       // TODO: returns all the files in the Metadata
       // JsonParser jp = readMetaData();
        
        Metadata metadata = gson.fromJson(json, Metadata.class);
    	listOfFiles = metadata.getFileNames();
        
        return listOfFiles;
    }

    
    public void touch(String fileName) throws Exception
    {
        // TODO: Create the file fileName by adding a new entry to the Metadata
        // Write Metadata
    	
    	Metadata metadata = gson.fromJson(json, Metadata.class);
    	metadata.createFile(fileName);
    	json = gson.toJson(metadata);
    }
    public void delete(String fileName) throws Exception
    {
        // TODO: remove all the pages in the entry fileName in the Metadata and then the entry
        // for each page in Metadata.filename
        //     peer = chord.locateSuccessor(page.guid);
        //     peer.delete(page.guid)
        // delete Metadata.filename
        // Write Metadata
    	
        
    }
    
    public byte[] read(String fileName, int pageNumber) throws Exception
    {
        // TODO: read pageNumber from fileName
    	Metadata metadata = gson.fromJson(json, Metadata.class);
    	Page page = metadata.getFile(fileName).getPage(pageNumber);
    	
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	ObjectOutput out = null;
    	byte[] yourBytes;
    	try {
    	  out = new ObjectOutputStream(bos);   
    	  out.writeObject(page);
    	  out.flush();
    	  yourBytes = bos.toByteArray();
    	} finally {
    	  try {
    	    bos.close();
    	  } catch (IOException ex) {
    	  }
    	}
    	
        return yourBytes;
    }
    
    
    public byte[] tail(String fileName) throws Exception
    {
        // TODO: return the last page of the fileName
    	Metadata metadata = gson.fromJson(json, Metadata.class);
    	Page page = metadata.getFile(fileName).getLastPage();
    	
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	ObjectOutput out = null;
    	byte[] yourBytes;
    	try {
    	  out = new ObjectOutputStream(bos);   
    	  out.writeObject(page);
    	  out.flush();
    	  yourBytes = bos.toByteArray();
    	} finally {
    	  try {
    	    bos.close();
    	  } catch (IOException ex) {
    	  }
    	}
    	return yourBytes;
    }
    public byte[] head(String fileName) throws Exception
    {
        // TODO: return the first page of the fileName
    	Metadata metadata = gson.fromJson(json, Metadata.class);
    	Page page = metadata.getFile(fileName).getFirstPage();
    	
    	ByteArrayOutputStream bos = new ByteArrayOutputStream();
    	ObjectOutput out = null;
    	byte[] yourBytes;
    	try {
    	  out = new ObjectOutputStream(bos);   
    	  out.writeObject(page);
    	  out.flush();
    	  yourBytes = bos.toByteArray();
    	} finally {
    	  try {
    	    bos.close();
    	  } catch (IOException ex) {
    	  }
    	}
    	return yourBytes;
    }
    public void append(String filename, Byte[] data) throws Exception
    {
        // TODO: append data to fileName. If it is needed, add a new page.
        // Let guid be the last page in Metadata.filename
        //ChordMessageInterface peer = chord.locateSuccessor(guid);
        //peer.put(guid, data);
        // Write Metadata
    	
        
    }
    
}
