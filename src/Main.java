/**
 * @author Afzal Ismail
 * @version
 */
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Map;

public class Main extends Application{

    private BorderPane borderPane;

    private MenuBar menuBar;

    private Menu file, help;

    private MenuItem browseFile, save, exit,manual;

    private Button displayTable, importFile, saveData, correlation, chart, anova;

    private Button asc, dsc;

    private ArrayList<DataFile> currentFile, files = new ArrayList<>();

    private ArrayList<String> colNames;

    private ArrayList<ColumnData> cData;

    private Object[][] data;

    private TabPane tabPane;

    private Tab tab;

    private String path = null;

    private String[] columnNames = null;

    private CheckBox columnHeaders;

    private ScrollPane scrollPane;

    private String tabID,tabPath;

    private ArrayList<CheckBox> checkBoxes;

    private GridPane dataPane;

    private ScrollPane scrollData;

    private Stage window;

    private final int maxDistinct = 20, minCol = 2, maxCols = 60, maxFileNum = 20;


    /**
     * main function
     * @param args
     */
    public static void main ( String[] args){

        launch(args);
    }

    /**
     * function that will display the main window once the program is launched
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage){

        window = primaryStage;

        window.setTitle("DVision");

        currentFile = new ArrayList<>();
        files = new ArrayList<>();

        //layout of application
        borderPane = new BorderPane();

        //creating menu bar which will be found on top
        menuBar = new MenuBar();
        file = new Menu("File");
        help = new Menu("Help");

        //Load file image
        Image loadFile = new Image("picture/load.png");

        //Load file button which appears on the submenu
        //and all the events and styling associated to this button
        ImageView loadFileView = new ImageView(loadFile);
        loadFileView.setFitHeight(20);
        loadFileView.setFitWidth(20);
        importFile = new Button();
        Tooltip importButton = new Tooltip("Import a new file");
        importFile.setTooltip(importButton);
        importFile.setOnMouseEntered(e -> importFile.setStyle("-fx-background-color: rgb(99, 99, 99)"));
        importFile.setOnMouseExited(e -> importFile.setStyle("-fx-background-color: rgb(68, 69, 71)"));
        importFile.setPadding(new Insets(2,10,2,10));
        importFile.setStyle("-fx-background-color: rgb(68, 69, 71)");
        importFile.setGraphic(loadFileView);

        //Save data image
        Image saveFile = new Image("picture/save1.png");

        //Save button which appears in the submenu
        ImageView saveFileView = new ImageView(saveFile);
        saveFileView.setFitWidth(22);
        saveFileView.setFitHeight(22);
        saveData = new Button();
        Tooltip saveButton = new Tooltip("Save data");
        saveData.setTooltip(saveButton);
        saveData.setOnMouseEntered(e -> saveData.setStyle("-fx-background-color: rgb(99, 99, 99)"));
        saveData.setOnMouseExited(e -> saveData.setStyle("-fx-background-color: rgb(68, 69, 71)"));
        saveData.setPrefHeight(24);
        saveData.setPadding(new Insets(2,10,0,10));
        saveData.setStyle("-fx-background-color: rgb(68, 69, 71)");
        saveData.setGraphic(saveFileView);

        //saveTxtFile(saveData,primaryStage);

        //HBox containing the load and save file buttons
        HBox subMenu = addSubMenuBar();
        subMenu.getChildren().addAll(importFile,saveData);

        //Menu items for file tab
        browseFile = new MenuItem("Browse File");
        save = new MenuItem("Save");

        //Menu items for help tab
        exit = new MenuItem("Exit");
        manual = new MenuItem("Manual");

        //tabpane
        tabPane = new TabPane();

        //opens the file browser and saves each file selected in an arraylist
        browseFile.setOnAction(e -> {

            //System.out.println("Browse file menu item pressed.");

            if(files.size() < maxFileNum) {
                displayHeaders(window);
            }else{
                alertBox.display("Max number of imported files reached","The program can only import up to 20 files.");
                //System.out.println("File limit of 20 reached.");
            }

        });

        //import file icon action when pressed
        importFile.setOnAction(e -> {

            //System.out.println("Import file icon pressed.");
            //System.out.println(files.size());

            if(files.size() < maxFileNum) {
                displayHeaders(window);
            }else{
                alertBox.display("Max number of imported files reached","The program can only import up to 20 files.");
                //System.out.println("File limit of 20 reached.");
            }
        });

        //------------------------------------------------calls the saveTxtFile function when the menu item is pressed
        save.setOnAction(e ->{

            saveTxtFile(primaryStage);
        });

        //------------------------------------------------calls the saveTxtFile function when the save item is pressed
        saveData.setOnAction(e ->{

            saveTxtFile(primaryStage);

        });

        //------------------------------------------------calls the close program function when the exit menu is pressed
        exit.setOnAction(e ->{

            try {
                closeProgram();
            }catch (IOException ioE){

            }

        });

        //------------------------------------------------calls the manual window to appear
        manual.setOnAction(e ->{

            logFile.addToLog("Manual window opened");
            manualWindow.manWindow();

        });

        //Testing log messages
        //help.setOnAction(e -> logFile.printLog());
        //help.setOnAction(e -> System.out.println(tabPane.getSelectionModel().getSelectedItem().getId()));


        //Setting tab pane to top
        tabPane.setSide(Side.TOP);

        //assigning items to file tab
        file.getItems().addAll(browseFile,save);
        //assigning items to help tab
        help.getItems().addAll(exit,manual);

        //assigning menu tabs to menu bar
        menuBar.getMenus().addAll(file,help);
        //Color of menu bar
        menuBar.setStyle("-fx-background-color: rgb( 51, 170, 168)");

        //Displaying the program logo on the top left corner
        Image logo = new Image("picture/logo2.png");
        ImageView logoView = new ImageView(logo);

        logoView.setX(0);
        logoView.setY(0);
        logoView.setFitHeight(60);
        logoView.setFitWidth(68);

        //VBox that contains the logo, menu bar and submenu
        VBox header = headerArea();
        header.setStyle("-fx-background-color: rgb(123, 129, 137)");
        header.getChildren().addAll(logoView,menuBar,subMenu);

        //setting nodes to border pane
        borderPane.setTop(header);
        borderPane.setCenter(tabPane);
        //borderPane.getStylesheets().add("styleSheet.css");

        //creating new scene
        Scene scene = new Scene(borderPane, 1400, 750);
        //scene.getStylesheets().add("styleSheet.css");

        //calls the close close program function when the window is requested to close
        window.setOnCloseRequest(e -> {
                e.consume();

                try {
                closeProgram();
            }catch (IOException i){

            }

        });


        window.getIcons().add(logo);
        window.setScene(scene);

        //window cannot be resized
        window.setResizable(true);
        window.show();

    }

    /**
     * function that will be called when the program is requested to be closed
     * if the answer is true, it will save the log files
     * @throws IOException
     */
    private void closeProgram() throws IOException {

        logFile.addToLog("Program close request");

        boolean answer = confirmBox.display("Close request", "Are you sure you want to exit" + "\n" +
                                                                                "the program?");

        if(answer){

            logFile.addToLog("Program closed");

            logFile.saveLog();

            window.close();
            Platform.exit();
        }

    }

