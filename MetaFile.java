import java.util.ArrayList;
public class MetaFile {
	private String name;
	private int numberOfPages;
	private int pageSize;
	private int size;
	private ArrayList<Page> page;

	/**
	 * Constructor for the MetaFile class. Requires a name,
	 * the number of pages, the page size, file size, and an arraylist of pages.
	 * @param name
	 * @param numberOfPages
	 * @param pageSize
	 * @param size
	 * @param pages
	 */
	public MetaFile(String name, int numberOfPages, int pageSize, int size, ArrayList<Page> pages)
	{
		this.name = name;
		this.numberOfPages = numberOfPages;
		this.pageSize = pageSize;
		this.size = size;
		this.page = pages;

	}
	/**
	 * Alternate constructor for the MetaFile class.
	 * This constructor does not take any arguments.
	 */
	public MetaFile()
	{
		this.page = new ArrayList<Page>();
	}
	/**
	 * Returns the name of the metafile.
	 * @return name
	 */
	public String getName()
	{
		return this.name;
	}
	/**
	 * Sets the name of the Metafile
	 * @param newName
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	/**
	 * Returns the specified page from the inputted number.
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public Page getPage(int pageNum) throws Exception
	{
		if(pageNum > page.size() + 1)
			throw new Exception("Page number does not exist!");
		else
			return page.get(pageNum);
	}
	/**
	 * Returns the last page in the array.
	 * @return the last page
	 */
	public Page getLastPage()
	{
		int size  = page.size() -1;
		System.out.println("" + size);
		return page.get(size);
	}
	/**
	 * Appends a page to the arraylist of pages.
	 * @param page
	 */
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
	/**
	 * Returns the first page in the array.
	 * @return the first page
	 */
	public Page getFirstPage()
	{
		return page.get(0);
	}
}
