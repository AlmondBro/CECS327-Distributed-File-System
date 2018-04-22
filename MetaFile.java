import java.util.ArrayList;
public class MetaFile {
	private String name;
//	private int numberOfPages;
//	private int pageSize;
//	private int size;
	private ArrayList<Page> page;

	
/*	public MetaFile(String name, int numberOfPages, int pageSize, int size, ArrayList<Page> pages)
	{
		this.name = name;
		this.numberOfPages = numberOfPages;
		this.pageSize = pageSize;
		this.size = size;
		this.page = pages;

	}*/
	public MetaFile()
	{
		this.page = new ArrayList<Page>();
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setName(String newName) {
		this.name = newName;
	}

	public Page getPage(int pageNum) throws Exception
	{
		if(pageNum > page.size() + 1)
			throw new Exception("Page number does not exist!");
		else
			return page.get(pageNum);
	}
	public void addPage(Page pageObject)
	{
		
		  page.add(pageObject);
		  System.out.println("Number of pages: "+ page);
		if(page.size() > 1)
		{
		   int number = page.size();
		   pageObject.setPage(number);
		} else
		{
			pageObject.setPage(1);
		}
	}
	public Page getLastPage()
	{
		return page.get(page.size() - 1);
	}
	public Page getFirstPage()
	{
		return page.get(0);
	}
}