    /**
     * Function to open file browser and store the name and path of each file in an arraylist
     * @param stage the stage in which the file browser should be opened
     */
    private void fileBrowser(Stage stage, ArrayList<DataFile> currentPath, ArrayList<DataFile> paths){

        try {

            DataFile dataFile = new DataFile();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Browse a file");
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
            File selectedFile = fileChooser.showOpenDialog(stage);

            logFile.addToLog("File " + selectedFile.getName() + " is selected");
            System.out.println("File " + selectedFile.getName() + " is selected");

                    dataFile.setName(selectedFile.getName());
                    dataFile.setPath(selectedFile.getPath());

                    //checks for duplicate files if already opened
                    boolean dup = duplicates(paths,dataFile);
                    //System.out.println(dup);

                    //add the file to the arraylist if the list is empty
                    if(paths.size() == 0){

                        currentPath.add(dataFile);

                    //check for duplicates before adding
                    }else if(paths.size() > 0 && dup){

                        alertBox.display("Error","File has already been imported");

                    }else{

                        currentPath.add(dataFile);
                    }


        }catch (Exception e){
            //System.out.println("No file chosen.");
            logFile.addToLog("No file chosen.");
        }

        //System.out.println(dataFile.getName());
        //System.out.println(dataFile.getPath());
    }

    /**
     * check for duplicates in the opened files arraylist
     * @param a list of datafiles imported
     * @param d file that needs to be checked
     * @return true if duplicate found or else false
     */
    private boolean duplicates(ArrayList<DataFile> a, DataFile d){

        boolean res = false;

        for(DataFile df:a){

            if(df.getName().equals(d.getName())){

                res = true;
            }else{

            }
        }

        return res;

    }

