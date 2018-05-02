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
	public MetaFile(String name, int numberOfPages, int pageSize, int size, ArrayList<Page> pages) {
		this.name = name;
		this.numberOfPages = numberOfPages;
		this.pageSize = pageSize;
		this.size = size;
		this.pages = pages;
	}
	
	/**
	 * Alternate constructor for the MetaFile class.
	 * This constructor does not take any arguments.
	 */
	public MetaFile() {
		this.pages = new ArrayList<Page>();
	}

	/**
	 * Returns the name of the metafile.
	 * @return name
	 */
	public String getName() {
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
	public Page getPage(int pageNum) throws ArrayIndexOutOfBoundsException {
		Page acquiredPage = pages.get(1);
		try {
			if(pageNum > pages.size()) {
				System.out.println("Page number does not exist or the page number you entered exceeds the total number of pages of the file. Returning the first page for now if it exists.");
			} //end if-statement	
			else {
				acquiredPage = pages.get(pageNum);
			} //end else-statement
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("ArrayIndexOutOfBounds Exception - Please enter a different number for the page. It is likely too that the file still does not have any pages. Returning the first page for now.");
		} 
		/*catch (InputMismatchException e) {
			System.out.println("InputMismatch Exception  - Please enter a number and not a string.");
		} */
		return acquiredPage;
	} //end getPage()

	/**
	 * Returns the last page in the Pages array.
	 * @return the last page in a file.
	 */
	public Page getLastPage() throws ArrayIndexOutOfBoundsException {
		Page lastPage = null;
		try {
			int lastPageIndex  = pages.size() - 1;
			System.out.println("" + lastPageIndex);
			lastPage = pages.get(lastPageIndex);
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Array Index out of Bounds Exception.");
			System.out.println("Either this file in the metadata does not have any pages, or the file itself does not exist.");
		}
		return lastPage;
	}

	/**
	 * Appends a page to the arraylist of pages.
	 * @param pageObject is the 
	 */
	public void addPage(Page pageObject) {
		  pages.add(pageObject);
		  System.out.println("Number of pages: "+ pages);
		if (pages.size() > 0) {
		   //int number = page.size();
		   //pageObject.setPage(number);
		   pageObject.incrementPageNo();
		} else {
			pageObject.setPage(1);
		}
	}

	/**
	 * Returns the first page in the array.
	 * @return the first page
	 */
	public Page getFirstPage() {
		return pages.get(0);
	}
}
