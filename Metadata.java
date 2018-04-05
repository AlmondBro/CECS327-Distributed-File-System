
public class Metadata {
	private String name;
	private MetaFile file;
	
	public Metadata(String name, MetaFile file)
	{
		this.name = name;
		this.file = file;
	}
	
	public void changeName(String newname)
	{
		name = newname;
	}
}