    /**
     * save function that will save the statistical data for the selected columns in a text file
     * @param stage - which window the file saver will be opened
     */
    private void saveTxtFile(Stage stage){

        //determine which tab is opened
            try {
                tabID = tabPane.getSelectionModel().getSelectedItem().getId();
                //System.out.println("Tab " + tabID + "is being manipulated by user.");
                logFile.addToLog("Save button is selected for file: " + tabID);

            }catch (Exception e2){

            }

            //get the data from the file
            readFile(files,tabID,path,data,cData);

            //arraylist that will contain the column names for which the datd will be saved
            ArrayList<String> saveCols = new ArrayList<>();

        //get the selected checkboxes
            selectedCheckbox(saveCols, files,tabID);

            //arraylist that will contain the statistical information for a particular column
            ArrayList<String> saveData = new ArrayList<>();

            try{

                //check which tab is being manipulated by user
                for(DataFile f: files){

                    if(f.getName().equals(tabID)){

                        logFile.addToLog("Columns selected for saving: ");

                        //check which column(s) were selected by the user
                        for(String s: saveCols){

                            for(ColumnData c: f.getColData()){

                                if(c.getName().equals(s)){

                                    logFile.addToLog("- " + c.getName());

                                    String saveString = "";

                                    //check thr data type
                                    String check = checkVariable.checkVar(c.getData());

                                    //statistical data to be save for categorical data
                                    if(check.equals("String") || check.equals("Char") || check.equals("Boolean")){

                                        Map distinct = Distinct.getDistinct(c.getData());

                                        int missVal = Statistics.missingData(c.getData());

                                        ArrayList<String> distinctString = new ArrayList<>();

                                        distinct.forEach((k,v) -> {

                                            String ds = "->" +  k  + "=" +  v  +"\n";

                                            distinctString.add(ds);
                                        });

                                        if(distinct.size() <= maxDistinct){

                                            saveString = "Column name: " + c.getName() + "\n" +
                                                    "Data type: " + check + "\n" +
                                                    "Length of data: " + c.getData().size() + "\n" +
                                                    "No. of missing values: " + missVal + "\n" +
                                                    "No. of distinct elements: " + distinct.size() + "\n" +
                                                    "Distinct elements: " + "\n" + distinctString + "\n" + "\n";

                                            saveData.add(saveString);
                                            //System.out.println(saveString);

                                        }else{

                                            saveString = "Column name: " + c.getName() + "\n" +
                                                    "Data type: " + check + "\n" +
                                                    "Length of data: " + c.getData().size() + "\n" +
                                                    "No. of missing values: " + missVal + "\n" +
                                                    "No. of distinct elements exceed 20!" + "\n" + "\n";

                                            saveData.add(saveString);
                                            //System.out.println(saveString);

                                        }

                                    //statistical data to be saved for numerical data
                                    }else if(check.equals("Integer") || check.equals("Double")){

                                        int missVAl = Statistics.missingData(c.getData());
                                        double sum = Statistics.getSum(c.getData());
                                        double mean = Statistics.getMean(c.getData(), sum);
                                        double variance = Statistics.getVariance(c.getData(), mean);
                                        double stDeviation = Statistics.getStDeviation(variance);
                                        double median = Statistics.getMedian(c.getData());

                                        DecimalFormat df = new DecimalFormat(".####");

                                        saveString = "Column name: " + c.getName() + "\n" +
                                                "Length of data: " + c.getData().size() + "\n" +
                                                "Sum of data: " + df.format(sum) + "\n" +
                                                "Min value: " + Statistics.getMin(c.getData()) + "\n" +
                                                "Max value: " + Statistics.getMax(c.getData()) + "\n" +
                                                "Mean: " + df.format(mean) + "\n" +
                                                "Median: " + median + "\n" +
                                                "Variance: " + df.format(variance) + "\n" +
                                                "Standard deviation: " + df.format(stDeviation) + "\n" +
                                                "Missing values: " + missVAl + "\n" + "\n";

                                        saveData.add(saveString);
                                        //System.out.println(saveString);

                                    }
                                }
                            }
                        }
                    }
                }


            }catch (Exception e1){

            }

            //opening the file chooser
            FileChooser fileChooser = new FileChooser();

            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //allows the user to save the file with a custom name and in a specific location on the internal or an external hardrive
            File file = fileChooser.showSaveDialog(stage);

            logFile.addToLog("File saved to: " + file.getPath());

            //System.out.println(file.getPath());

            if(file != null){
                writeFile(saveData, file);
            }


    }

    /**
     * function to write the content of a string arraylist to a text file
     * @param s arraylist of string
     * @param file file that will contain the strings
     */
    private void writeFile(ArrayList<String> s, File file){

        try{

            FileWriter fileWriter = new FileWriter(file);

            for(String st: s){
                //System.out.println(st);

                fileWriter.write(st);
            }
            fileWriter.close();

        }catch (IOException e){

        }


    }

    /**
     * To add the choiceBox to a VBox to be displayed in the BorderPane
     * the choiceBox consists of the headers of each column that the user can select to be displayed
     * @return vbox
     */
    private VBox addVBox(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.setPrefWidth(200);
        vbox.setPrefHeight(500);
        //vbox.setStyle("-fx-background-color: rgb(119, 214, 211)");

        //Text title = new Text("Data columns");

        //vbox.getChildren().add(title);

        return vbox;
    }

    /**
     *The Vbox for the header area that will contain the logo, menu and submenu
     * @return
     */
    private VBox headerArea(){

        VBox vBox = new VBox();

        return vBox;
    }

    /**
     * Creating HBoxes that can will place all the other layouts in the tabs horizontally
     * @return hBox
     */
    private HBox addHBox(){
        HBox hBox = new HBox();

        return hBox;
    }

    /**
     * HBox the that contains the load file and save file button icons
     * @return
     */
    private HBox addSubMenuBar(){
        HBox hBox = new HBox();
        VBox space = new VBox();
        space.setPrefWidth(10);
        hBox.getChildren().add(space);
        hBox.setSpacing(5);
        hBox.setPrefHeight(25);
        hBox.setStyle("-fx-background-color: rgb(68, 69, 71)");

        return hBox;
    }

    /**
     * Creating ScrollPanes that can be added into other types of layouts
     * @return scrollPane
     */
    private ScrollPane addScrollPane(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefViewportWidth(1200);

        return scrollPane;
    }

