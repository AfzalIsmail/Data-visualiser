import java.util.ArrayList;
import java.util.Collections;

public class numericalSorting {

    public static ArrayList<Double> sortAsc(ArrayList<Object> a) {

        ArrayList<Double> arrayList = new ArrayList<>();


            for (Object o : a) {

                double d = Statistics.pDouble(o);

                arrayList.add(d);

            }

            Collections.sort(arrayList);


        return arrayList;


        }

        public static ArrayList<Double> sortDesc (ArrayList < Object > a) {

            ArrayList<Double> arrayList = sortAsc(a);

            Collections.reverse(arrayList);

            return arrayList;

        }

        public static ArrayList<String> catSortAsc(ArrayList<Object> a){

        ArrayList<String> arrayList = new ArrayList<>();

        for(Object o:a){

            String s = o.toString();

            arrayList.add(s);
        }

        Collections.sort(arrayList);

        return  arrayList;

        }

        public static ArrayList<String> catSortDcs(ArrayList<Object> a){

        ArrayList<String> arrayList = catSortAsc(a);

        Collections.reverse(arrayList);

        return arrayList;
        }





}
