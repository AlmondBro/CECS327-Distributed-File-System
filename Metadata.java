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
public class Metadata {
	private String name;
	private ArrayList<MetaFile> metafiles; 
	
	public Metadata(String name, ArrayList<MetaFile> metafiles)
	{
		this.name = name;
		this.metafiles = metafiles;
	}
	
	public void changeName(String newname)
	{
		name = newname;
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
	public void createFile(String fileName)
	{
		ArrayList<Page> pages = new ArrayList<Page>();
        MetaFile file = new MetaFile(fileName, 0, 0, 0, pages);
	}
	public MetaFile getFile(String fileName) throws Exception
	{
		for(int i = 0; i < metafiles.size(); i++)
		{
			if(metafiles.get(i).getName().equals(fileName))
				return metafiles.get(i);
		}
		throw new Exception("A file with that name does not exist!");
	}
	public void toJson() {

	}

	public void readfromJSON() {
	
	}
	public void deleteFile() {
		
	}
}
