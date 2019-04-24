/**
 * @author Afzal Ismail
 * @version 1.0
 */
import java.util.ArrayList;

public class ColumnData {

    private String name;

    private ArrayList<Object> data;

    /**
     * constructor for ColumnData
     */
    public ColumnData(){

        name = "null";

        data = null;
    }

    /**
     * method to get the name of the column
     * @return name of column
     */
    public String getName() {
        return name;
    }

    /**
     * function to set the name of a column
     * @param name of the column
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * method to get the objects in the arraylist
     * @return arraylist of data
     */
    public ArrayList<Object> getData() {
        return data;
    }

    /**
     * function to set the data arraylist
     * @param data arraylist
     */
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
