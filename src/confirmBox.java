import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class confirmBox {

    static boolean answer;

    public static boolean display(String title, String message){

        Stage window = new Stage();

        //Block other user input events when the alert box is displayed untilalert box is taken care of
        window.initModality(Modality.APPLICATION_MODAL);

        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(100);

        Label label = new Label();

        label.setText(message);


        //create 2 buttons
        Button yesButton = new Button("Yes");
        yesButton.setStyle("-fx-background-color: rgb( 51, 170, 168)");
        Button noButton = new Button("Cancel");
        noButton.setStyle("-fx-background-color: rgb(224, 224, 224)");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });


        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(yesButton,noButton);
        buttons.setPadding(new Insets(50,10,10,10));
        //set alignment
        buttons.setAlignment(Pos.BOTTOM_RIGHT);

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(10,10,10,10));
        layout.getChildren().addAll(label,buttons);


        Scene scene = new Scene(layout);
        window.setScene(scene);
        //display this window and needs to be closed before going to previous window
        window.showAndWait();

        return answer;

    }
}
