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
	
	public void changeName(String oldName, String newName)
	{
		for(int i = 0; i < metafiles.size(); i++)
		{
			if(metafiles.get(i).getName().equals(oldName)) {
				metafiles.get(i).setName(newName);
			}
				
		}
	}
	public String getFileNames()
	{
		String fileNames = "";
		for(int i = 0; i < metafiles.size(); i++)
		{
			fileNames = fileNames + metafiles.get(i).getName();
		}
		return fileNames;
	}
	
	public void createFile(String fileName) throws FileNotFoundException, IOException {
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

	public void append(String name, string localFile) {
		
	}

	public void toJson() {

	}

	public void readfromJSON() {
	
	}
}
