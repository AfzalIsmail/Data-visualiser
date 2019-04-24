/**
 * @author Afzal Ismail
 * @version 2.0
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class logFile {

    public static ArrayList<String> logMessages = new ArrayList<>();

    /**
     * this function is called everytime an action requires saving
     * @param logMessage - a string which will summarise the action that has taken place
     */
    public static void addToLog(String logMessage){

        logMessages.add(logMessage);

    }

    /**
     * this function is called when there is a request to close the program
     * it will save the strings in the arraylist in a text file in folder called logFiles
     * @throws IOException for writing in a text file
     */
    public static void saveLog() throws IOException {

        //for(String log: logMessages){
            //System.out.println(log);
        //}

        //getting the timestamp when the program is closed as this will be used as the file name
        SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss-dd_MM_yyyy");

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        String fileName = "logOf: " + sdf.format(timestamp)+ ".txt";

        File file = new File("logFiles");

        //checking if the folder "logFiles" exist. If not, it will be created
        if (!file.exists()) {

            if (file.mkdir()) {

                System.out.println("Directory is created!");

            } else {

                System.out.println("Failed to create directory!");

            }
        }

        //writing each string in the arraylist in the log file
        PrintWriter saveFile = new PrintWriter(new FileWriter("logFiles/"+fileName));

        for(String log : logMessages){

            saveFile.println(log);
        }

        saveFile.close();

    }


}
