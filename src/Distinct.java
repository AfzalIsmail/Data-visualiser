import java.util.ArrayList;

public class Distinct {

    public static ArrayList<Object> getDistinct (ArrayList<Object> data)
    {
        ArrayList<Object> distinct = new ArrayList();


        for (int i=0; i<data.size(); i++)
        {
            if (i==0)
            {
                Object x = data.get(i);
                distinct.add(x);
                //System.out.println(x);
            }
            else
            {
                Object x = data.get(i);
                //System.out.println(x);
                int counter=0; //internal variable to count if there are same elements

                for (int j=0; j<distinct.size(); j++)
                {
                    Object y = distinct.get(j);
                    //System.out.println("Y is : "+y);

                    if (x.equals(y)) //if the elements are same
                    {
                        counter++;
                        break;
                    }
                }

                if (counter == 0) //if there is no same element in the arraylist, the current element is added.
                {
                    distinct.add(x);
                }
            }

        }
        //System.out.println("Final distinct elements : "+distinct);
        //System.out.println("No of elements : "+distinct.size());
        return distinct;

    }

}
