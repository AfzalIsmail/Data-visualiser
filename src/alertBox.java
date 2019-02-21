import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class alertBox {

    public static void display(String title, String message){

        Stage window = new Stage();

        //Block other user input events when the alert box is displayed untilalert box is taken care of
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(300);
        window.setMinHeight(200);

        Label label = new Label();

        label.setText(message);
        
        Button closeButton = new Button("Ok");

        closeButton.setOnAction(e -> window.close());


        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        //set alignment to center of window
        layout.setAlignment(Pos.CENTER);

       // closeButton.setBorder(new Insets(30,10,10,10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        //display this window and needs to be closed before going to previous window
        window.showAndWait();

    }

}
