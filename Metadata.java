public class Metadata
{
    private ArrayList<Metafile> Metafiles;
    private DFS distributedFileSystem;

    public Metadata()
    {
      
    }


    public void addFile(String fileName)
    {
        this.Metafiles = new ArrayList<Metafile>;
        Metafiles.setName(fileName);
    }
}