public class Page {
	private int number;
	private long guid;
	private long size;
	
	public Page(int number, long guid, long size)
	{
		this.number = number;
		this.guid = guid;
		this.size = size;
	}
	
	public int getNumber()
	{
		return number;
	}
}
