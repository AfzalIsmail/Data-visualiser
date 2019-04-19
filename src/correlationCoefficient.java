import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class correlationCoefficient {

    public static void corrCoefWindow(ColumnData a, ColumnData b) {

        double sumA = 0, sumB = 0;
        double meanA = 0, meanB = 0;

        Stage window = new Stage();

        window.setTitle("Correlation coefficient between " + a.getName() + " and " + b.getName());
        window.setMinHeight(600);
        window.setMinWidth(450);

        ArrayList<Double> aDouble = new ArrayList<>();

        ArrayList<Double> bDouble = new ArrayList<>();

        for(Object o : a.getData()){
            double d = 0;

            /*if(o == null || o.toString().length() == 0 || o.toString().equals(" ")){

                d = 0;

            }else {

                d = Double.parseDouble(o.toString());
            }*/

            d = Statistics.pDouble(o);

            aDouble.add(d);
        }

        for(Object o : b.getData()){
            double d = 0;

            /*if(o == null || o.toString().length() == 0 || o.toString().equals(" ")){

                d = 0;

            }else {

                d = Double.parseDouble(o.toString());
            }*/

            d = Statistics.pDouble(o);

            bDouble.add(d);
        }

        sumA = Statistics.getSum(a.getData());
        sumB = Statistics.getSum(b.getData());

        //meanA = Statistics.getMean(a.getData(),sumA);
        //meanB = Statistics.getMean(b.getData(),sumB);

        meanA = sumA/a.getData().size();
        meanB = sumB/b.getData().size();


        DecimalFormat df = new DecimalFormat(".#####");

        Text t1 = new Text("Number of records = " + a.getData().size() + "\n" +
                        "∑X = " + df.format(sumA) + "\n" +
                        "∑Y = " + df.format(sumB) + "\n" +
                        "Mx = " + df.format(meanA) + "\n" +
                        "My = " + df.format(meanB));

        Text t2 = getCoefficient(aDouble,bDouble,meanA,meanB);

        //Text t = new Text(coefficient.toString());

        //------------------------------------------------------------------------code for graph
        NumberAxis xAxis = new NumberAxis();
        NumberAxis yAxis = new NumberAxis();
        ScatterChart<Number,Number> sc = new ScatterChart<>(xAxis,yAxis);

        xAxis.setLabel(a.getName());
        yAxis.setLabel(b.getName());
        sc.setTitle("Correlation coefficient");
        XYChart.Series series1 = new XYChart.Series();

        for(int i = 0;i<aDouble.size();i++){
            series1.getData().add(new XYChart.Data(aDouble.get(i),bDouble.get(i)));

        }
        sc.getData().add(series1);
        //sc.setStyle(.symbol{-fx-background-color: rgb( 51, 170, 168)});

        VBox vbox = new VBox();
        vbox.getChildren().addAll(t1,t2,sc);
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10,10,10,20));

        Scene scene = new Scene(vbox);

        scene.getStylesheets().add("scatterChart.css");

        window.setScene(scene);
        window.show();


    }

    public static Text getCoefficient(ArrayList<Double> a, ArrayList<Double> b, double meanA, double meanB){

        int sizeA = 0, sizeB = 0;
        double aM = 0, bM = 0;
        double aMSq = 0, bMSq = 0;
        double sumAMSq = 0, sumBMSq = 0;
        double ab = 0,sumAB = 0;
        double sqrAB = 0;
        double corr = 0;

        sizeA = a.size();
        sizeB = b.size();

        if(sizeA != sizeB){

            alertBox.display("Error", "Size of both columns not the same.");
        }else{

            for(int i = 0; i < sizeA ; i++){

                aM = a.get(i) - meanA;

                bM = b.get(i) - meanB;

                aMSq = aM * aM;

                bMSq = bM * bM;

                sumAMSq = sumAMSq + aMSq;

                sumBMSq = sumBMSq + bMSq;

                ab = aM * bM;

                sumAB = sumAB + ab;

            }

            //System.out.println(sumAMSq);
            //System.out.println(sumBMSq);
            //System.out.println(sumAB);

            sqrAB = Math.sqrt(sumAMSq * sumBMSq);

            corr = sumAB / sqrAB;

        }

        DecimalFormat df = new DecimalFormat("0.#####");

        Text t = new Text("∑(X - Mx)2 = " + df.format(sumAMSq) + "\n" +
                          "∑(Y - My)2 = " + df.format(sumBMSq) + "\n" +
                          "∑(X - Mx)(Y - My) = " + df.format(sumAB) + "\n" + "\n" +
                            "r = ∑((X - My)(Y - Mx)) / √((SSx)(SSy))" + "\n" + "\n" +
                          "r = " + df.format(corr));

        return t;

    }

    
}
