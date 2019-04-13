import com.sun.xml.internal.xsom.impl.scd.Iterators;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;

public class chartWindow {

    public static void display(ArrayList<String> cols, ArrayList<DataFile> dFiles, TabPane t){

        String tabID = "";

        try {
            tabID = t.getSelectionModel().getSelectedItem().getId();
            System.out.println("Tab " + tabID + "is being manipulated by user.");
        }catch (Exception e){

        }


        int counterInt = 0, counterString = 0, counterBool = 0;

        ArrayList<ColumnData> catData = new ArrayList<>();

        ArrayList<ColumnData> numData = new ArrayList<>();

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
        lineChart.setDisable(true);

        Button pieChart = new Button();
        Image pChart = new Image("picture/pieChart.png");
        ImageView pChartView = new ImageView(pChart);
        pChartView.setFitWidth(40);
        pChartView.setFitHeight(40);
        pieChart.setGraphic(pChartView);
        pieChart.setDisable(true);

        Button barChart = new Button();
        Image bChart = new Image("picture/barChart.png");
        ImageView bChartView = new ImageView(bChart);
        bChartView.setFitWidth(40);
        bChartView.setFitHeight(40);
        barChart.setGraphic(bChartView);
        barChart.setDisable(true);

        //-----------------------------------------------------------------checking the data tpe for the selected columns

            for (DataFile d : dFiles) {

                if (d.getName().equals(tabID)) {

                    for (String s : cols) {

                        for (ColumnData c : d.getColData()) {

                            if (c.getName().equals(s)) {

                                String checkVar = checkVariable.checkVar(c.getData());

                                if (checkVar.equals("String") || checkVar.equals("Char") || checkVar.equals("Boolean")) {

                                    counterString = counterString + 1;

                                    catData.add(c);

                                } else if (checkVar.equals("Double") || checkVar.equals("Integer")) {

                                    counterInt = counterInt + 1;

                                    numData.add(c);
                                }
                            }
                        }
                    }
                }
            }

        if(counterString >= 1){

            pieChart.setDisable(false);
            barChart.setDisable(false);

        }if(counterInt >= 2){

            lineChart.setDisable(false);

        }else{

        }

        pieChart.setOnAction(e -> {

            VBox v = pieChartDisp(catData);

            ScrollPane scrollV = new ScrollPane();

            scrollV.setContent(v);

            Scene secondaryScene = new Scene(scrollV);

            chartWindow.setScene(secondaryScene);
        });


        barChart.setOnAction(e -> {

            VBox v = barChartDisp(catData);

            ScrollPane scrollB = new ScrollPane();

            scrollB.setContent(v);

            Scene secondaryScene = new Scene(scrollB);

            chartWindow.setScene(secondaryScene);
        });

        lineChart.setOnAction(e -> {

            VBox v = lineChartDisp(numData,numData);

            ScrollPane scrollL = new ScrollPane();

            scrollL.setContent(v);

            Scene secondaryScene = new Scene(scrollL);

            chartWindow.setScene(secondaryScene);

        });



        System.out.println(counterString);
        System.out.println(counterInt);

        HBox buttonsArea = new HBox();
        buttonsArea.setAlignment(Pos.CENTER);
        buttonsArea.setSpacing(10);
        buttonsArea.setStyle("-fx-background-color: rgb(68, 69, 71)");

        buttonsArea.getChildren().addAll(lineChart,pieChart,barChart);

        Scene primaryScene = new Scene(buttonsArea);

        chartWindow.setScene(primaryScene);

        chartWindow.show();

    }

    public static VBox pieChartDisp(ArrayList<ColumnData> columnData){

        VBox charts = new VBox();

        for(ColumnData c: columnData){

            Map distinct = Distinct.getDistinct(c.getData());

            //ArrayList<categoricalChart> catCharts = new ArrayList<>();

            /*distinct.forEach((k,v) -> {

                String a = k.toString();
                //ks.add(a);

                double b = Double.parseDouble(v.toString());
                //vs.add(b);

                categoricalChart catChart = new categoricalChart();
                catChart.setName(a);
                catChart.setValue(b);

                catCharts.add(catChart);

            });*/

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new ArrayList<>());


            //for(categoricalChart cChart: catCharts){

                    //pieChartData.add(new PieChart.Data(cChart.getName(),cChart.getValue()));
            //}

            distinct.forEach((k,v) -> {

                String a = k.toString();

                double b = Double.parseDouble(v.toString());

                pieChartData.add(new PieChart.Data(a,b));

            });


            PieChart chart = new PieChart(pieChartData);
            chart.setTitle(c.getName());

            charts.getChildren().add(chart);

        }

        return charts;

    }

    public static VBox barChartDisp(ArrayList<ColumnData> columnData){

        VBox charts = new VBox();

        for(ColumnData c: columnData){

            Map distinct = Distinct.getDistinct(c.getData());

             CategoryAxis xAxis = new CategoryAxis();
             NumberAxis yAxis = new NumberAxis();

            BarChart<String, Number> bc = new BarChart<String, Number>(xAxis,yAxis);

            xAxis.setLabel(c.getName());
            yAxis.setLabel("Frequency");

            XYChart.Series series1 = new XYChart.Series();

            distinct.forEach((k,v) ->{

                String a = k.toString();

                double b = Double.parseDouble(v.toString());

                series1.getData().add(new XYChart.Data(a,b));

            });

            bc.getData().add(series1);

            charts.getChildren().add(bc);
        }

        return charts;

    }

    public static VBox lineChartDisp (ArrayList<ColumnData> numData, ArrayList<ColumnData> catData){

        VBox vBox = new VBox();

        for(ColumnData xA: numData){

            for(ColumnData yA: numData){

                 NumberAxis xAxis = new NumberAxis();
                 NumberAxis yAxis = new NumberAxis();

                 xAxis.setLabel(xA.getName());
                 yAxis.setLabel(yA.getName());

                LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);

                XYChart.Series series = new XYChart.Series();



                for(int i = 0; i<xA.getData().size();i++){

                    //for(int j = 0; j<numData.size(); j++){

                        double x = Statistics.pDouble(xA.getData().get(i));
                        double y = Statistics.pDouble(yA.getData().get(i));

                        series.getData().add(new XYChart.Data(x,y));
                    //}
                }

                lineChart.getData().add(series);

                vBox.getChildren().add(lineChart);



            }


        }

        return vBox;

    }

}
