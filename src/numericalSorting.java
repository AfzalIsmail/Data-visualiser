/**
 * @author Afzal Ismail
 * @version 2.0
 */

import java.util.ArrayList;
import java.util.Collections;

public class numericalSorting {

    /**
     * function to sort numerical values in ascending order
     * @param a arraylist of object
     * @return arraylist in ascending order
     */
    public static ArrayList<Double> sortAsc(ArrayList<Object> a) {

        ArrayList<Double> arrayList = new ArrayList<>();

            //convert each object to double
            for (Object o : a) {

                if(o == null || o.toString().length() == 0 || o.toString().equals(" ")){

                }else {

                    double d = Statistics.pDouble(o);
                    arrayList.add(d);

                }

            }

            //sort arraylist
            Collections.sort(arrayList);

        return arrayList;

        }

    /**
     * sort arraylist in descending order
     * @param a arraylist of object
     * @return arraylist in descending order
     */
    public static ArrayList<Double> sortDesc (ArrayList < Object > a) {

            ArrayList<Double> arrayList = sortAsc(a);

            //reverse the order of the arraylist
            Collections.reverse(arrayList);

            return arrayList;

    }

    /**
     * function to sort non-numerical data in ascending order
     * @param a arraylist of object
     * @return arraylist sorted in ascending order
     */
    public static ArrayList<String> catSortAsc(ArrayList<Object> a){

        ArrayList<String> arrayList = new ArrayList<>();

        for(Object o:a){

            String s = o.toString();

            arrayList.add(s);
        }

        Collections.sort(arrayList);

        return  arrayList;

    }

    /**
     * function to sort non-numerical data in descending order
     * @param a arraylist of object
     * @return arraylist sorted in descending order
     */
    public static ArrayList<String> catSortDcs(ArrayList<Object> a){

        ArrayList<String> arrayList = catSortAsc(a);

        Collections.reverse(arrayList);

        return arrayList;

    }





}
