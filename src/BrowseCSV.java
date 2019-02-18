import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;


public class BrowseCSV extends Application{

    BorderPane borderPane;

    MenuBar menuBar;

    Menu file, help;

    MenuItem browseFile, save, exit,manual;

    Button button1, test;

    ArrayList<DataFile> files, backupFiles;

    Object [][] data;

    TabPane tabPane;

    Tab tab;

    String path = null;

    String[] columnNames = null;

    TableView tableView;

    CheckBox columnHeaders;



    public static void main ( String[] args){

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Data Visualizer");

        files = new ArrayList<>();
        backupFiles = new ArrayList<>();

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

        //Sample button
        button1 = new Button("Display table");

        //tabpane
        tabPane = new TabPane();





        //opens the file browser and saves each file selected in an arraylist
        browseFile.setOnAction(e -> {

            fileBrowser(primaryStage, files);

            try {

                for (DataFile file : files) {

                    path = file.getPath();

                    tab = new Tab(file.getName());

                    tabPane.getTabs().add(tab);


                    //backing up deleted files so that they can be used again if needed
                    backupFiles.add(file);

                    //remove file fro the arraylist once it has been opened
                    //so that the tabpane does not duplicate the tabs
                    files.remove(file);
                }
            }catch (Exception e1){

            }

            /*for(DataFile file: files){
               System.out.println(file.getName());
                System.out.println(file.getPath());
            }*/
        });



        //Setting tab pane to top
        tabPane.setSide(Side.TOP);

        //assigning items to file tab
        file.getItems().addAll(browseFile,save);
        //assigning items to help tab
        help.getItems().addAll(exit,manual);

        //assigning menu tabs to menu bar
        menuBar.getMenus().addAll(file,help);



        //setting nodes to border pane
        borderPane.setTop(menuBar);
        //borderPane.setTop(button1);
        //borderPane.setLeft(button1);
        //borderPane.setLeft(leftpanel);
        borderPane.setCenter(tabPane);

        //creating new scene
        Scene scene = new Scene(borderPane, 1000, 600);

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
        File selectedFile = fileChooser.showOpenDialog(stage);

        dataFile.setName(selectedFile.getName());
        dataFile.setPath(selectedFile.getPath());

        paths.add(dataFile);

        //System.out.println(dataFile.getName());
        //System.out.println(dataFile.getPath());


    }





}
