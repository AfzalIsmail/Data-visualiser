import java.util.ArrayList;

public class Statistics {

    public static double getSum(ArrayList<Object> a){
        double sum = 0;

        for(Object o:a){

            double d = 0;

            if(o == null || o.toString().length() == 0 || o.toString().equals(" ")){

                d = 0;

            }else {

                d = Double.parseDouble(o.toString());
            }

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

            if(o == null || o.toString().length() == 0 || o.toString().equals(" ")){

                d = 0;

            }else {

                d = Double.parseDouble(o.toString());
            }

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


}
