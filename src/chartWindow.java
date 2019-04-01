import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class chartWindow {

    public static void display(){

        Stage chartWindow = new Stage();

        chartWindow.setTitle("Charts");
        chartWindow.setMinHeight(200);
        chartWindow.setMinWidth(350);

        Button lineChart = new Button();
        Image lChart = new Image("picture/lineChart.png");
        ImageView lChartView = new ImageView(lChart);
        lChartView.setFitWidth(40);
        lChartView.setFitHeight(40);
        lineChart.setGraphic(lChartView);

        Button pieChart = new Button();
        Image pChart = new Image("picture/pieChart.png");
        ImageView pChartView = new ImageView(pChart);
        pChartView.setFitWidth(40);
        pChartView.setFitHeight(40);
        pieChart.setGraphic(pChartView);

        Button barChart = new Button();
        Image bChart = new Image("picture/barChart.png");
        ImageView bChartView = new ImageView(bChart);
        bChartView.setFitWidth(40);
        bChartView.setFitHeight(40);
        barChart.setGraphic(bChartView);

        HBox buttonsArea = new HBox();
        buttonsArea.setAlignment(Pos.CENTER);
        buttonsArea.setSpacing(10);
        buttonsArea.setStyle("-fx-background-color: rgb(68, 69, 71)");

        buttonsArea.getChildren().addAll(lineChart,pieChart,barChart);

        Scene primaryScene = new Scene(buttonsArea);

        chartWindow.setScene(primaryScene);

        chartWindow.show();

    }

}
