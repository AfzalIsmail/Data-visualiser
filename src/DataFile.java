import java.util.List;

public class DataFile {

    private String name;
    private String path;
    private int numAttr;
    private String[] titles;

    public DataFile(){
        name = "null";
        path = "null";
        numAttr = 0;
        titles = new String[60];

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getNumAttr() {
        return numAttr;
    }

    public void setNumAttr(int numAttr) {
        this.numAttr = numAttr;
    }

    public String[] getTitles() {
        return titles;
    }

    public void setTitles(String[] titles) {
        this.titles = titles;
    }
}
