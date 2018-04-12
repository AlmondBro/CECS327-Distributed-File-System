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
	public int getNumber()
	{
		return number;
	}
}
