import javafx.scene.control.CheckBox;

import java.util.ArrayList;

public class DataFile {

    private String name;
    private String path;
    private int colNum;
    private ArrayList<String> cNames;
    private ArrayList<ColumnData> colData;
    private ArrayList<CheckBox> checkBoxHeaders;

    public DataFile(){
        name = "null";
        path = "null";
        colNum = 0;
        colData = null;
        checkBoxHeaders = null;

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

    public int getColNum() {
        return colNum;
    }

    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    public ArrayList<String> getcNames() {
        return cNames;
    }

    public void setcNames(ArrayList<String> cNames) {
        this.cNames = cNames;
    }

    public ArrayList<ColumnData> getColData() {
        return colData;
    }

    public void setColData(ArrayList<ColumnData> colData) {
        this.colData = colData;
    }

    public ArrayList<CheckBox> getCheckBoxHeaders() {
        return checkBoxHeaders;
    }

    public void setCheckBoxHeaders(ArrayList<CheckBox> checkBoxHeaders) {
        this.checkBoxHeaders = checkBoxHeaders;
    }
}

