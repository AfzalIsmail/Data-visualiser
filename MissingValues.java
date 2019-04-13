import java.util.ArrayList;

public class MissingValues {

    public static int missingData(ArrayList<Object> a){

        int missingVal = 0;

        for(Object o: a){

            if(o == null || o == "" || o.equals("") || o.equals(" ")){
                missingVal++;
            }
        }

        return missingVal;
    }
}
