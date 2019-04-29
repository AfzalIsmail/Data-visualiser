import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class manualWindow {

    public static void manWindow(){

        Stage window = new Stage();
        window.setMinWidth(450);
        window.setMinHeight(600);

        window.setTitle("Manual");

        Text h1 = new Text("Getting started" + "\n");
        h1.setFont(Font.font("Source sans pro", 18));
        h1.setFill(Color.rgb(51, 170, 168));

        Text p1 = new Text("This program is aimed to be simple and intuitive to use." + "\n" +
                            "To get started, please follow these simple steps below: " + "\n" + "\n" +

                            "1. Import a file by clicking on File -> Browse File " + "\n" +
                            "   or click the new file icon in the top left corner." + "\n" +
                            "   This will cause a new tab to appear in the main window," + "\n" +
                            "   along with the column headers found in that file." + "\n" +
                            "2. Select the column(s) (checkbox(es)) that need(s) to be analysed"+ "\n" +
                            "   and click on the 'Display Table' button. This will display" + "\n" +
                            "   the column(s) selected along with their respective statistical information." + "\n" + "\n");


        Text h2 = new Text("Sorting" + "\n");
        h2.setFont(Font.font("Source sans pro", 18));
        h2.setFill(Color.rgb(51, 170, 168));

        Text p2 = new Text("To use the sorting function:" + "\n" + "\n" +
                "1. Select the required columns." + "\n" +
                "2. Click on the 'Sort asc' to sort in ascending order or " + "\n" +
                "   click on the 'Sort desc' to sort in descending order." + "\n" +
                "   The columns will be displayed accordingly." + "\n" + "\n");


        Text h3 = new Text("Correlation coefficient" + "\n");
        h3.setFont(Font.font("Source sans pro", 18));
        h3.setFill(Color.rgb(51, 170, 168));

        Text p3 = new Text("To get the correlation coefficient: " + "\n" +
                            "1. Select only 2 columns that contain numerical data." + "\n" +
                            "2. Click on the 'Correlation coefficient' button." + "\n" +
                            "   A new window will appear containing the calculated value" + "\n" +
                            "   along with a scatter chart to visually show the relationship" + "\n" +
                            "   between the data selected." + "\n" +
                            "Note: The program will automatically detect if more or less than 2" + "\n" +
                            "      columns are selected or if the data in any of the columns are not numerical." + "\n" + "\n");


        Text h4 = new Text("ANOVA - Analysis of variance" + "\n");
        h4.setFont(Font.font("Source sans pro", 18));
        h4.setFill(Color.rgb(51, 170, 168));

        Text p4 = new Text("To calculate the ANOVA for numerical data: " + "\n" +"\n" +
                "1. Select the at least 2 columns containing the numerical data" + "\n" +
                "   that needs to be analysed." + "\n" +
                "2. Click on the 'ANOVA' button." + "\n" +
                "   A new window will pop up containing the Anova value." + "\n" +
                "Note: The program will automatically detect if one or more of the" +"\n" +
                "      columns selected does not contain numerical data. A error will pop up." + "\n" +
                "      The program will take only the numerical the numerical data selected." + "\n" + "\n");


        Text h5 = new Text("Charts" + "\n");
        h5.setFont(Font.font("Source sans pro", 18));
        h5.setFill(Color.rgb(51, 170, 168));

        Text p5 = new Text("To generate charts from the imported data:" + "\n" + "\n" +
                            "1. Select the required columns." + "\n" +
                            "2. Click on the charts button. " + "\n" +
                            "   This will display a new window with the possible charts that" +"\n" +
                            "   can be created, depending on the type of data selected. The button(s) for" +"\n" +
                            "   the chart(s) that cannot be created are disabled." + "\n" + "\n");

        Text h6 = new Text("Saving processed data" + "\n");
        h6.setFont(Font.font("Source sans pro", 18));
        h6.setFill(Color.rgb(51, 170, 168));

        Text p6 = new Text("To save the statistical information displayed when one or more" + "\n" +
                            "columns is/are selected and the 'Display table' is pressed," + "\n" +
                            "click on File -> Save or click the save icon in the top left corner." + "\n" +
                            "This will prompt a save window to appear, asking the user to name the file" + "\n" +
                            "and select the location where the file needs to be saved." + "\n"+ "\n");

        Text h7 = new Text("Caution"  + "\n" );
        h7.setFont(Font.font("Source sans pro", 18));
        h7.setFill(Color.rgb(51, 170, 168));

        Text p7 = new Text("1. If one or more of the selected columns do not appear," + "\n" +
                            "   please check your data set for any wrong values, values that" + "\n" +
                            "   may not need to be in a particular column."  + "\n" +
                            "2. Check that there are no missing delimiters(',') in your csv file." + "\n"+ "\n");

        Text h8 = new Text("Contact"  + "\n");
        h8.setFont(Font.font("Source sans pro", 18));
        h8.setFill(Color.rgb(51, 170, 168));

        Text p8 = new Text("If there are any issues that you want to clarify or " + "\n" +
                            "if you want any additional information, please send an email to:" + "\n" +
                            "mai15@aber.ac.uk" + "\n"+ "\n");



        VBox content = new VBox();
        content.getChildren().addAll(h1,p1,h2,p2,h3,p3,h4,p4,h5,p5,h6,p6,h7,p7,h8,p8);
        content.setPadding(new Insets(10,10,10,10));

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(content);



        Scene scene = new Scene(scrollPane);

        window.setScene(scene);
        window.show();

    }
}
