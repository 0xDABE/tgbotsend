import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class CfgLoader{
    public static String returnable = "";
    public static boolean CompatibilityModeOff = false;

    public static int load(String path){
        File file = new File(path);
        if (!file.exists()){
            ColoredMessage.red("Config not found at \"" + path + "\"", CompatibilityModeOff);
            return -1;
        }

        try(Scanner scanf = new Scanner(file)){
            while (scanf.hasNextLine()){
                String temp = scanf.nextLine();
                String[] arr;
                if (temp.contains("ColoredOutput=")){
                    arr = temp.split("\"");
                    if (Objects.equals(arr[1].toLowerCase(Locale.ROOT), "true"))
                        CompatibilityModeOff = true;
                    else if (!arr[1].toLowerCase(Locale.ROOT).equals("false")) ColoredMessage.yellow("    ColoredOutput is not True, but also not a False. " +
                            "Launching with ColoredOutput=\"False\" (case does not matter)", CompatibilityModeOff);
                    continue;
                }
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
                            ColoredMessage.yellow("Number of attempts in config cannot be less than zero\n  Continuing with Attempts=3...\n", CompatibilityModeOff);
                            SenderBot.Attempts = 3;
                        }
                    } catch (NumberFormatException f){
                        ColoredMessage.yellow("Incorrect number of attempts in config\n  Continuing with Attempts=3...\n", CompatibilityModeOff);
                    }
                    continue;
                }
                if (temp.contains("Limit=")) {
                    arr = temp.split("\"");
                    try{
                        SenderBot.limitInMiB = Integer.parseInt(arr[1]);
                    } catch (NumberFormatException f){
                        ColoredMessage.yellow("Incorrect limit in config\n  Continuing with Limit=50...\n", CompatibilityModeOff);
                    }
                    continue;
                }
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }


        if (Objects.equals(SenderBot.token, "")
                || Objects.equals(SenderBot.BotName, "") || Objects.equals(SenderBot.ChatID, "")){
            ColoredMessage.red("Config is not correct", CompatibilityModeOff);
            return -1;
        }
        return 0;
    }
}
