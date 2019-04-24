import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Distinct {


    /**
     * function to get the distinct values from an arraylist of categorical data as well as their frequency
     * @param data arraylist of objects
     * @return map
     */
    public static Map<Object, Integer> getDistinct (ArrayList<Object> data){


        Map <Object, Integer> counter = new HashMap<>();

        //checks if map already contains the object.
        for(Object a: data){

            //if it does, it increases the counter value by 1
            if(counter.containsKey(a)){

                counter.put(a, counter.get(a)+1);

             //if not, it adds the object to the map and sets its counter value to 1
            }else{

                counter.put(a,1);
            }
        }
        //System.out.println("Final distinct elements : "+distinct);
        //System.out.println("No of elements : "+distinct.size());
        return counter;

    }

}
