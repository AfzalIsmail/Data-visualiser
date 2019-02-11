import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class BrowseCSV extends Application{

    BorderPane borderPane;

    MenuBar menuBar;

    Menu file, help;

    MenuItem browseFile, save, exit,manual;

    Button button1;




    public static void main ( String[] args){

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Data Visualizer");

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
        button1 = new Button("test");

        //opens the file browser
        button1.setOnAction(e -> fileBrowser(primaryStage));

        //assigning items to file tab
        file.getItems().addAll(browseFile,save);
        //assigning items to help tab
        help.getItems().addAll(exit,manual);

        //assigning menu tabs to menu bar
        menuBar.getMenus().addAll(file,help);

        //setting nodes to border pane
        borderPane.setTop(menuBar);
        borderPane.setLeft(button1);

        //creating new scene
        Scene scene = new Scene(borderPane, 1000, 600);

        primaryStage.setScene(scene);
        //window cannot be resized
        primaryStage.setResizable(false);
        primaryStage.show();


    }

    /**
     * Function to open file browser
     * @param stage
     */
    public void fileBrowser(Stage stage){

        DataFile dataFile = new DataFile();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse a file");
        File selectedFile = fileChooser.showOpenDialog(stage);

        dataFile.setName(selectedFile.toString());
        dataFile.setPath(selectedFile.getPath());

        System.out.println(selectedFile.getName());
        System.out.println(selectedFile.getPath());

    }


}
