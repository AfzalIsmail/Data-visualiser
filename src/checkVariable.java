import java.util.ArrayList;

public class checkVariable {

    public static String checkVar (ArrayList<Object> dataObject) {

        String varType = "";

        int i = 0;

        int cInt = 0, cDouble = 0, cChar = 0, cString = 0, cBoolean = 0;

    for(i = 0; i <5; i++) {

        if (canConvertInt(dataObject.get(i)) && !dataObject.get(i).toString().equals(" ") ) {

            cInt++;


        } else if (canConvertDouble(dataObject.get(i)) && !dataObject.get(i).toString().equals(" ")) {

            cDouble++;

        } else if (canConvertChar(dataObject.get(i)) && !dataObject.get(i).toString().equals(" ")) {

            cChar++;

        } else if (canConvertBoolean(dataObject.get(i)) && !dataObject.get(i).toString().equals(" ")) {

            cBoolean++;

        }else if (canConvertString(dataObject.get(i)) && !dataObject.get(i).toString().equals(" ")) {

            cString++;

        }  else {

        }
    }

        if(cInt >= 1) {
            varType = "Integer";

        }else if(cDouble >= 1) {
            varType = "Double";

        }else if(cChar >=1) {
            varType = "Char";

        }else if(cBoolean >= 1) {
            varType = "Boolean";

        }
        else if(cString >= 1) {
            varType = "String";
            //System.out.println(cString);

        }else {
            varType = "Not recognised";
        }

    //System.out.println(cInt);
        return varType;
    }


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

    public static boolean canConvertChar (Object data)
    {
        boolean res = true;

        try
        {
            char test1 = (char) data;

        }
        catch (Exception E)
        {
            res = false;
        }

        return res;
    }


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

    public static boolean canConvertBoolean (Object data)
    {
        boolean res = false;

        String test = data.toString();

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
