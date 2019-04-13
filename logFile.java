import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class logFile {

    public static ArrayList<String> logMessages = new ArrayList<>();

    public static void addToLog(String logMessage){


        logMessages.add(logMessage);


    }

    public static void saveLog() throws IOException {

        for(String log: logMessages){
            System.out.println(log);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss-dd_MM_yyyy");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String fileName = "logOf: " + sdf.format(timestamp)+ ".txt";

        File file = new File("logFiles");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }

        PrintWriter saveFile = new PrintWriter(new FileWriter("logFiles/"+fileName));

        for(String log : logMessages){

            saveFile.println(log);
        }

        saveFile.close();

    }


}
