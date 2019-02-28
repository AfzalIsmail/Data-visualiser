import java.util.ArrayList;

public class ColumnData {
    private String name;

    private ArrayList<String> data;

    public ColumnData(){

        name = "null";

        data = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getData() {
        return data;
    }

    public void setData(ArrayList<String> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ColumnData{" +
                "name='" + name + '\'' +
                ", data=" + data +
                '}';
    }
}
