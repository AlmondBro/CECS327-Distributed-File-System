import java.util.ArrayList;


public class MetaFile {
	private String name;
	private int numberOfPages;
	private int pageSize;
	private int size;
	private ArrayList<Page> pages;
	
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
		this.pages = pages;
	}
	/**
	 * Returns the name of the metafile.
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * Returns the specified page from the inputted number.
	 * @param pageNum
	 * @return
	 * @throws Exception
	 */
	public Page getPage(int pageNum) throws Exception
	{
		if(pageNum > pages.size() + 1)
			throw new Exception("Page number does not exist!");
		else
			return pages.get(pageNum);
	}
	/**
	 * Appends a page to the arraylist of pages.
	 * @param page
	 */
	public void addPage(Page page)
	{
		pages.add(page);
	}
	/**
	 * Returns the last page in the array.
	 * @return
	 */
	public Page getLastPage()
	{
		return pages.get(pages.size() - 1);
	}
	/**
	 * Returns the first page in the array.
	 * @return
	 */
	public Page getFirstPage()
	{
		return pages.get(0);
	}
}
