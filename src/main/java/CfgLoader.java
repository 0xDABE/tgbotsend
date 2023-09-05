import java.io.File;
import java.io.FileNotFoundException;
import java.util.Objects;
import java.util.Scanner;

public class CfgLoader{
    public static String returnable = "";

    public static int load(String path){
        File file = new File(path);
        if (!file.exists()){
            ColoredMessage.red("Config not found at \"" + path + "\"");
            return -1;
        }

        try(Scanner scanf = new Scanner(file)){
            while (scanf.hasNextLine()){
                String temp = scanf.nextLine();
                String[] arr;
                if (temp.contains("Token=")) {
                    arr = temp.split("\"");
                    SenderBot.token = arr[1];
                    continue;
                }
                if (temp.contains("ChatID=")) {
                    arr = temp.split("\"");
                    SenderBot.ChatID = arr[1];
                    continue;
                }
                if (temp.contains("BotName=")) {
                    arr = temp.split("\"");
                    SenderBot.BotName = arr[1];
                    continue;
                }
                if (temp.contains("Attempts=")) {
                    arr = temp.split("\"");
                    try{
                        SenderBot.Attempts = Integer.parseInt(arr[1]);
                        if (SenderBot.Attempts < 1){
                            ColoredMessage.yellow("Number of attempts in config cannot be less than zero\n  Continuing with Attempts=3...\n");
                            SenderBot.Attempts = 3;
                        }
                    } catch (NumberFormatException f){
                        ColoredMessage.yellow("Incorrect number of attempts in config\n  Continuing with Attempts=3...\n");
                    }
                    continue;
                }
                if (temp.contains("Limit=")) {
                    arr = temp.split("\"");
                    try{
                        SenderBot.limitInMiB = Integer.parseInt(arr[1]);
                    } catch (NumberFormatException f){
                        ColoredMessage.yellow("Incorrect limit in config\n  Continuing with Limit=50...\n");
                    }
                    continue;
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }


        if (Objects.equals(SenderBot.token, "")
                || Objects.equals(SenderBot.BotName, "") || Objects.equals(SenderBot.ChatID, "")){
            ColoredMessage.red("Config is not correct");
            return -1;
        }
        return 0;
    }
}
