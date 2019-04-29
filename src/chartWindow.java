/**
 * @author Afzal Ismail
 * @version 2.0
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Map;

public class chartWindow {

    /**
     * Function that will display a new window containing the buttons for every charts
     * @param cols selected columns from main window
     * @param dFiles arraylist of all dataFiles that are currently open
     * @param t all the tabpanes present in the main window
     */
    public static void display(ArrayList<String> cols, ArrayList<DataFile> dFiles, TabPane t){

        String tabID = "";

        //determine which tab is currently displayed
        try {
            tabID = t.getSelectionModel().getSelectedItem().getId();
            System.out.println("Tab " + tabID + "is being manipulated by user.");
            logFile.addToLog("Chart options for file " + tabID + " is being displayed");
        }catch (Exception e){

        }

        //counter for each data type present in the selected columns
        int counterInt = 0, counterString = 0;

        //will contain all columns that have categorical data types
        ArrayList<ColumnData> catData = new ArrayList<>();

        //will contain all columns that have numerical data types
        ArrayList<ColumnData> numData = new ArrayList<>();

        Stage chartWindow = new Stage();

        chartWindow.setTitle("Charts");
        chartWindow.setMinHeight(200);
        chartWindow.setMinWidth(350);

        //----------------------------------------------------------------button for line chart
        Button lineChart = new Button();
        Tooltip tLine = new Tooltip("Line chart");
        lineChart.setTooltip(tLine);
        Image lChart = new Image("picture/lineChart.png");
        ImageView lChartView = new ImageView(lChart);
        lChartView.setFitWidth(40);
        lChartView.setFitHeight(40);
        lineChart.setGraphic(lChartView);
        lineChart.setDisable(true);

        //---------------------------------------------------------------- button for pie chart
        Button pieChart = new Button();
        Tooltip tPie = new Tooltip("Pie chart");
        pieChart.setTooltip(tPie);
        Image pChart = new Image("picture/pieChart.png");
        ImageView pChartView = new ImageView(pChart);
        pChartView.setFitWidth(40);
        pChartView.setFitHeight(40);
        pieChart.setGraphic(pChartView);
        pieChart.setDisable(true);

        //----------------------------------------------------------------- button for barchart
        Button barChart = new Button();
        Tooltip tBar = new Tooltip("Bar chart");
        barChart.setTooltip(tBar);
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

                                //increse counterString by 1 each time checkVar detects a categorical data type
                                if (checkVar.equals("String") || checkVar.equals("Char") || checkVar.equals("Boolean")) {

                                    counterString = counterString + 1;

                                    catData.add(c);


                                }//increse counterInt by 1 each time checkVar detects a numerical data type
                                else if (checkVar.equals("Double") || checkVar.equals("Integer")) {

                                    counterInt = counterInt + 1;

                                    numData.add(c);
                                }
                            }
                        }
                    }
                }
            }

         //sets the button(s) clickable depending on the type of data present
        if(counterString >= 1){

            pieChart.setDisable(false);
            barChart.setDisable(false);

            logFile.addToLog("Pie chart and bar chart option available");

        }if(counterString >= 1 && counterInt >= 1){

            pieChart.setDisable(false);
            barChart.setDisable(false);
            lineChart.setDisable(false);
            logFile.addToLog("All chart options available");

        }if(counterInt >= 2){

            lineChart.setDisable(false);
            logFile.addToLog("Line chart option available");

        }else{

        }

        Button back = new Button("Back");

        HBox backArea = new HBox();

        backArea.getChildren().add(back);


        //pieChart button action
        pieChart.setOnAction(e -> {

            logFile.addToLog("Pie chart button selected");

            VBox v = pieChartDisp(catData,numData);

            ScrollPane scrollV = new ScrollPane();

            scrollV.setContent(v);

            Scene secondaryScene = new Scene(scrollV);

            chartWindow.setScene(secondaryScene);
        });

        //barChart button action
        barChart.setOnAction(e -> {

            logFile.addToLog("Bar chart button selected");

            VBox v = barChartDisp(catData,numData);

            ScrollPane scrollB = new ScrollPane();

            scrollB.setContent(v);

            Scene secondaryScene = new Scene(scrollB);

            chartWindow.setScene(secondaryScene);
        });

        //lineChart button action
        lineChart.setOnAction(e -> {

            logFile.addToLog("Line chart button selected");

            VBox v = lineChartDisp(numData,catData);

            ScrollPane scrollL = new ScrollPane();

            scrollL.setContent(v);

            Scene secondaryScene = new Scene(scrollL);

            chartWindow.setScene(secondaryScene);

        });



        //System.out.println(counterString);
        //System.out.println(counterInt);

        HBox buttonsArea = new HBox();
        buttonsArea.setAlignment(Pos.CENTER);
        buttonsArea.setSpacing(10);
        buttonsArea.setStyle("-fx-background-color: rgb(68, 69, 71)");

        buttonsArea.getChildren().addAll(lineChart,pieChart,barChart);

        Scene primaryScene = new Scene(buttonsArea);

        chartWindow.setScene(primaryScene);

        chartWindow.show();

    }

    /**
     * method to display all the possible piecharts from the data selected
     * @param catData arraylist of categorical data
     * @param numData arraylist of numerical data
     * @return vbox containing all the possible piecharts
     */
    public static VBox pieChartDisp(ArrayList<ColumnData> catData, ArrayList<ColumnData> numData){

        //vbox that will contain all the charts
        VBox charts = new VBox();

        //create a piechart if the number of categorical data is the same as the number of numerical data
        //which might be concluded that each numerical data has a direct relationship to the corresponding categorical data
        for(ColumnData cat: catData){

            Map distinct = Distinct.getDistinct(cat.getData());

            for(ColumnData num: numData){

                if(distinct.size() == num.getData().size()){

                    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new ArrayList<>());

                    for(int i = 0; i<cat.getData().size();i++){

                        String a = cat.getData().get(i).toString();
                        double b = Statistics.pDouble(num.getData().get(i));

                        pieChartData.add(new PieChart.Data(a,b));

                    }

                    PieChart chart = new PieChart(pieChartData);
                    chart.setTitle(num.getName());

                    chart.getData().forEach(data -> {

                            double total = 0;

                            total = Statistics.getSum(num.getData());

                        //Displaying percentage for each slice when mouse is hovered
                        String s = String.format("%.2f%%", (100*data.getPieValue()/total));
                        Tooltip toolTip = new Tooltip(s);
                        Tooltip.install(data.getNode(), toolTip);

                        //System.out.println(data.getPieValue());

                    });

                    charts.getChildren().add(chart);

                }
            }

        }

        //create a pie chart for distinct frequency of each categorical data present
        for(ColumnData c: catData){

            Map distinct = Distinct.getDistinct(c.getData());


            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(new ArrayList<>());

            //getting the pie chart data from the hashmap
            distinct.forEach((k,v) -> {

                String a = k.toString();

                double b = Double.parseDouble(v.toString());

                pieChartData.add(new PieChart.Data(a,b));

            });


            PieChart chart = new PieChart(pieChartData);
            chart.setTitle("Frequency: "+c.getName());

            //displaying the percentage for each slice when hovering with the cursor
            chart.getData().forEach(data -> {

                double total = c.getData().size();

                //Displaying percentage for each slice when mouse is hovered
                String s = String.format("%.2f%%", (100*data.getPieValue()/total));
                Tooltip toolTip = new Tooltip(s);
                Tooltip.install(data.getNode(), toolTip);

                //System.out.println(data.getPieValue());

            });

            charts.getChildren().add(chart);

        }

        return charts;

    }

    /**
     * method to display all the possible bar charts from selected columns
     * @param columnData arraylist containing all categorical data
     * @param numData arraylist containing all numerical data
     * @return vbox that will contain all bar charts
     */
    public static VBox barChartDisp(ArrayList<ColumnData> columnData, ArrayList<ColumnData> numData){

        //vbox that will contain all charts
        VBox charts = new VBox();

        //bar chart created if distinct frequency is equal to number of numerical data
        for(ColumnData cat: columnData){

            Map distinct = Distinct.getDistinct(cat.getData());

            for(ColumnData num: numData){

                if(distinct.size() == numData.get(0).getData().size()){

                    CategoryAxis xAxis = new CategoryAxis();
                    NumberAxis yAxis = new NumberAxis();

                    BarChart<String,Number> bc = new BarChart<>(xAxis,yAxis);

                    xAxis.setLabel(cat.getName());
                    yAxis.setLabel(num.getName());

                    XYChart.Series series = new XYChart.Series();

                    for(int i = 0; i< num.getData().size();i++){

                        String x = cat.getData().get(i).toString();
                        double y = Statistics.pDouble(num.getData().get(i));

                        series.getData().add(new XYChart.Data(x,y));

                    }

                    bc.getData().add(series);

                    charts.getChildren().add(bc);
                }
            }
        }

        //bar chart created to show the frequency of categorical data
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

            series1.setName("Frequency: " + c.getName());

            bc.getData().add(series1);

            charts.getChildren().add(bc);
        }

        return charts;

    }

    /**
     * method to display line charts possible
     * @param numData arraylist containing all numerical data
     * @param catData arraylist containing all categorical data
     * @return vbox with all the line charts
     */
    public static VBox lineChartDisp (ArrayList<ColumnData> numData, ArrayList<ColumnData> catData){

        VBox vBox = new VBox();

        //line chart created if number of distinct values is equal to the number of numerical data
        for(ColumnData xA: catData){

            Map distinct = Distinct.getDistinct(xA.getData());

            //System.out.println("Distinct: "+distinct.size());

            for(ColumnData yA: numData){

                if(distinct.size() == numData.get(0).getData().size()){

                    //System.out.println("Num: "+yA.getData().size());

                    CategoryAxis xAxis = new CategoryAxis();
                    NumberAxis yAxis = new NumberAxis();

                    xAxis.setLabel(xA.getName());
                    yAxis.setLabel(yA.getName());

                    LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);


                    XYChart.Series series = new XYChart.Series();

                    series.setName(xA.getName());

                    for(int i = 0; i<xA.getData().size();i++){

                        String x = xA.getData().get(i).toString();
                        double y = Statistics.pDouble(yA.getData().get(i));

                        series.getData().add(new XYChart.Data(x,y));

                    }

                    lineChart.getData().add(series);

                    vBox.getChildren().add(lineChart);

                }

            }

        }

        //create a line chart to compare all the line charts with same set of categorical data but different numerical data
        for(ColumnData xA: catData){

            Map distinct = Distinct.getDistinct(xA.getData());

            if (distinct.size() == numData.get(0).getData().size()) {

                //System.out.println("Distinct: "+distinct.size());

                CategoryAxis xAxis = new CategoryAxis();
                NumberAxis yAxis = new NumberAxis();

                xAxis.setLabel(xA.getName());

                LineChart<String,Number> lineChart = new LineChart<>(xAxis,yAxis);

                for(ColumnData yA: numData) {

                    //System.out.println("Num: " + yA.getData().size());

                    XYChart.Series series = new XYChart.Series();

                    series.setName(yA.getName());

                    for (int i = 0; i < xA.getData().size(); i++) {

                        String x = xA.getData().get(i).toString();
                        double y = Statistics.pDouble(yA.getData().get(i));

                        series.getData().add(new XYChart.Data(x, y));

                    }

                    lineChart.getData().add(series);


                }

                vBox.getChildren().add(lineChart);

            }

        }

        //create line chart for each pair of numerical data present
        for(ColumnData xA: numData){

            for(ColumnData yA: numData){

                 NumberAxis xAxis = new NumberAxis();
                 NumberAxis yAxis = new NumberAxis();

                 xAxis.setLabel(xA.getName());
                 yAxis.setLabel(yA.getName());

                LineChart<Number,Number> lineChart = new LineChart<>(xAxis,yAxis);

                XYChart.Series series = new XYChart.Series();

                series.setName(xA.getName());


                for(int i = 0; i<xA.getData().size();i++){

                        double x = Statistics.pDouble(xA.getData().get(i));
                        double y = Statistics.pDouble(yA.getData().get(i));

                        series.getData().add(new XYChart.Data(x,y));

                }

                lineChart.getData().add(series);

                vBox.getChildren().add(lineChart);

            }

        }

        return vBox;

    }

}
