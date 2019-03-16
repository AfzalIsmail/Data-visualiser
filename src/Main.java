import com.sun.tools.javac.file.SymbolArchive;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 */
public class Main extends Application{

    BorderPane borderPane;

    MenuBar menuBar;

    Menu file, help;

    MenuItem browseFile, save, exit,manual;

    Button displayTable, importFile, saveData;

    ArrayList<DataFile> currentFile, files = new ArrayList<>();

    ArrayList<String> colNames;

    ArrayList<ColumnData> cData;

    Object[][] data;

    TabPane tabPane;

    Tab tab;

    String path = null;

    String[] columnNames = null;

    CheckBox columnHeaders;

    ScrollPane scrollPane;

    String tabID,tabPath;

    ArrayList<CheckBox> checkBoxes;

    GridPane dataPane;

    ScrollPane scrollData;


    /**
     *
     * @param args
     */
    public static void main ( String[] args){

        launch(args);
    }

    /**
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("DVision");

        currentFile = new ArrayList<>();
        files = new ArrayList<>();

        //layout of application
        borderPane = new BorderPane();

        //creating menu bar which will be found on top
        menuBar = new MenuBar();
        file = new Menu("File");
        help = new Menu("Help");

        Image loadFile = new Image("picture/load.png");

        ImageView loadFileView = new ImageView(loadFile);
        loadFileView.setFitHeight(20);
        loadFileView.setFitWidth(20);
        importFile = new Button();
        importFile.setOnMouseEntered(e -> importFile.setStyle("-fx-background-color: rgb(99, 99, 99)"));
        importFile.setOnMouseExited(e -> importFile.setStyle("-fx-background-color: rgb(68, 69, 71)"));
        importFile.setPadding(new Insets(2,10,2,10));
        importFile.setStyle("-fx-background-color: rgb(68, 69, 71)");
        importFile.setGraphic(loadFileView);
        importFile.setOnAction(e -> displayHeaders(primaryStage));

        Image saveFile = new Image("picture/save1.png");

        ImageView saveFileView = new ImageView(saveFile);
        saveFileView.setFitWidth(22);
        saveFileView.setFitHeight(22);
        saveData = new Button();
        saveData.setOnMouseEntered(e -> saveData.setStyle("-fx-background-color: rgb(99, 99, 99)"));
        saveData.setOnMouseExited(e -> saveData.setStyle("-fx-background-color: rgb(68, 69, 71)"));
        saveData.setPrefHeight(24);
        saveData.setPadding(new Insets(2,10,0,10));
        saveData.setStyle("-fx-background-color: rgb(68, 69, 71)");
        saveData.setGraphic(saveFileView);

        HBox subMenu = addSubMenuBar();
        subMenu.getChildren().addAll(importFile,saveData);

        //Menu items for file tab
        browseFile = new MenuItem("Browse File");
        save = new MenuItem("Save");

        //Menu items for help tab
        exit = new MenuItem("Exit");
        manual = new MenuItem("Help");

        //tabpane
        tabPane = new TabPane();

        //opens the file browser and saves each file selected in an arraylist
        browseFile.setOnAction(e -> {
            displayHeaders(primaryStage);

        });

        //Testing log messages
        //help.setOnAction(e -> logFile.printLog());
        help.setOnAction(e -> System.out.println(tabPane.getSelectionModel().getSelectedItem().getId()));


        //Setting tab pane to top
        tabPane.setSide(Side.TOP);

        //assigning items to file tab
        file.getItems().addAll(browseFile,save);
        //assigning items to help tab
        help.getItems().addAll(exit,manual);

        //assigning menu tabs to menu bar
        menuBar.getMenus().addAll(file,help);

        menuBar.setStyle("-fx-background-color: rgb( 51, 170, 168)");

        Image logo = new Image("picture/logo2.png");
        ImageView logoView = new ImageView(logo);

        logoView.setX(0);
        logoView.setY(0);
        logoView.setFitHeight(60);
        logoView.setFitWidth(68);

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

        primaryStage.getIcons().add(logo);
        primaryStage.setScene(scene);

        //window cannot be resized
        primaryStage.setResizable(false);
        primaryStage.show();

    }

    /**
     * Function to open file browser and store the name and path of each file in an arraylist
     * @param stage
     */
    public void fileBrowser(Stage stage, ArrayList<DataFile> paths){

        DataFile dataFile = new DataFile();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse a file");
        fileChooser.getExtensionFilters().add( new FileChooser.ExtensionFilter("CSV Files","*.csv"));
        File selectedFile = fileChooser.showOpenDialog(stage);

        logFile.addToLog("File " + selectedFile.getName() + " is choosen");


        dataFile.setName(selectedFile.getName());
        dataFile.setPath(selectedFile.getPath());

        paths.add(dataFile);

        //System.out.println(dataFile.getName());
        //System.out.println(dataFile.getPath());
    }

