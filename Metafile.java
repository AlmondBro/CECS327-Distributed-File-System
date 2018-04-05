import java.util.ArrayList;
public class Metafile {
    private string name;
    private long size;
    private ArrayList<Page> pages; 
    private DFS distributedFileSystem;


    public Metafile(string name, long size, ArrayList<Page> pages) {
        this.name = name;
        this.size = size;
        this.pages = pages;    

        
    }

}