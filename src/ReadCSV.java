import java.io.BufferedReader;
import java.io.FileReader;

public class ReadCSV {


    public static Object[][] readFile (String filepath) throws Exception
    {

        FileReader fr = new FileReader (filepath);
        BufferedReader br = new BufferedReader (fr);

        Object [][] data = null;

        try
        {
            int col=0, row=0;
            String line = null;

            while ((line = br.readLine()) != null)
            {
                row++;
                String [] column = line.split(",");
                col = column.length;
            }

            int i=0, j=0;
            data = new Object [row-1][col]; //reason row minus 1 is to exclude the table header
            br = new BufferedReader (new FileReader (filepath));

            int counterRow = 0;
            while ((line = br.readLine()) != null)
            {
                if(counterRow >0) //consider excluding the table header
                {
                    String [] column = line.split(",");

                    for (j=0; j<column.length; j++)
                    {
                        data [i][j] = column [j];
                    }
                    i++;
                }
                counterRow++;
                //i++;

            }


        }
        catch(Exception E)
        {
            System.out.println("+++ReadArrayFile: "+E.getMessage());
        }

        return data;
    }

    public static String[] readHeader (String filepath) throws Exception
    {
        String [] header = null;

        //String filepath = "Data.csv";

        FileReader fr = new FileReader (filepath);
        BufferedReader br = new BufferedReader (fr);


        for (int i=0; i<1; i++)
        {
            String line = br.readLine();
            header = line.split(",");
        }
        return header;
    }

}
