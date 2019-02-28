import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;


public class Main extends Application{

    private BorderPane borderPane;

    MenuBar menuBar;

    Menu file, help;

    MenuItem browseFile, save, exit,manual;

    Button button1;

    ArrayList<DataFile> currentFile, files = new ArrayList<>();

    ArrayList<String> colNames;

    ArrayList<ColumnData> cData;

    String[][] data;

    TabPane tabPane;

    Tab tab;

    String path = null;

    String[] columnNames = null;

    TableView tableView;

    CheckBox columnHeaders;

    ScrollBar scrollBar;

    ScrollPane scrollPane;

    DataFile dataFile;

    String tabID,tabPath;

    ArrayList<CheckBox> checkBoxes;



    public static void main ( String[] args){

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Data Visualizer");

        currentFile = new ArrayList<>();
        files = new ArrayList<>();

        //layout of application
        borderPane = new BorderPane();

        //creating menu bar which will be found on top
        menuBar = new MenuBar();
        file = new Menu("File");
        help = new Menu("Help");



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


        //Setting tab pane to top
        tabPane.setSide(Side.TOP);

        //assigning items to file tab
        file.getItems().addAll(browseFile,save);
        //assigning items to help tab
        help.getItems().addAll(exit,manual);

        //assigning menu tabs to menu bar
        menuBar.getMenus().addAll(file,help);

        menuBar.setStyle("-fx-background-color: rgb( 51, 170, 168)");

        Image logo = new Image("picture/logo.png");
        ImageView logoView = new ImageView(logo);

        logoView.setX(0);
        logoView.setY(0);
        logoView.setFitHeight(70);
        logoView.setFitWidth(100);


        VBox header = headerArea();
        header.setStyle("-fx-background-color: rgb(83, 93, 93)");
        header.getChildren().addAll(logoView,menuBar);



        //setting nodes to border pane
        borderPane.setTop(header);
        borderPane.setCenter(tabPane);
        //borderPane.getStylesheets().add("styleSheet.css");



        //creating new scene
        Scene scene = new Scene(borderPane, 1400, 750);
        //scene.getStylesheets().add("styleSheet.css");


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
        vbox.setStyle("-fx-background-color: rgb(119, 214, 211)");

        Text title = new Text("Data columns");

        vbox.getChildren().add(title);


        return vbox;

    }

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
     * Creating ScrollPanes that can be added into other types of layouts
     * @return scrollPane
     */
    public ScrollPane addScrollPane(){
        ScrollPane scrollPane = new ScrollPane();

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
                //columnChoice.setStyle("-fx-background-color: rgb(51, 135, 178)");


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
                    button1 = new Button("Display table");
                    columnChoice.getChildren().add(button1);
                    //columnChoice.maxHeight(200);

                    //Make pane scrollable
                    scrollPane = new ScrollPane();
                    scrollPane.setContent(columnChoice);
                    

                    //adding vbox scrollpane to hbox
                    tabContent.getChildren().add(scrollPane);

                    //setting the content of the tab to the choice of columns
                    tab.setContent(tabContent);


                    //adding tab to tabPanes
                    tabPane.getTabs().add(tab);
                    

                    //Set action event for when button1id pressed
                    button1.setOnAction(event1 -> {

                        button1Function();

                        for(DataFile f: files){
                            //System.out.println(f.getName());
                        }


                    });

                    //Adding all files to files arraylist
                    files.add(file);

                    //Remove a DataFile from the files array once its tab has been closed
                    for(Tab t: tabPane.getTabs()) {
                        t.setOnClosed(e -> {
                            for (DataFile f : files) {
                                if (f.getName().equals(t.getId())) {
                                    files.remove(f);
                                } else {

                                }
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

            /*for(DataFile file: files){
               System.out.println(file.getName());
                System.out.println(file.getPath());
            }*/
    }

    /**
     * Function that are associated with button1 when it is pressed
     */
    public void button1Function(){

        ArrayList<String> colsToDisplay = new ArrayList<>();


        //DataFile temp = new DataFile();
        //Get the ID of the currently viewed tab
        tabID = tabPane.getSelectionModel().getSelectedItem().getId();

        for(DataFile f: files){
            if(tabID.equals(f.getName())){
                for(CheckBox c: f.getCheckBoxHeaders()){
                    if(c.isSelected()){
                        colsToDisplay.add(c.getId());

                    }
                }
            }
        }

        for(String s:colsToDisplay){
            System.out.println(s);
        }

        //temp.setName(tabID);

        //Search for the file with the same ID as the current tab in the files array list
        //And get the path for that file
        ArrayList<String> temp = new ArrayList<>();

        for(DataFile f: files){
            if(tabID.equals(f.getName())){
                //temp.setcNames(f.getcNames());
                temp = f.getcNames();
                System.out.println(tabID);
                tabPath = f.getPath();
                System.out.println(tabPath);
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

            ArrayList<String> a;
            ColumnData columnData;
            cData = new ArrayList<>();


            for (int i = 0; i < numCol; i = i + 1) {


                columnData = new ColumnData();

                columnData.setName(temp.get(i));
                //System.out.println(temp.getcNames().get(i));

                a = new ArrayList<>();

                for (int j = 0; j < numRow; j = j + 1) {

                    String s = data[j][i];

                    a.add(s);

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
                        for (String s : c.getData()) {
                            //System.out.println(s);
                        }
                    }
                }
            }

                                        /*for (DataFile f1 : currentFile) {

                                            for (ColumnData c : f1.getColData()) {

                                                for (String s : c.getData()) {

                                                    //System.out.println(s);
                                                }
                                            }
                                        }*/


        } catch (Exception e1) {

        }


    //DataFile f1 = files.get(0);

    //for(ColumnData c : f.getColData()){

    //for(String s: c.getData()){

    // System.out.println(s);
    //}
    //}
    }



}
