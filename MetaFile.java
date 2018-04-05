import java.util.ArrayList;


public class MetaFile {
	private String name;
	private int numberOfPages;
	private int pageSize;
	private int size;
	private ArrayList<Page> pages;
	
	public MetaFile(String name, int numberOfPages, int pageSize, int size, ArrayList<Page> pages)
	{
		this.name = name;
		this.numberOfPages = numberOfPages;
		this.pageSize = pageSize;
		this.size = size;
		this.pages = pages;
	}
}
