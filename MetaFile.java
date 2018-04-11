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
	
	public String getName()
	{
		return name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public Page getPage(int pageNum) throws Exception
	{
		if(pageNum > pages.size() + 1)
			throw new Exception("Page number does not exist!");
		else
			return pages.get(pageNum);
	}
	public void addPage(Page page)
	{
		pages.add(page);
	}
	public Page getLastPage()
	{
		return pages.get(pages.size() - 1);
	}
	public Page getFirstPage()
	{
		return pages.get(0);
	}
}
