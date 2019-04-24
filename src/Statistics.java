/**
 * @author Afzal Ismail
 * @version 1.0
 */
import java.util.ArrayList;
import java.util.Collections;

public class Statistics {

    /**
     * function to calculate the sum of data in a numerical data column
     * @param a arraylist of object
     * @return sum
     */
    public static double getSum(ArrayList<Object> a){
        double sum = 0;

        for(Object o:a){

            double d = pDouble(o);

            sum = sum + d;
        }

        return sum;
    }

    /**
     * function to get the mean in a numerical data column
     * @param a arraylist of object
     * @param sumS sum of arraylist a
     * @return mean value
     */
    public static double getMean (ArrayList<Object> a, Double sumS){
         //double sum = 0;
         double mean = 0;
         int sampleSize = 0;

         //the number of missing data is removed from the sample size
         sampleSize = a.size() - missingData(a);
         System.out.println(sampleSize);
         mean = sumS/sampleSize;

         return mean;
    }

    /**
     * function to get the variance an arraylist
     * @param a arraylist of data
     * @param meanM mean of arraylist a
     * @return variance value
     */
    public static double getVariance (ArrayList<Object> a, Double meanM){

        //double sum = 0;
        //double mean = 0;
        int sampleSize = 0;
        double sumSq = 0;
        double var = 0;


        sampleSize = a.size() - missingData(a);

        //System.out.println(a.size());
        //System.out.println(sampleSize);
        //System.out.println(MissingValues.missingData(a));

        for(Object o: a){

            //double d = 0;
            double diff = 0;
            double sq = 0;

            //does not take into consideration missing values
            if(o == null || o.toString().length() == 0 || o.toString().equals(" ")){

                //d = 0;
                //System.out.println("One null found");

            }else {

                double d = Double.parseDouble(o.toString());

                //System.out.println(d);

                //difference between d and the mean
                diff = d - meanM;

                //squared of the difference
                sq = diff * diff;

                //sum of the squared differences in all the arraylist
                sumSq = sumSq + sq;

            }

        }

        //variance value
        var = sumSq/sampleSize;

        return var;


    }

    /**
     * function to get the standard deviation
     * @param varV variance value
     * @return standard deviation value
     */
    public static double getStDeviation(Double varV){

        //square root of the variance value
        double sd = Math.sqrt(varV);

        return sd;


    }

    /**
     * function to get the minimum value
     * @param a arraylist of object
     * @return min value
     */
    public static double getMin(ArrayList<Object> a){

        ArrayList<Double> arr = new ArrayList<>();

        //does not take into account any missing values
        for(Object o: a){

            double d = pDouble(o);

            if(d ==0 && o.equals(" ")){

            }else {
                arr.add(d);
            }
        }

        //gets the minimum value in the arraylist
        double min = Collections.min(arr);

        return min;
    }

    /**
     * function to get the maximum value in the arraylist
     * @param a arraylist of objects
     * @return max value
     */
    public static double getMax(ArrayList<Object> a){

        ArrayList<Double> arr = new ArrayList<>();

        for(Object o: a){

            double d = pDouble(o);

            if(d ==0 && o.equals(" ")){

            }else {
                arr.add(d);
            }

        }

        //get the maximum value in the arraylist
        double max = Collections.max(arr);

        return max;
    }

    /**
     * get the median value in the arraylist
     * @param a arraylist of objects
     * @return
     */
    public static double getMedian(ArrayList<Object> a){

        ArrayList<Double> arr = numericalSorting.sortAsc(a);

        double median;

        int size = arr.size();

        double firstVal = arr.get(size/2);

        double secondVal = arr.get(size/2 - 1);

        //check if the size of the arraylist is in odd or even numbers
        //if it is even, the average of the two middle numbers is taken
        if(size % 2 == 0){

            double sumMiddleNumbers = firstVal + secondVal;

            median = sumMiddleNumbers/2;

        }else{

            median = firstVal;

        }

        return median;

    }

    /**
     * function that counts the number of missing records in a column
     * @param a arraylist of object for a column
     * @return number of missing values
     */
    public static int missingData(ArrayList<Object> a){

        int missingVal = 0;

        for(Object o: a){

            //check if the record is null
            if(o == null || o == "" || o.equals("") || o.equals(" ")){
                missingVal++;
            }
        }

        return missingVal;
    }

    /**
     * function to parse an object to double
     * @param a object
     * @return double
     */
    public static double pDouble(Object a){

        double d = 0;

        //if the object is null, it is taken as 0
        if(a == null || a.toString().length() == 0 || a.toString().equals(" ")){

            d = 0;

        }else {

            d = Double.parseDouble(a.toString());
        }

        return d;

    }


}
