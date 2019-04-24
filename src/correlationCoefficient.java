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

    /**
     * window that will give the correlation coefficient between two numerical column data
     * and a scatter chart to show their relationship
     * @param a first column
     * @param b second column
     */
    public static void corrCoefWindow(ColumnData a, ColumnData b) {

        double sumA = 0, sumB = 0;
        double meanA = 0, meanB = 0;

        Stage window = new Stage();

        window.setTitle("Correlation coefficient between " + a.getName() + " and " + b.getName());
        window.setMinHeight(600);
        window.setMinWidth(450);

        ArrayList<Double> aDouble = new ArrayList<>();

        ArrayList<Double> bDouble = new ArrayList<>();

        //converting the objects in both arraylist to double
        for(Object o : a.getData()){

            double d = 0;

            d = Statistics.pDouble(o);

            aDouble.add(d);
        }

        for(Object o : b.getData()){

            double d = 0;

            d = Statistics.pDouble(o);

            bDouble.add(d);
        }

        //getting the sums of both columns
        sumA = Statistics.getSum(a.getData());
        sumB = Statistics.getSum(b.getData());

        //meanA = Statistics.getMean(a.getData(),sumA);
        //meanB = Statistics.getMean(b.getData(),sumB);

        //getting the mean of both columns, including missing values
        meanA = sumA/a.getData().size();
        meanB = sumB/b.getData().size();

        DecimalFormat df = new DecimalFormat("0.#####");

        Text t1 = new Text("Number of records = " + a.getData().size() + "\n" +
                        "∑X = " + df.format(sumA) + "\n" +
                        "∑Y = " + df.format(sumB) + "\n" +
                        "Mean x = " + df.format(meanA) + "\n" +
                        "Mean y = " + df.format(meanB));

        Text t2 = getCoefficient(aDouble,bDouble,meanA,meanB);

        //Text t = new Text(coefficient.toString());

        //------------------------------------------------------------------------code for scatter chart to show the relationship between the two columns
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

        window.setScene(scene);
        window.show();


    }

    /**
     * method to calculate the correlation coefficient between the two columns
     * @param a first arraylist
     * @param b second arraylist
     * @param meanA mean of first arraylist
     * @param meanB mean of second arraylist
     * @return Text that contains the correlation coefficient as well as an explanation of the calculation
     */
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

            //calculating the coefficient
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

        Text t = new Text("∑(X - Mean x)2 = " + df.format(sumAMSq) + "\n" +
                          "∑(Y - Mean y)2 = " + df.format(sumBMSq) + "\n" +
                          "∑(X - Mean x)(Y - Mean y) = " + df.format(sumAB) + "\n" + "\n" +
                            "r = ∑((X - Mean y)(Y - Mean x)) / √((SSx)(SSy))" + "\n" + "\n" +
                          "r = " + df.format(corr));

        return t;

    }

}
