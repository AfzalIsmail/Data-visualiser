import java.util.ArrayList;

public class ColumnData {
    private String name;

    private ArrayList<Object> data;

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

    public ArrayList<Object> getData() {
        return data;
    }

    public void setData(ArrayList<Object> data) {
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
