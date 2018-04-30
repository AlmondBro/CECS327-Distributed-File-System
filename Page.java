public class Page {
	private int number;
	private long guid;
	private long size;
	
	
	/**
	 * The constructor for the page class. Requires
	 * a page number, a guid, and the page size.
	 * @param number
	 * @param guid
	 * @param size
	 */
	public Page(int number, long guid, long size)
	{
		this.number = number;
		this.guid = guid;
		this.size = size;
	}

	/**
	 * Returns the page's number.
	 * @return
	 */
	public int getNumberofPage()
	{
		return this.number;
	}
	/**
	 * Returns the last page
	 * @return the index of the page
	 */
	public int getLastPage()
	{
		return 0;
	}
	/**
	 * Returns the page's GUID
	 * @return guid
	 */
	public long getGUID() {
		return this.guid;
	}
	/**
	 * Returns the size of the page
	 * @return size
	 */
	public long getSize() {
		return this.getSize();
	}
	/**
	 * Sets the page's number
	 * @param number
	 */
	public void setPage(int number) {
		this.number = number;
	}
}
