/**
 * @author Afzal Ismail
 * @version 2.1
 */
import java.util.ArrayList;

public class checkVariable {

    /**
     * method to check the type of data in a particular array list of objects
     * @param dataObject arraylist of object parsed
     * @return a string with the type of data present
     */
    public static String checkVar (ArrayList<Object> dataObject) {

        String varType = "";

        int i = 0;

        //counter for each type of data that can be present
        int cInt = 0, cDouble = 0, cChar = 0, cString = 0, cBoolean = 0;

    //checking every record in the arraylist
    for(i = 0; i <dataObject.size(); i++) {

        if (canConvertInt(dataObject.get(i)) && !dataObject.get(i).toString().equals(" ") ) {

            cInt++;

        }else if (canConvertDouble(dataObject.get(i)) && !dataObject.get(i).toString().equals(" ")) {

            cDouble++;

        } else if (canConvertString(dataObject.get(i)) && !dataObject.get(i).toString().equals(" ") && dataObject.get(i).toString().length() == 1) {

            cChar++;

        } else if (canConvertBoolean(dataObject.get(i)) && !dataObject.get(i).toString().equals(" ")) {

            cBoolean++;

        }else if (canConvertString(dataObject.get(i)) && !dataObject.get(i).toString().equals(" ")) {

            cString++;

        }  else {

        }
    }

        //determining the data type by comparing the counters
        if(cBoolean >= 1) {
            varType = "Boolean";

        }else if(cChar >=1 && cString == 0 && cInt == 0 && cDouble == 0) {
            varType = "Char";

        }else if(cString >= 1) {
            varType = "String";
            //System.out.println(cString);

        }else if(cChar >= 1 && (cDouble >= 1 || cInt >= 1)){

            varType = "String";

        } else if(cInt >= 1 && cDouble == 0) {
            varType = "Integer";

        }else if(cDouble >= 1) {
            varType = "Double";

        }else {
            varType = "String";
        }

    //System.out.println(cInt);
        return varType;
    }


    /**
     * boolean method to check if an object can be converted to integer
     * @param data object parsed
     * @return true if object can be converted to integer or false if it cannot
     */
    public static boolean canConvertInt (Object data)
    {
        boolean res = true;

        try
        {
            String test = data.toString();
            int test1 =  Integer.parseInt(test);
        }
        catch (Exception E)
        {
            res = false;
        }

        return res;
    }

    /*public static boolean canConvertChar (Object data)
    {
        boolean res = true;

        try
        {
            char test1 = (char) data;
            //String test = data.toString();
            //char test1 =


        }
        catch (Exception E)
        {
            res = false;
        }


        return res;
    }*/

    /**
     *boolean method to check if an object can be converted to double
     *@param data object parsed
     *@return true if object can be converted to double or false if it cannot
     */
    public static boolean canConvertDouble (Object data)
    {
        boolean res = true;

        try
        {
            String test = data.toString();
            double test1 =  Double.parseDouble(test);
        }
        catch (Exception E)
        {
            res = false;
        }

        return res;
    }

    /**
     *boolean method to check if an object equals to "true" or "false" when converted to a string
     *@param data object parsed
     *@return true if object can be converted to integer or false if it cannot
     */
    public static boolean canConvertBoolean (Object data)
    {
        boolean res = false;

        String test = data.toString().toLowerCase();

        if(test.equals("true") || test.equals("false")){

            res = true;

        }else{
            res = false;
        }

        /*try
        {
            String test = data.toString();
            boolean test1 = Boolean.parseBoolean(test);
        }
        catch (Exception E)
        {
            res = false;
        }*/

        return res;
    }

    /**
     * boolean method to check if an object can be converted to string
     * @param data object parsed
     * @return true if it can be converted or false if not
     */
    public static boolean canConvertString (Object data)
    {
        boolean res = true;

        try
        {
            String test = data.toString();
            res = true;

        }
        catch (Exception E)
        {
            res = false;
        }

        return res;
    }
}
