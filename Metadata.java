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

	private String name;
	private ArrayList<MetaFile> metafiles; 
	
	public Metadata(String name, ArrayList<MetaFile> metafiles) {
		this.name = name;
		this.metafiles = metafiles;
	}

	public Metadata() {
		metafiles = new ArrayList<MetaFile>();	
	}

	
	public void changeName(String oldName, String newName) {
		for(int i = 0; i < metafiles.size(); i++) {
			if(metafiles.get(i).getName().equals(oldName)) {
				metafiles.get(i).setName(newName);
			}
		}
	}

	public String getFileNames() {
		String fileNames = "";
		for(int i = 0; i < metafiles.size(); i++) {
			fileNames += "\n"+ "\t" + "#" + i +":\t" + metafiles.get(i).getName();
		} //end for-loop
		return fileNames;
	} //end getFileNames() method

	public boolean checkIfFileExists(String fileName) {
		String currentFile; 
		boolean fileExists = false;
		for(int i = 0; i < metafiles.size(); i++) {
			currentFile = metafiles.get(i).getName().trim();
			//return ( (currentFile == fileName) ? true : false );
			if (currentFile.equalsIgnoreCase(fileName.trim()) ) {
				System.err.println("File" + fileName + "Exists");
				fileExists = true;
				return fileExists;
			} //end-if-statement
		} //end for-loop
		System.out.println("File you want to create does not exist. Creating file!");
		return fileExists;
	} //end check
	
	public void createFile(String fileName) throws FileNotFoundException, IOException {
		// checkIfFileExists(fileName); 
		// boolean fileAlreadyExists = checkIfFileExists(fileName);
		// if (fileAlreadyExists) {
		// 	System.out.println("File already exists. Please try again to create a new file and enter another file name."); 
		// 	return; 
		// } 

		//MetaFile metafile = new MetaFile(fileName, 0, 0, 0, new ArrayList<Page>());
		MetaFile metafile = new MetaFile();
		metafiles.add(metafile);
		
		/*ArrayList<Page> pages = new ArrayList<Page>();
		File new_File = new File(fileName);
		FileStream fileStream = new FileStream(fileName);
		fileStream.setFile(new_File);
		//(String name, int numberOfPages, int pageSize, int size, ArrayList<Page> pages)
		MetaFile newFile = new MetaFile(fileStream.getFile().getPath(), 0, 0, fileStream.getSize(), pages);
		metafiles.add(newFile); */
	}

	public MetaFile getFile(String fileName) throws Exception {
		for(int i = 0; i < metafiles.size(); i++)
		{
			if(metafiles.get(i).getName().equals(fileName))
				return metafiles.get(i);
		}
		throw new Exception("A file with that name does not exist!");
	}

	public void delete(String fileName)
	{
		for(int i = 0; i < metafiles.size(); i++)
		{
			if(metafiles.get(i).getName().equals(fileName))
				 metafiles.remove(i);
		}
	}

	public void append(String name, String localFile) {
		
	}

	public void toJson() {

	}

	public void readfromJSON() {
	
	}
}
