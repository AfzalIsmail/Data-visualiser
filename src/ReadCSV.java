/**
 * @author Afzal Ismail
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.FileReader;

public class ReadCSV {

    /**
     * method to read the body of the csv file
     * does not store the first line of the file
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

            String separator = sep(filePath);


            //get the number of lines and columns in the csv file
            //to initialise the 2d array
            while ((line = br.readLine()) != null){

                String [] columns = line.split(separator);

                col = columns.length;

                row++;

            }

            int i=0, j=0;

            //2d array that will contain the body of the file, excluding the header
            data = new Object [row-1][col];

            br = new BufferedReader (new FileReader (filePath));

            int counterRow = 0;

            while ((line = br.readLine()) != null){

                //to exclude the table header
                if(counterRow > 0){


                    String [] column = line.split(separator);

                    //System.out.println(column.length);
                    //System.out.println(col);

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
     * method to read only the first line of a csv file
     * @param filePath path of file
     * @return string[] array containing the header names
     * @throws Exception if file cannot be read
     */
    public static String[] readHeader (String filePath) throws Exception{

        String [] header = null;

        FileReader fr = new FileReader (filePath);
        BufferedReader br = new BufferedReader (fr);

        String separator = sep(filePath);

        for (int i=0; i<1; i++){

            String line = br.readLine();

            header = line.split(separator);

        }

        return header;
    }

    public static String sep (String fn) throws Exception
    {
        FileReader fr = new FileReader (fn);
        BufferedReader br = new BufferedReader (fr);
        String sep = null;

        try
        {
            String line = null;
            String [] row = null;

            for (int i=0; i<1; i++)
            {
                line = br.readLine();
                row = line.split(",");
            }

            for(int j=0; j<row.length; j++)
            {
                System.out.println(row[j]);
            }

            System.out.println("Length : "+row.length);

            if (row.length>1)
            {
                sep = ",";
            }
            else
            {
                sep = " ";
            }

        }
        catch (Exception E)
        {
            System.out.println("+++ReadArrayFile: "+E.getMessage());

        }

        System.out.println("Sep is : "+sep);
        return sep;
    }


}
