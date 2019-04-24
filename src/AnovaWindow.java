/**
 * @author Afzal Ismail
 * @version 1.1
 */

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.apache.commons.math3.stat.inference.OneWayAnova;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class AnovaWindow {

    /**
     * Display a new window containing the means of the selected numerical columns and the Anova value
     * @param c arraylist of columnData parsed from Main class
     */
    public static void anovaWindow(ArrayList<ColumnData> c){

        Stage window = new Stage();

        window.setTitle("ANOVA");
        window.setMinWidth(300);
        window.setMinHeight(250);

        //Creating an ArrayList for double[]
        //The Anova library will accept only this type of input
        ArrayList<double[]> classes = new ArrayList<>();

        for(ColumnData cd : c){

            ArrayList<Double> temp = new ArrayList<>();

            for(Object o: cd.getData()){

                double d = 0.0;

                //Does not take missing values in the array
                if(o == null || o.toString().length() == 0 || o.toString().equals(" ")){


                }else{

                  //converts the objects into double
                  d = Statistics.pDouble(o);

                }

                temp.add(d);

            }

            double[] a = new double[cd.getData().size()];

            //converting arraylist into double array
            for(int i = 0; i < cd.getData().size(); i++){

                a[i] = temp.get(i);
            }

            classes.add(a);
        }

        String warning = "";

        VBox means = new VBox();

        for(ColumnData cd: c){


            double sum = Statistics.getSum(cd.getData());
            double mean = Statistics.getMean(cd.getData(),sum);

            DecimalFormat df = new DecimalFormat(".####");

            Text m = new Text("Mean for column " + cd.getName() + ": "+ df.format(mean));

            means.getChildren().add(m);

        }

        //Creating a One way anova object
        //OneWayAnova library imported from external library: org.apache.commons.math3.stat.inference.OneWayAnova
        OneWayAnova anova = new OneWayAnova();

        double resultF = anova.anovaFValue(classes);

        Text anov = new Text("F value = " + resultF + "\n" +
                            warning);

        VBox vBox = new VBox();

        vBox.getChildren().addAll(means,anov);
        vBox.setPadding(new Insets(5,5,5,5));
        vBox.setSpacing(10);

        Scene scene = new Scene(vBox);

        window.setScene(scene);

        window.show();

        //System.out.println(resultF);

    }
}
