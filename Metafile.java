public class Metafile{
    private String Name;
    private long Size;
    private ArrayList<Page> pages;

    //array of pages
    public Metafile()
    {
        
    }

    public String getName()
    {
        return Name;
    }

    public long getSize()
    {
        return Size;
    }

    public void setName(String fileName)
    {
        this.Name = fileName;
    }
    public void setSize(long size)
    {
        this.Size = size;
    }
}