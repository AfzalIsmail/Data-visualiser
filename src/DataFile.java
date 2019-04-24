/**
 * @author Afzal Ismail
 * @version 1.0
 */

import javafx.scene.control.CheckBox;

import java.util.ArrayList;

public class DataFile {

    private String name;
    private String path;
    private int colNum;
    private ArrayList<String> cNames;
    private ArrayList<ColumnData> colData;
    private ArrayList<CheckBox> checkBoxHeaders;

    /**
     * constructor for the object DataFile
     * contains all the variables required for each file imported in the program
     */
    public DataFile(){
        name = "null";
        path = "null";
        colNum = 0;
        colData = null;
        checkBoxHeaders = null;

    }

    /**
     * method to get the name of the file
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * function to set the name of the file
     * @param name of file
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * method to get the path of the file
     * @return path of file
     */
    public String getPath() {
        return path;
    }

    /**
     * function to set the path of the file
     * @param path of file
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * method to get the number of columns of the file
     * @return number of columns
     */
    public int getColNum() {
        return colNum;
    }

    /**
     * function to set the number of columns in a file
     * @param colNum column number
     */
    public void setColNum(int colNum) {
        this.colNum = colNum;
    }

    /**
     * method to get the arraylist containing all the headers of the columns in the file
     * @return arraylist of string of column headers
     */
    public ArrayList<String> getcNames() {
        return cNames;
    }

    /**
     * function to set the arraylist of column headers
     * @param cNames arraylist of string of column headers
     */
    public void setcNames(ArrayList<String> cNames) {
        this.cNames = cNames;
    }

    /**
     * method to get the arraylist of column data for a particular file
     * @return arraylist of column data
     */
    public ArrayList<ColumnData> getColData() {
        return colData;
    }

    /**
     * set the arraylist of column data to a file
     * @param colData arraylist of column data
     */
    public void setColData(ArrayList<ColumnData> colData) {
        this.colData = colData;
    }

    /**
     * gets the arraylist of checkboxes for a file
     * these checkboxes correspond to the column headers in this file
     * @return arraylist of checkboxes
     */
    public ArrayList<CheckBox> getCheckBoxHeaders() {
        return checkBoxHeaders;
    }

    /**
     * sets an arraylist of checkboxes to a file
     * @param checkBoxHeaders arraylist of checkboxes
     */
    public void setCheckBoxHeaders(ArrayList<CheckBox> checkBoxHeaders) {
        this.checkBoxHeaders = checkBoxHeaders;
    }
}

