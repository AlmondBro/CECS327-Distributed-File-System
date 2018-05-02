/*
public class Metadata {
    private ArrayList<Metafile> metafiles; 
    
    public Metadata() {
        this.metafiles = new ArrayList<Metafile>;
    } 

    public addFile(string fileName) {
    }

    public void append(String fileName, long GUID) {
        
    }
} */
import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.IOException;;
public class Metadata {

	//private String name;
	private ArrayList<MetaFile> metafiles; 
	
/*	public Metadata(String name, ArrayList<MetaFile> metafiles) {
		this.name = name;
		this.metafiles = metafiles;
	}*/
	/**
	 * The constructor of the Metadata class.
	 * This constructor does not take any arguments.
	 */
	public Metadata() {
		metafiles = new ArrayList<MetaFile>();	
	}

	/**
	 * Renames the metadata's name with a new one.
	 * @param newname
	 */
	public void changeName(String oldName, String newName)
	{
		for(int i = 0; i < metafiles.size(); i++)
		{
			if(metafiles.get(i).getName().equals(oldName)) {
				metafiles.get(i).setName(newName);
			}
				
		}
	}
	/**
	 * Returns a list of all of the file's names
	 * in the form of a string.
	 * @return fileNames the string of all the files' names
	 */
	public String getFileNames(){
		String fileNames = "";
		for(int i = 0; i < metafiles.size(); i++) {
			fileNames += "\n\t" + "#" + (i+1) + ":\t" + metafiles.get(i).getName();
		}

		if (fileNames == "" || fileNames == null ) {
			String message = "\n\tList of files are empty";
			fileNames =  message;
		}	
		return fileNames;
	}
	/**
	 * Creates a file with the given filename.
	 * @param fileName
	 */
	public void createFile(String fileName) throws FileNotFoundException, IOException {
		for(int i = 0; i < metafiles.size(); i++) {
			if ( metafiles.get(i).getName().equals(fileName) ) {
				System.out.println("A file with that name already exists!");
				return;
			} //end if-statement
			else {
				System.out.println("Writing new Metafile...");
				MetaFile metafile = new MetaFile(fileName, 0, 0, 0, new ArrayList<Page>());
				metafiles.add(metafile);
			}
		} //end for loop

		
	} //end createFile() method

	/**
	 * Returns a specific file using the filename inputed.
	 * @param fileName
	 * @return file
	 * @throws Exception
	 */
	public MetaFile getFile(String fileName) {
		MetaFile metafile = null;
		for(int i = 0; i < metafiles.size(); i++) {
			if(metafiles.get(i).getName().equals(fileName)) {
				metafile = metafiles.get(i);
				//return metafiles.get(i);
			} //end if-statement.
		} //end for loop
		
		if (metafile == null) {
			System.out.println("A file with that name does not exist. Returning a null file.");
		}

		return metafile;
	} //end getFile() method

	/**
	 * Deletes the given file in the metadata.
	 * @param fileName is the name of the file you wish to delete.
	 */
	public void delete(String fileName) {
		boolean duplicateFlag = false;
		for(int i = 0; i < metafiles.size(); i++) {
			if(metafiles.get(i).getName().equals(fileName)) {
				metafiles.remove(i);
				duplicateFlag = true;
				System.out.println("Removed file: " + fileName + "\t");
			} 		 
		}  //end for-loop
		if (duplicateFlag = false) {
			System.out.println("File you wish to delete actually does not exist!");
		} //end else-statement
	} //end delete() 

	/**
	 * Appends a file to the end of the metadata.
	 * @param name
	 * @param localFile
	 */
	public void append(String name, String localFile) {
		
	}

	/**
	 * Converts the metadata to a json string
	 */
	public void toJson() {

	}
	/**
	 * Converts a json string into a metadata object.
	 */
	public void readfromJSON() {
	
	}
}