    /**
     * Function to read only the headers in the csv files in the files arraylist
     * and display them in their appropriate tab
     * @param stage
     */
    private void displayHeaders(Stage stage){

        //Keeping log of when browseFile is pressed
        logFile.addToLog("File browser opened");
        System.out.println("File browser opened");

        //calling the filebrowser function
        fileBrowser(stage, currentFile, files);

        try {
            //iterates over files arraylist
            for (DataFile file : currentFile) {

                //HBox that will contain the
                HBox tabContent = addHBox();

                //VBox that will contain the choice for the columns to be displayed
                VBox columnChoice = addVBox();
                columnChoice.setStyle("-fx-background-color: rgb(224, 224, 224)");

                //Holds the string of the file path
                path = file.getPath();

                //Creating a new tab for each file uploaded
                tab = new Tab(file.getName());
                tab.setId(file.getName());


                //read the header of the csv file
                try {
                    columnNames = ReadCSV.readHeader(file.getPath());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    System.out.println("Error to open file for column");

                }

                //sets the column number found in the csv file
                file.setColNum(columnNames.length);

                //Text to be displayed in the left panel once a file is imported
                //Displays the path of the imported file
                Text filePath = new Text("File path: " + path);
                filePath.setStyle("-fx-fill: rgb(79, 86, 84)");
                columnChoice.getChildren().add(filePath);

                //Display the number of columns in that file
                Text colNum = new Text("Number of columns: " + file.getColNum());
                colNum.setStyle("-fx-fill: rgb(79, 86, 84)");
                columnChoice.getChildren().add(colNum);

                Text title = new Text("Data columns:");
                columnChoice.getChildren().add(title);



                //checks that the number of columns in the data set does not exceed 60
                if (columnNames.length <= 60 && columnNames.length >= 2) {

                    System.out.println("Column headers displayed.");

                    colNames = new ArrayList<>();
                    checkBoxes = new ArrayList<>();

                    //Displays each column header in a checkbox on the left side of the UI
                    for (int i = 0; i < columnNames.length; i++) {

                        //creating checkboxes for each file header in the file
                        columnHeaders = new CheckBox(columnNames[i]);

                        columnHeaders.setId(columnNames[i]);  //setting the column headers id to their respective names

                        checkBoxes.add(columnHeaders);   //Adding the checkbox to the arraylist

                        colNames.add(columnNames[i]);    //Adding the header names to the arraylist colNames

                        //Adding the checkboxes to the columnChoice VBox
                        columnChoice.getChildren().add(columnHeaders);
                    }

                    //setting the arraylist of checkboxes to the file object
                    file.setCheckBoxHeaders(checkBoxes);

                    //setting the arraylist of column names to the file object
                    file.setcNames(colNames);

                    //Displays button 1
                    //Adding it to the columnChoice VBox
                    //--------------------------------------------------------------------------------------------------Display table button
                    displayTable = new Button("Display table");
                    Tooltip disTable = new Tooltip("Click to display selected columns.");
                    displayTable.setTooltip(disTable);
                    displayTable.setStyle("-fx-background-color: rgb( 51, 170, 168)");

                    //--------------------------------------------------------------------------------------------------sort in ascending order button
                    asc = new Button("Sort asc");
                    Tooltip disAsc = new Tooltip("Click to sort in ascending order the selected columns");
                    asc.setTooltip(disAsc);
                    asc.setStyle("-fx-background-color: rgb( 156, 216, 213)");

                    //--------------------------------------------------------------------------------------------------sort in descending order button
                    dsc = new Button("Sort dsc");
                    Tooltip disDsc = new Tooltip("Click to sort in descending order the selected columns");
                    dsc.setTooltip(disDsc);
                    dsc.setStyle("-fx-background-color: rgb( 156, 216, 213)");

                    //--------------------------------------------------------------------------------------------------hbox that contains the sort buttons
                    HBox sortButtons = new HBox();
                    sortButtons.setSpacing(5);
                    sortButtons.getChildren().addAll(asc,dsc);

                    //--------------------------------------------------------------------------------------------------Correlation coefficient button
                    correlation = new Button("Correlation coefficient");
                    correlation.setId(tabID);
                    correlation.setStyle("-fx-background-color: rgb(224, 224, 224)");
                    Tooltip corrF = new Tooltip("Select 2 columns to find their correlation coefficient");
                    correlation.setTooltip(corrF);

                    //--------------------------------------------------------------------------------------------------ANOVA button
                    anova = new Button("ANOVA");
                    anova.setStyle("-fx-background-color: rgb(224, 224, 224)");
                    Tooltip tAnova = new Tooltip("Select 2 or more columns to calculate the anova");
                    anova.setTooltip(tAnova);

                    //--------------------------------------------------------------------------------------------------Display charts button
                    chart = new Button("Display charts");
                    chart.setStyle("-fx-background-color: rgb(224, 224, 224)");
                    Tooltip tChart = new Tooltip("Select all the columns you wish to represent on charts");
                    chart.setTooltip(tChart);


                    //--------------------------------------------------------------------------------------------------Make pane scrollable
                    scrollPane = new ScrollPane();
                    scrollPane.setContent(columnChoice);

                    //--------------------------------------------------------------------------------------------------VBox containing the buttons
                    VBox leftButtons = new VBox();
                    leftButtons.setStyle("-fx-background-color: rgb(249, 249, 249)");
                    leftButtons.setPrefHeight(100);
                    leftButtons.setPrefWidth(200);
                    leftButtons.setSpacing(10);
                    leftButtons.setPadding(new Insets(10,10,10,10));
                    leftButtons.getChildren().addAll(displayTable,sortButtons,correlation,anova,chart);

                    //--------------------------------------------------------------------------------------------------VBox that is found on the left
                    //--------------------------------------------------------------------------------------------------contains the checkboxes and buttons
                    VBox leftPanel = new VBox();
                    leftPanel.setMinWidth(200);
                    leftPanel.getChildren().addAll(scrollPane,leftButtons);

                    //adding vbox scrollpane to hbox
                    tabContent.getChildren().add(leftPanel);


                    //Area where the data tables will appear
                    HBox grids = grids();

                    //Making the area where the data columns are displayed scrollable
                    ScrollPane scrollGrids = addScrollPane();

                    //Set action event for when display table button is pressed
                    displayTable.setOnAction(event1 -> {

                        System.out.println("Display table button pressed.");
                        logFile.addToLog("Display table function is pressed");

                        //calling displayTableFunction function
                        displayTableFunction("n",tabContent, grids, scrollGrids);

                    });

                    //--------------------------------------------------------------------------------------------------event on clicking the correlation
                    //--------------------------------------------------------------------------------------------------coefficient button
                    correlation.setOnAction(event2 -> {

                        logFile.addToLog("Correlation coefficient button is pressed");
                        correlationFunction();
                    });

                    //--------------------------------------------------------------------------------------------------event for the anova button
                    anova.setOnAction(event -> {

                        logFile.addToLog("Anova button is pressed");
                        anovaFunction();
                    });

                    //--------------------------------------------------------------------------------------------------event for the display charts button
                    chart.setOnAction(event -> {

                        logFile.addToLog("Chart button is pressed");
                        displayChartWindow();
                    });

                    //--------------------------------------------------------------------------------------------------action when sort ascending button is pressed
                    asc.setOnAction(e -> {

                        logFile.addToLog("Sort ascending button is pressed");
                        displayTableFunction("a",tabContent,grids,scrollGrids);

                    });

                    //--------------------------------------------------------------------------------------------------action when sort descending is pressed
                    dsc.setOnAction(e -> {

                        logFile.addToLog("Sort descending button is pressed");
                        displayTableFunction("d",tabContent,grids,scrollGrids);

                    });

                    //setting the content of the tab to the choice of columns
                    tab.setContent(tabContent);

                    //adding tab to tabPanes
                    tabPane.getTabs().add(tab);

                    //Adding all files to files arraylist
                    files.add(file);

                    //Remove a DataFile from the files array once its tab has been closed
                        for (Tab t : tabPane.getTabs()) {
                            t.setOnClosed(e -> {
                                try {
                                    for (DataFile f : files) {
                                        if (f.getName().equals(t.getId())) {
                                            System.out.println("File " + f.getName() + "is closed.");
                                            logFile.addToLog("File " + f.getName() + " is closed");
                                            files.remove(f);
                                        } else {

                                        }
                                    }
                                }catch(Exception e1){

                                    System.out.println("All tabs closed.");
                                }

                            });
                        }



                    //remove file fro the arraylist once it has been opened
                    //so that the tabpane does not duplicate the tabs
                    currentFile.remove(file);

                //if the data file imported has less than 2 or more than 60 columns an error pop up will be displayed
                }else if(columnNames.length < minCol){
                    //display error if dataset contains more than 60 attributes
                    alertBox.display("Error", "The dataset contains less than 2 attributes");
                    logFile.addToLog("Error: The file contains less than 2 attributes");
                    //System.out.println("Error: The file chosen contains more than 60 attributes");
                    currentFile.remove(file);

                }else if(columnNames.length > maxCols){

                    alertBox.display("Error", "The dataset contains more than 60 attributes");
                    logFile.addToLog("Error: The file contains more than 60 attributes");
                    //System.out.println("Error: The file chosen contains more than 60 attributes");
                    currentFile.remove(file);

                }
            }
        }catch (Exception e1){
            //e1.printStackTrace();
            //System.out.println("Error to open file for column");
            //alertBox.display("Error","An error has occured when opening the file");

        }

    }

