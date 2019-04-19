import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Distinct {


    /**
     *
     * @param data
     * @return
     */
    public static Map<Object, Integer> getDistinct (ArrayList<Object> data){


        Map <Object, Integer> counter = new HashMap<>();

        for(Object a: data){
            if(counter.containsKey(a)){
                counter.put(a, counter.get(a)+1);
            }else{
                counter.put(a,1);
            }
        }
        //System.out.println("Final distinct elements : "+distinct);
        //System.out.println("No of elements : "+distinct.size());
        return counter;

    }

}
