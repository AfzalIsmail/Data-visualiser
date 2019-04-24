/**
 * @author Afzal Ismail
 * @version 1.0
 */

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class alertBox {

    /**
     * Function to display a new window that will contain the error message
     * @param title - error title
     * @param message - explanation of the error
     */
    public static void display(String title, String message){

        Stage window = new Stage();

        //Block other user input events when the alert box is displayed untilalert box is taken care of
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(200);

        Label label = new Label();

        label.setText(message);

        Image warning = new Image("picture/warning.png");
        ImageView warningView = new ImageView(warning);

        warningView.setX(125);
        warningView.setY(80);
        warningView.setFitHeight(50);
        warningView.setFitWidth(50);

        Button closeButton = new Button("Ok");
        closeButton.setStyle("-fx-background-color: rgb( 51, 170, 168)");

        closeButton.setOnAction(e -> window.close());


        VBox layout = new VBox(10);
        layout.getChildren().addAll(warningView,label, closeButton);
        //set alignment to center of window
        layout.setAlignment(Pos.CENTER);

        // closeButton.setBorder(new Insets(30,10,10,10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        //display this window and needs to be closed before going to previous window
        window.showAndWait();

    }

}
