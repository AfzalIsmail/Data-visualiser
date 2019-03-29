import java.util.ArrayList;
import java.util.Collections;

public class Statistics {

    public static double getSum(ArrayList<Object> a){
        double sum = 0;

        for(Object o:a){

            double d = 0;

            /*if(o == null || o.toString().length() == 0 || o.toString().equals(" ")){

                d = 0;

            }else {

                d = Double.parseDouble(o.toString());
            }*/

            d = pDouble(o);

            sum = sum + d;
        }

        return sum;
    }

    public static double getMean (ArrayList<Object> a, Double sumS){
         //double sum = 0;
         double mean = 0;
         int sampleSize = 0;

         sampleSize = a.size();
         mean = sumS/sampleSize;

         return mean;
    }

    public static double getVariance (ArrayList<Object> a, Double meanM){

        //double sum = 0;
        //double mean = 0;
        int sampleSize = 0;
        double sumSq = 0;
        double var = 0;


        sampleSize = a.size();

        for(Object o: a){

            double d = 0;
            double diff = 0;
            double sq = 0;

            /*if(o == null || o.toString().length() == 0 || o.toString().equals(" ")){

                d = 0;

            }else {

                d = Double.parseDouble(o.toString());
            }*/

            d = pDouble(o);

            diff = d - meanM;

            sq = diff * diff;

            sumSq = sumSq + sq;

        }

        var = sumSq/sampleSize;

        return var;


    }

    public static double getStDeviation(Double varV){

        double sd = Math.sqrt(varV);

        return sd;


    }

    public static double getMin(ArrayList<Object> a){

        ArrayList<Double> arr = new ArrayList<>();

        for(Object o: a){

            double d = pDouble(o);

            if(d ==0 && o.equals(" ")){

            }else {
                arr.add(d);
            }
        }

        double min = Collections.min(arr);

        return min;
    }

    public static double getMax(ArrayList<Object> a){

        ArrayList<Double> arr = new ArrayList<>();

        for(Object o: a){

            double d = pDouble(o);

            if(d ==0 && o.equals(" ")){

            }else {
                arr.add(d);
            }

        }

        double max = Collections.max(arr);

        return max;
    }

    public static double getMedian(ArrayList<Object> a){

        ArrayList<Double> arr = numericalSorting.sortAsc(a);

        double median;

        int size = arr.size();

        double firstVal = arr.get(size/2);

        double secondVal = arr.get(size/2 - 1);

        if(size % 2 == 0){

            double sumMiddleNumbers = firstVal + secondVal;

            median = sumMiddleNumbers/2;

        }else{

            median = firstVal;

        }

        return median;

    }


    public static double pDouble(Object a){

        double d = 0;

        if(a == null || a.toString().length() == 0 || a.toString().equals(" ")){

            d = 0;

        }else {

            d = Double.parseDouble(a.toString());
        }

        return d;

    }


}
