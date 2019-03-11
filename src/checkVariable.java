import java.util.ArrayList;

public class checkVariable {

    public static String checkVar (ArrayList<Object> dataObject) {

        String varType = "";

        int i = 0;


        if (canConvertInt(dataObject.get(0))) {

            varType = "Integer";

        } else if (canConvertDouble(dataObject.get(0))) {

            varType = "Double";

        } else if (canConvertChar(dataObject.get(0))) {

            varType = "Char";

        } else if (canConvertString(dataObject.get(0)) && dataObject.get(0).equals(null)) {

            varType = "String";

        } else if (canConvertBoolean(dataObject.get(0))) {

            varType = "Boolean";

        } else {

            varType = "Not recognised";

        }

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
        boolean res = true;

        try
        {
            String test = data.toString();
            boolean test1 = Boolean.parseBoolean(test);
        }
        catch (Exception E)
        {
            res = false;
        }

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