    /**
     * function that will display the data for each selected column name in its own column along with the required
     * statistical information
     * @param type - "n" is to display table normally, "a" is to display table in ascending order, "d" is to display in descending order
     * @param tabcontent - hbox that will contain all the data columns
     * @param hGrid - gridpane that will contain, for each column, the name, the data and the statistical information
     * @param scrollHGrid - make the whole hbox with the data columns scrollable
     */
    private void displayTableFunction(String type,HBox tabcontent, HBox hGrid, ScrollPane scrollHGrid){

        //HBox grids = grids();

        //Clearing the data column area every time the display column button is pressed
        hGrid.getChildren().clear();

        //Arraylist that will contain the names of the columns selected by the user
        ArrayList<String> colsToDisplay = new ArrayList<>();

        //DataFile temp = new DataFile();
        //Get the ID of the currently viewed tab
        try {
            tabID = tabPane.getSelectionModel().getSelectedItem().getId();
            //System.out.println("Tab " + tabID + "is being manipulated by user.");
            logFile.addToLog("Tab " + tabID + "is being manipulated by user.");

        }catch (Exception e){

        }

        selectedCheckbox(colsToDisplay,files,tabID);

        readFile(files,tabID,path,data,cData);

            try {

                for (DataFile f : files) {

                    //Searches for the file that matches the tab ID
                    if (f.getName().equals(tabID)) {
                        //grids = new ArrayList<>();

                        //For all the columns selected by the user
                        for (String s : colsToDisplay) {

                            //System.out.println("Column selected: " + s);
                            logFile.addToLog("Column selected: " + s);

                            dataPane = new GridPane();    //creating a new gridpane for each column selected

                            //for all the columns in the data file
                            for (ColumnData c : f.getColData()) {

                                //Check which column was selected by the user and display these tables
                                if (c.getName().equals(s)) {

                                    VBox dataColumn = stats(c);

                                    String check = checkVariable.checkVar(c.getData());

                                    //check the data type
                                    if(check.equals("Integer") || check.equals("Double")) {

                                        //sort in ascending order
                                        if (type.equals("a")) {

                                            ArrayList<Double> doubles = numericalSorting.sortAsc(c.getData());

                                            for (Double d : doubles) {

                                                Text dataRow = new Text(d.toString());

                                                dataColumn.getChildren().add(dataRow);
                                            }
                                        //sort in descending order
                                        } else if (type.equals("d")) {

                                            ArrayList<Double> doubles = numericalSorting.sortDesc(c.getData());

                                            for (Double d : doubles) {

                                                Text dataRow = new Text(d.toString());

                                                dataColumn.getChildren().add(dataRow);
                                            }

                                        }else if(type.equals("n")) {
                                            for (Object o : c.getData()) {

                                                //System.out.println(st);

                                                Text dataRow = new Text(o.toString());

                                                //adding each data value to the vbox dataColumn
                                                dataColumn.getChildren().add(dataRow);

                                            }

                                        }

                                    }else if(check.equals("String") || check.equals("Char") || check.equals("Boolean")){

                                        if(type.equals("a")){

                                            ArrayList<String> strings = numericalSorting.catSortAsc(c.getData());

                                            for(String st: strings){

                                                Text dataRow = new Text(st);

                                                dataColumn.getChildren().add(dataRow);

                                            }
                                        }else if(type.equals("d")){

                                            ArrayList<String> strings = numericalSorting.catSortDcs(c.getData());

                                            for(String st: strings){

                                                Text dataRow = new Text(st);

                                                dataColumn.getChildren().add(dataRow);

                                            }

                                        }else if(type.equals("n")) {

                                            for (Object o : c.getData()) {

                                                //System.out.println(st);

                                                Text dataRow = new Text(o.toString());

                                                //adding each data value to the vbox dataColumn
                                                dataColumn.getChildren().add(dataRow);

                                            }

                                        }

                                    }

                                    //adding the vbox dataColumn to scrollData to make it scrollable
                                    scrollData.setContent(dataColumn);

                                }
                            }
                            //adding the data values to the 2nd grid position in the gridpane
                            dataPane.add(scrollData, 0, 1);

                            //tabcontent.getChildren().add(dataPane);
                            //grids.add(dataPane);
                            //adding the gridpanes dataPane in the hGrid hbox to be displayed horizontally
                            hGrid.getChildren().add(dataPane);
                            //making hGrid scrollable
                            scrollHGrid.setContent(hGrid);

                        }
                        //adding the scroll grids to the main layout of each tab
                        tabcontent.getChildren().add(scrollHGrid);

                    }
                }
            }catch (Exception e){

                logFile.addToLog("Error has occurred when trying display the data columns.");

            }

    }