    /**
     * To add the choiceBox to a VBox to be displayed in the BorderPane
     * @return vbox
     */
    public VBox addVBox(){
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.setPrefWidth(200);
        vbox.setMaxHeight(300);
        //vbox.setStyle("-fx-background-color: rgb(119, 214, 211)");

        //Text title = new Text("Data columns");

        //vbox.getChildren().add(title);

        return vbox;
    }

    /**
     *
     * @return
     */
    public VBox headerArea(){

        VBox vBox = new VBox();

        return vBox;
    }

    /**
     * Creating HBoxes that can be added to other types of layouts
     * @return hBox
     */
    public HBox addHBox(){
        HBox hBox = new HBox();

        return hBox;
    }

    /**
     *
     * @return
     */
    public HBox addSubMenuBar(){
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
    public ScrollPane addScrollPane(){
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefViewportWidth(1200);

        return scrollPane;
    }

    /**
     * Function to read only the headers in the csv files in the files arraylist
     * and display them in their appropriate tab
     * @param stage
     */
    public void displayHeaders(Stage stage){

        //Keeping log of when browseFile is pressed
        logFile.addToLog("File browser opened");

        //calling the filebrowser function
        fileBrowser(stage, currentFile);

        try {
            //iterates over files arraylist
            for (DataFile file : currentFile) {

                //HBox that will contain the
                HBox tabContent = addHBox();

                //VBox that will contain the choice for the columns to be displayed
                VBox columnChoice = addVBox();
                columnChoice.setStyle("-fx-background-color: rgb(224, 224, 224)");



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

                Text filePath = new Text("File path: " + path);
                filePath.setStyle("-fx-fill: rgb(79, 86, 84)");
                columnChoice.getChildren().add(filePath);

                Text colNum = new Text("Number of columns: " + file.getColNum());
                colNum.setStyle("-fx-fill: rgb(79, 86, 84)");
                columnChoice.getChildren().add(colNum);

                Text title = new Text("Data columns:");
                columnChoice.getChildren().add(title);



                //checks that the number of columns in the data set does not exceed 60
                if (columnNames.length <= 60) {
                    colNames = new ArrayList<>();
                    checkBoxes = new ArrayList<>();

                    //Displays each column header in a checkbox on the left side of the UI
                    for (int i = 0; i < columnNames.length; i++) {

                        //creating checkboxes for each file header in the file
                        columnHeaders = new CheckBox(columnNames[i]);

                        columnHeaders.setId(columnNames[i]);

                        checkBoxes.add(columnHeaders);

                        colNames.add(columnNames[i]);

                        //Adding the checkboxes to the columnChoice VBox
                        columnChoice.getChildren().add(columnHeaders);
                    }

                    file.setCheckBoxHeaders(checkBoxes);

                    file.setcNames(colNames);

                    //Displays button 1
                    //Adding it to the columnChoice VBox
                    displayTable = new Button("Display table");
                    displayTable.setStyle("-fx-background-color: rgb( 51, 170, 168)");
                    columnChoice.getChildren().add(displayTable);

                    //Make pane scrollable
                    scrollPane = new ScrollPane();
                    scrollPane.setContent(columnChoice);

                    

                    //adding vbox scrollpane to hbox
                    tabContent.getChildren().add(scrollPane);


                    //Area where the data tables will appear
                    HBox grids = grids();


                    ScrollPane scrollGrids = addScrollPane();

                    //Set action event for when button1id pressed
                    displayTable.setOnAction(event1 -> {

                        displayTableFunction(tabContent, grids, scrollGrids);


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
                                            files.remove(f);
                                        } else {

                                        }
                                    }
                                }catch(Exception e1){

                                }

                            });
                        }



                    //remove file fro the arraylist once it has been opened
                    //so that the tabpane does not duplicate the tabs
                    currentFile.remove(file);

                }else{
                    //display error if dataset contains more than 60 attributes
                    alertBox.display("Error", "The dataset contains more than 60 attributes");
                    logFile.addToLog("Error: The file contains more than 60 attributes");
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
     * Function that are associated with button1 when it is pressed
     */
    public void displayTableFunction(HBox tabcontent, HBox hGrid, ScrollPane scrollHGrid){

        //HBox grids = grids();

        hGrid.getChildren().clear();

        ArrayList<String> colsToDisplay = new ArrayList<>();


        //DataFile temp = new DataFile();
        //Get the ID of the currently viewed tab
        tabID = tabPane.getSelectionModel().getSelectedItem().getId();

        //Getting all the checkboxes that are selected
        //and adding those that are selected in an arraylist
        try {
            for (DataFile f : files) {
                if (tabID.equals(f.getName())) {
                    for (CheckBox c : f.getCheckBoxHeaders()) {
                        if (c.isSelected()) {
                            colsToDisplay.add(c.getId());

                        }
                    }
                }
            }
        }catch(Exception e){

        }

        //Testing
        for(String s:colsToDisplay){
            //System.out.println(s);
        }

        //temp.setName(tabID);

        //Search for the file with the same ID as the current tab in the files array list
        //And get the path for that file
        ArrayList<String> temp = new ArrayList<>();

        for(DataFile f: files){
            if(tabID.equals(f.getName())){
                //temp.setcNames(f.getcNames());
                temp = f.getcNames();
                //System.out.println(tabID);
                tabPath = f.getPath();
                //System.out.println(tabPath);
            }else{

            }
        }

        //Read the csv file's body
        try {

            data = ReadCSV.readFile(tabPath);
            //System.out.println(data[0].length);
            //System.out.println(data.length);

            int numCol = data[0].length;
            int numRow = data.length;

            ArrayList<Object> a;
            ColumnData columnData;
            cData = new ArrayList<>();


            for (int i = 0; i < numCol; i = i + 1) {


                columnData = new ColumnData();

                columnData.setName(temp.get(i));
                //System.out.println(temp.getcNames().get(i));

                a = new ArrayList<>();

                for (int j = 0; j < numRow; j = j + 1) {

                    Object s = data[j][i];

                    if(s == null || s.toString().equals(null) || s.toString().length() == 0){

                        a.add(" ");

                    }else{
                        a.add(s);
                    }

                //System.out.println(s);

                }
                columnData.setData(a);

                cData.add(columnData);

                //file.getColData().add(columnData);
            }


            //Assigning arraylist of data to columnData
            for(DataFile f: files){
                if(f.getName().equals(tabID)){
                    f.setColData(cData);

                }
            }

            //Testing
            for(DataFile f: files){
                if(f.getName().equals(tabID)) {
                    for (ColumnData c : f.getColData()) {
                        //System.out.println(c.getName());
                        for (Object o : c.getData()) {
                            //System.out.println(s);
                        }
                    }
                }
            }

            for(DataFile f:files){

                if(f.getName().equals(tabID)){
                    //grids = new ArrayList<>();

                    //tabcontent.getChildren().remove(grids);
                    //hGrid.getChildren().clear();

                    for(String s: colsToDisplay){

                        dataPane = new GridPane();

                        for(ColumnData c: f.getColData()){

                            if(c.getName().equals(s)){

                                scrollData = new ScrollPane();

                                VBox dataColumn = dataCol();

                                VBox header = new VBox();
                                header.setPrefWidth(180);
                                header.setPrefHeight(25);
                                Text colName = new Text(c.getName());


                                colName.setFont(Font.font("arial",FontWeight.BOLD, FontPosture.REGULAR, 16));
                                header.getChildren().add(colName);
                                header.setAlignment(Pos.CENTER);

                                dataPane.add(header,0,0);

                                String check = checkVariable.checkVar(c.getData());

                                System.out.println(check);

                                if(check.equals("String") || check.equals("Char")) {
                                    Map distinct = Distinct.getDistinct(c.getData());
                                    //System.out.println("Length of data : " + c.getData().size());
                                    //System.out.println("Final distinct elements : " + distinct);
                                    //System.out.println("No of elements : " + distinct.size());

                                    int missVal = MissingValues.missingData(c.getData());

                                    Text distinctText;

                                    if(distinct.size() <= 20) {

                                         distinctText = new Text("Data type: " + check + "\n" +
                                                "Length of data: " + c.getData().size() + "\n" +
                                                "Final distinct elements: " + distinct + "\n" +
                                                "No of elements: " + distinct.size() + "\n" +
                                                "No of missing values: " + missVal);
                                    }else{
                                        distinctText = new Text("Data type: " + check + "\n" +
                                                "Length of data: " + c.getData().size() + "\n" +
                                                "No of missing values: " + missVal + "\n" +
                                                "Number of distinct " + "\n" +"elements exceed 20!");

                                    }

                                    displayStats(distinctText,dataPane);


                                }else if(check.equals("Integer") || check.equals("Double")){

                                    int missVal = MissingValues.missingData(c.getData());


                                    double sum = Statistics.getSum(c.getData());
                                    double mean = Statistics.getMean(c.getData(), sum);
                                    double variance = Statistics.getVariance((c.getData()), mean);
                                    double stDeviation = Statistics.getStDeviation(variance);

                                    //System.out.println(sum);
                                    //System.out.println(mean);
                                    System.out.println(variance);
                                    System.out.println(stDeviation);

                                    Text doubleText = new Text("Data type: " + check + "\n" +
                                            "Length of data: " + c.getData().size() + "\n" +
                                            "Sum of data: " + sum + "\n" +
                                            "Mean: " + mean + "\n" +
                                            "Variance: " + variance + "\n" +
                                            "Std. dev: " + stDeviation + "\n" +
                                            "Missing values: " + missVal);

                                    doubleText.setFont(Font.font("Source sans pro", 13));


                                    displayStats(doubleText,dataPane);

                                }

                                //System.out.println(c.getName());

                                for(Object o:c.getData()){

                                    //System.out.println(st);

                                    Text dataRow = null;

                                        dataRow = new Text(o.toString());


                                    dataColumn.getChildren().add(dataRow);


                                }

                                scrollData.setContent(dataColumn);

                            }
                        }
                        dataPane.add(scrollData, 0 ,1);

                        //tabcontent.getChildren().add(dataPane);
                        //grids.add(dataPane);
                        hGrid.getChildren().add(dataPane);
                        scrollHGrid.setContent(hGrid);

                    }

                    tabcontent.getChildren().add(scrollHGrid);

                }
            }

        } catch (Exception e1) {

        }

    }

    /**
     *
     * @return
     */
    public VBox dataCol(){

        VBox vbox = new VBox();
        vbox.setPrefWidth(180);
        vbox.setPrefHeight(400);
        vbox.setAlignment(Pos.CENTER_LEFT);

        return vbox;
    }

    /**
     *
     * @return
     */
    public HBox grids(){

        HBox hbox = new HBox();
        //hbox.setPrefWidth(1100);

        return hbox;
    }

    /**
     *
     * @param text
     * @param grid
     */
    public void displayStats(Text text, GridPane grid){

        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: rgb(224, 224, 224)");
        vbox.setPrefWidth(180);
        vbox.setPrefHeight(170);
        vbox.getChildren().add(text);

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(vbox);

        grid.add(scroll,0,3);

    }


}
