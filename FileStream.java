import java.io.*;
import java.nio.*;

//only to transfer a file from local to remote
// use when you do the append
public class FileStream extends InputStream implements Serializable {

    private String path;
    private File file;
    private int currentPosition;
    private byte[] byteBuffer;
    private int size; //make public or make a getter to use to get the size of the file in 
    
    public FileStream(String pathName) throws FileNotFoundException, IOException {
      this.path = pathName;
      file = new File(pathName);
      this.size = (int)file.length();
      this.byteBuffer = new byte[size];
      
      FileInputStream fileInputStream = new FileInputStream(pathName);
      int i = 0;
      while (fileInputStream.available()> 0)
      {
	      byteBuffer[i++] = (byte)fileInputStream.read();
      }
      
      fileInputStream.close();	
      currentPosition = 0;	  
    }
    
    public FileStream() throws FileNotFoundException    {
      currentPosition = 0;	  
    }
    
    public int read() throws IOException {
 	    if (currentPosition < size) {
        return (int)byteBuffer[currentPosition++];
      }
      return 0;
    }
 	        
    public int available() throws IOException
    {
	    return size - currentPosition;
    }

    public int getSize() {
      return this.size;
    }

    public String getPath() {
      return this.path;
    }

    public void setPath(String newPath) {
      this.path = newPath;
    }

    public File getFile() {
      return this.file;
    }


    public void setFile(File newFile) {
      this.file = newFile;
    }
}