    /**
     * function that will give the statistical information for each data column
     * @param c data column
     * @return vbox for the data column
     */
    private VBox stats(ColumnData c){

            //Make the area containing the data columns scrollable
            scrollData = new ScrollPane();

            //vbox that will contain the data values
            VBox dataColumn = dataCol();

            //vbox that that will contain the column header
            VBox header = new VBox();
            header.setPrefWidth(180);
            header.setPrefHeight(25);
            Text colName = new Text(c.getName());

            colName.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 16));
            header.getChildren().add(colName);
            header.setAlignment(Pos.CENTER);


            //adding the column header to the first top grid of the grid pane
            dataPane.add(header, 0, 0);

            //check the data type of the values in this particular data column
            String check = checkVariable.checkVar(c.getData());

            //System.out.println(check);

            //if the data column is a string or char type
            //display length, distinct and frequency and missinf values
            if (check.equals("String") || check.equals("Char") || check.equals("Boolean")) {
                Map distinct = Distinct.getDistinct(c.getData());

                int missVal = Statistics.missingData(c.getData());

                Text distinctText;

                VBox distinctBox = new VBox();
                distinctBox.setStyle("-fx-background-color: rgb(247, 247, 247)");

                distinct.forEach((k, v) -> {

                    Text t = new Text("->" + k + " = " + v);

                    distinctBox.getChildren().add(t);

                });

                VBox allDistinct = new VBox();


                //check if the number of distinct values is not over 20
                //otherwise it will not be displayed
                if (distinct.size() <= maxDistinct) {

                    distinctText = new Text("Data type: " + check + "\n" +
                            "Length of data: " + c.getData().size() + "\n" +
                            "No of missing values: " + missVal + "\n" +
                            "No of elements: " + distinct.size() + "\n" +
                            "Distinct elements: ");

                    allDistinct.getChildren().addAll(distinctText, distinctBox);

                    displayStats(allDistinct, dataPane);

                } else {
                    distinctText = new Text("Data type: " + check + "\n" +
                            "Length of data: " + c.getData().size() + "\n" +
                            "No of missing values: " + missVal + "\n" +
                            "Number of distinct " + "\n" +
                            "elements exceed 20!");

                    allDistinct.getChildren().addAll(distinctText);

                    displayStats(allDistinct, dataPane);

                }

                //adding the stats to be displayed in a vbox in the 3rd grid of the datapane


                //display stats if data type id int or double
            } else if (check.equals("Integer") || check.equals("Double")) {

                //calling stats functions
                int missVal = Statistics.missingData(c.getData());
                double sum = Statistics.getSum(c.getData());
                double mean = Statistics.getMean(c.getData(), sum);
                double variance = Statistics.getVariance((c.getData()), mean);
                double stDeviation = Statistics.getStDeviation(variance);
                double median = Statistics.getMedian(c.getData());

                DecimalFormat df = new DecimalFormat("0.####");


                VBox allDouble = new VBox();

                //text that will be displayed
                Text doubleText = new Text("Data type: " + check + "\n" +
                        "Length of data: " + c.getData().size() + "\n" +
                        "Sum of data: " + df.format(sum) + "\n" +
                        "Min value: " + Statistics.getMin(c.getData()) + "\n" +
                        "Max value: " + Statistics.getMax(c.getData()) + "\n" +
                        "Mean: " + df.format(mean) + "\n" +
                        "Median: " + median + "\n" +
                        "Variance: " + df.format(variance) + "\n" +
                        "Std. dev: " + df.format(stDeviation) + "\n" +
                        "Missing values: " + missVal);

                doubleText.setFont(Font.font("Source sans pro", 13));

                //display a warning if there are/is missing value(s) in the data set
                //informing thr=e user that these are noy taken into consideration when getting the statistical values
                if(missVal >= 1){

                    Text warning = new Text("The statistical values displayed" + "\n" +
                                            "do not include missing values!");
                    warning.setFill(Color.rgb(244, 36, 29));

                    allDouble.getChildren().addAll( warning,doubleText);

                }else {

                    allDouble.getChildren().addAll(doubleText);
                }


                displayStats(allDouble, dataPane);

            }

        return dataColumn;

    }

    /**
     * window that pop up containing the correlation coefficient for the two numerical column selected
     */
    private void correlationFunction(){

        // get the current tab which is opened
        try {
            tabID = tabPane.getSelectionModel().getSelectedItem().getId();
            //System.out.println("Tab " + tabID + "is being manipulated by user.");
            logFile.addToLog("Tab " + tabID + "is being manipulated by user.");
        }catch (Exception e){

        }

        //get the data from the file
        readFile(files,tabID,path,data,cData);

        //arraylist containing the column name that are selected by user
        ArrayList<String> corrCols = new ArrayList<>();

        //arraylist that will conatining the two column data
        ArrayList<ColumnData> corrData = new ArrayList<>();

        //get which checkbox is selected
        selectedCheckbox(corrCols, files,tabID);

        //checking how many columns were selected
        //only two allowed
        if(corrCols.size() > 2){

            alertBox.display("Error","Please select only 2 columns to get their correlation coefficient");
            logFile.addToLog("Error: More than 2 columns were selected for correlation coefficient");

        }else if(corrCols.size() < 2){

            alertBox.display("Error","Please select 2 columns to get their correlation coefficient");
            logFile.addToLog("Error: Less than 2 columns were selected for correlation coefficient");

        }else if(corrCols.size() == 2){

            //checking whether the two columns selected contain the appropriate data type required to calculate the correlation coefficient
            for(DataFile f: files){

                //Searches for the file that matches the tab ID
                if(f.getName().equals(tabID)){

                    for(String s:corrCols){

                        //for all the columns in the data file
                        for(ColumnData c: f.getColData()){

                            //Check which column was selected by the user and display these tables
                            if(c.getName().equals(s)){
                                String check = checkVariable.checkVar(c.getData());

                                if(check.equals("Integer") || check.equals("Double")) {

                                   // System.out.println(s);
                                    corrData.add(c);
                                }else{

                                    alertBox.display("Error","One or more of the selected " + "\n" +
                                            "column(s) contain(s) an unsuitable data type " + "\n" +
                                            "for correlation coefficient");
                                    logFile.addToLog("Error: One or more of the selected column9s0 contain(s) an unsuitable data type");

                                }
                            }
                        }
                    }
                }
            }

            //for(ColumnData c:corrData){
                //System.out.println(c.getName());
            //}

            //parsing the data columns to the correlationCoefficient window
            try {

                correlationCoefficient.corrCoefWindow(corrData.get(0), corrData.get(1));
                logFile.addToLog("Correlation coefficient between "+corrData.get(0).getName() + " and " +corrData.get(1).getName());

            }catch (Exception e){

                logFile.addToLog("Error occurred when trying to check for the correlation coefficient");

            }
        }


    }

    /**
     * function to calculate the analysis of variance for two or more numerical columns
     */
    private void anovaFunction(){

        try {
            tabID = tabPane.getSelectionModel().getSelectedItem().getId();
           // System.out.println("Tab " + tabID + "is being manipulated by user.");
            logFile.addToLog("Tab " + tabID + " is being manipulated by user");

        }catch (Exception e){

        }

        ArrayList<String> anovaCols = new ArrayList<>();

        ArrayList<ColumnData> anovaData = new ArrayList<>();

        readFile(files,tabID,path,data,cData);

        selectedCheckbox(anovaCols, files,tabID);

        //there should be more than one selected column in order to calculate the anova
        if(anovaCols.size() < 2){
            alertBox.display("Error","Please select at least 2 columns to get the anova");
            logFile.addToLog("Error: Less than 2 columns were selected to calculate the anova");

        }else {

            for (DataFile f : files) {

                //Searches for the file that matches the tab ID
                if (f.getName().equals(tabID)) {

                    logFile.addToLog("Columns selected to get the anova:");

                    for (String s : anovaCols) {

                        //for all the columns in the data file
                        for (ColumnData c : f.getColData()) {

                            //Check which column was selected by the user and display these tables
                            if (c.getName().equals(s)) {

                                logFile.addToLog("- " + c.getName());

                                //checking if the appropriate data type were selected
                                String check = checkVariable.checkVar(c.getData());

                                if (check.equals("Integer") || check.equals("Double")) {

                                    anovaData.add(c);

                                }else{

                                    alertBox.display("Error","One or more of the selected " + "\n" +
                                            "column(s) contains an unsuitable data type " + "\n" +
                                            "for ANOVA");

                                }
                            }
                        }
                    }
                }
            }
        }

        try {

            //display anova window
            AnovaWindow.anovaWindow(anovaData);

        }catch (Exception e){

        }

        //for(ColumnData c: anovaData){
            //System.out.println(c.getName());
        //}

    }

    /**
     * function to display the charts window
     */
    private void displayChartWindow(){

        try {
            tabID = tabPane.getSelectionModel().getSelectedItem().getId();
           // System.out.println("Tab " + tabID + "is being manipulated by user.");
            logFile.addToLog("Tab " + tabID + " is being manipulated by user");
        }catch (Exception e){

        }

        ArrayList<String> chartCols = new ArrayList<>();

        readFile(files,tabID,path,data,cData);

        selectedCheckbox(chartCols, files,tabID);

        if(chartCols.size() < 1){

            alertBox.display("Error", "Please select at least one or more" + "\n" +
                                        "to display the charts.");

        }else {

            logFile.addToLog("Chart window displayed");

            chartWindow.display(chartCols, files, tabPane);

        }

    }

    /**
     * vbox containing the data values
     * @return vbox
     */
    private VBox dataCol(){

        VBox vbox = new VBox();
        vbox.setPrefWidth(180);
        vbox.setPrefHeight(400);
        vbox.setAlignment(Pos.TOP_LEFT);

        return vbox;
    }

    /**
     * hbox that will contain the data columns
     * @return hbox
     */
    private HBox grids(){

        HBox hbox = new HBox();
        //hbox.setPrefWidth(1100);

        return hbox;
    }

    /**
     * create a new vbox to contain the statistical information
     * @param v
     * @param grid
     */
    private void displayStats(VBox v, GridPane grid){

        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: rgb(224, 224, 224)");
        vbox.setPrefWidth(180);
        vbox.setPrefHeight(170);
        vbox.getChildren().add(v);

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(vbox);

        grid.add(scroll,0,3);

    }

    /**
     * function to get the checkboxes selected in a particular tab
     * @param s arraylist that will contain the names of the columns selected
     * @param dF arraylist of all the data files imported
     * @param id of the current tab opened
     */
    private static void selectedCheckbox(ArrayList<String> s, ArrayList<DataFile> dF, String id){

        //Getting all the checkboxes that are selected
        //and adding those that are selected in an arraylist
        try {
            for (DataFile f : dF) {
                if (id.equals(f.getName())) {
                    for (CheckBox c : f.getCheckBoxHeaders()) {
                        if (c.isSelected()) {
                            s.add(c.getId());

                        }
                    }
                }
            }
        }catch(Exception e){

            logFile.addToLog("Error occurred when selecting check boxes for headers");

        }
    }

    /**
     * function to get the data in the body of the csv file imported
     * @param dfX arraylist of datafiles imported
     * @param idX id of the opened tab
     * @param pathX path of the data file
     * @param oX 2d array that will contain the data in the body of the csv file
     * @param cdX arraylist that will be set as the arraylist of column data for a particular data file
     */
    private static void readFile(ArrayList<DataFile> dfX, String idX, String pathX, Object[][] oX, ArrayList<ColumnData> cdX) {

        ArrayList<String> temp = new ArrayList<>();

        for (DataFile f : dfX) {
            if (idX.equals(f.getName())) {
                //temp.setcNames(f.getcNames());
                temp = f.getcNames();
                //System.out.println(tabID);
                pathX = f.getPath();
                //System.out.println(tabPath);
            } else {

            }
        }

        //Read the csv file's body
        try {

            oX = ReadCSV.readFile(pathX);
            //System.out.println(data[0].length);
            //System.out.println(data.length);

            int numCol = oX[0].length;
            int numRow = oX.length;

            ArrayList<Object> a;
            ColumnData columnData;
            cdX = new ArrayList<>();


            //for each column in oX[][]
            for (int i = 0; i < numCol; i = i + 1) {

                columnData = new ColumnData();

                columnData.setName(temp.get(i));
                //System.out.println(temp.getcNames().get(i));

                a = new ArrayList<>();

                //for each row in oX[][]
                for (int j = 0; j < numRow; j = j + 1) {

                    Object s = oX[j][i];

                    //check if a record is null and replace it with a space " "
                    if (s == null || s.toString().equals(null) || s.toString().length() == 0) {

                        a.add(" ");

                    } else {
                        a.add(s);
                    }

                    //System.out.println(s);

                }
                columnData.setData(a);

                cdX.add(columnData);

                //file.getColData().add(columnData);
            }

        }catch(Exception e){

            logFile.addToLog("Error occurred when trying to read csv file");

        }

        //Assigning arraylist of data to columnData
        for(DataFile f: dfX){
            if(f.getName().equals(idX)){
                f.setColData(cdX);

            }
        }
    }

}
