/**
 * @author Afzal Ismail
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.FileReader;

public class ReadCSV {

    /**
     * function to read the body of a csv file and store it in a 2d array
     * without storing the first line, which containd the header
     * @param filePath path of csv file
     * @return data[][] containing the data
     * @throws Exception if file cannot be read
     */
    public static Object[][] readFile (String filePath) throws Exception{

        FileReader fr = new FileReader (filePath);
        BufferedReader br = new BufferedReader (fr);

        Object [][] data = null;

        try{

            int col=0, row=0;

            String line = "";


            //get the number of lines and columns in the csv file
            //to initialise the 2d array
            while ((line = br.readLine()) != null){

                String [] columns = line.split(",");

                col = columns.length;

                row++;

            }

            int i=0, j=0;

            //2d array that will contain the body of the file, excluding the header
            data = new Object [row-1][col];

            br = new BufferedReader (new FileReader (filePath));

            int counterRow = 0;

            while ((line = br.readLine()) != null){

                //to excluding the table header
                if(counterRow > 0){


                    String [] column = line.split(",");

                    /*if(column.length != col){

                        alertBox.display("Error","The number of records is not the same" + "\n" +
                                "as the number of columns." + "\n" +
                                "Please check your csv file and try again.");

                        break;
                    }*/

                    for (j=0; j<column.length; j++){

                        data [i][j] = column [j];
                    }

                    i++;
                }

                counterRow++;
            }

        }
        catch(Exception E){


        }

        return data;
    }

    /**
     * function to read only the column headers in a csv file, by reading only the first line
     * @param filePath path of file
     * @return array containing the header names
     * @throws Exception if file cannot be read
     */
    public static String[] readHeader (String filePath) throws Exception{

        String [] header = null;

        //String filepath = "Data.csv";

        FileReader fr = new FileReader (filePath);
        BufferedReader br = new BufferedReader (fr);


        for (int i=0; i<1; i++){

            String line = br.readLine();

            header = line.split(",");

        }

        return header;
    }


}
