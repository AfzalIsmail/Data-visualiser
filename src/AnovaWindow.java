import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.math3.stat.inference.OneWayAnova;

import java.util.ArrayList;

public class AnovaWindow {

    public static void anovaWindow(ArrayList<ColumnData> c){

        Stage window = new Stage();

        window.setTitle("ANOVA");
        window.setMinWidth(300);
        window.setMinHeight(250);

        ArrayList<double[]> classes = new ArrayList<>();

        for(ColumnData cd : c){

            ArrayList<Double> temp = new ArrayList<>();

            for(Object o: cd.getData()){

                Double d = Statistics.pDouble(o);

                temp.add(d);

            }

            double[] a = new double[cd.getData().size()];

            for(int i = 0; i < cd.getData().size(); i++){

                a[i] = temp.get(i);

            }

            classes.add(a);
        }

        String warning = "";

        for(int i = 0; i < c.get(0).getData().size(); i++){

            if(i == c.get(0).getData().size()) {

                if (c.get(i).getData().size() != c.get(i + 1).getData().size()) {

                    warning = "The size of the data samples are not " + "\n" +
                            "the same for all the columns selected." + "\n" +
                            "The results may not be accurate.";

                }
            }
        }

        OneWayAnova anova = new OneWayAnova();

        double resultF = anova.anovaFValue(classes);

        Text anov = new Text("F value = " + resultF + "\n" +
                            warning);

        VBox vBox = new VBox();

        vBox.getChildren().add(anov);

        Scene scene = new Scene(vBox);

        window.setScene(scene);

        window.show();


        System.out.println(resultF);

    }
}
