import java.util.ArrayList;


public class Metadata {
	private String name;
	private ArrayList<MetaFile> files;
	
	public Metadata(String name, ArrayList<MetaFile> files)
	{
		this.name = name;
		this.files = files;
	}
	
	public void changeName(String newname)
	{
		name = newname;
	}
	public String getFileNames()
	{
		String fileNames = "";
		for(int i = 0; i < files.size(); i++)
		{
			fileNames = fileNames + files.get(i).getName();
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
		for(int i = 0; i < files.size(); i++)
		{
			if(files.get(i).getName().equals(fileName))
				return files.get(i);
		}
		throw new Exception("A file with that name does not exist!");
	}
	public void deleteFile()
	{
		
	}
}
