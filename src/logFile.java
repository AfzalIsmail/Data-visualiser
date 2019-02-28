import java.util.ArrayList;

public class logFile {

    public static ArrayList<String> logMessages = new ArrayList<>();

    public static void addToLog(String logMessage){


        logMessages.add(logMessage);


    }

    public static void printLog(){

        for(String log: logMessages){
            System.out.println(log);
        }
    }


}
