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
			return message;
		}	
		return fileNames;
	}
	/**
	 * Creates a file with the given filename.
	 * @param fileName
	 */
	public void createFile(String fileName) throws FileNotFoundException, IOException {
		
		MetaFile metafile = new MetaFile(fileName, 0, 0, 0, new ArrayList<Page>());
		metafiles.add(metafile);
		/*ArrayList<Page> pages = new ArrayList<Page>();
		File new_File = new File(fileName);
		FileStream fileStream = new FileStream(fileName);
		fileStream.setFile(new_File);
		//(String name, int numberOfPages, int pageSize, int size, ArrayList<Page> pages)
		MetaFile newFile = new MetaFile(fileStream.getFile().getPath(), 0, 0, fileStream.getSize(), pages);
		metafiles.add(newFile); */
	}
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
			System.out.println("A file with that name does not exist.");
		}

		return metafile;
		//throw new Exception("A file with that name does not exist!");
	} //end getFile() method

	/**
	 * Deletes the given file in the metadata.
	 * @param fileName is the name of the file you wish to delete.
	 */
	public void delete(String fileName) {
		for(int i = 0; i < metafiles.size(); i++)
		{
			if(metafiles.get(i).getName().equals(fileName))
				 metafiles.remove(i);
		}
	}

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
