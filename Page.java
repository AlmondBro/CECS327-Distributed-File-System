public class Page {
	private int number;
	private long guid;
	private long size;
	
	public Page(int number, long guid, long size) {
		this.number = number;
		this.guid = guid;
		this.size = size;
	}
	
	public int getNumberofPage()
	{
		return this.number;
	}

	public long getGUID() {
		return this.guid;
	}

	public long getSize() {
		return this.size;
	}
}
