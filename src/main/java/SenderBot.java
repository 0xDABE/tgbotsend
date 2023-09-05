import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;
import java.util.Objects;

public class SenderBot extends TelegramLongPollingBot {
    public static String FilePath;
    public static String token = "";
    public static String BotName = "";
    public static String ChatID = "";
    public static int Attempts = 3;
    public static int limitInMiB = 50;

    public void sendFile(){
        File file = new File(FilePath);
        String size = classifyFileSize(file);
        if (Objects.equals(size, "0 bytes")){
            ColoredMessage.blue(FilePath + "(" + size + "): ");
            ColoredMessage.red(" ERROR (Empty file)\n");
            Main.errors += 1;
            return;
        }

        if (Objects.equals(size.substring(0, 5), "Error")){
            ColoredMessage.blue(FilePath + "(" + size.substring(5) + "): ");
            ColoredMessage.red("  ERROR (over 50 mib)\n");
            Main.errors += 1;
            return;
        }

        SendDocument sd = new SendDocument();
        sd.setChatId(ChatID);
        sd.setDocument(new InputFile(file));
        sd.setCaption("File:\n" + file.getName() + "\n\nRelative path:\n" + FilePath +"\n\nAbsolutePath:\n" + file.getAbsolutePath() + "\n\nSize:\n" + size);

        for (int i = 1; i < Attempts + 1; i++) {
            long start = System.currentTimeMillis();
            try {
                if (i == 1)ColoredMessage.blue(FilePath + "(" + size + "): ");
                execute(sd);
                long finish = System.currentTimeMillis();
                Main.total += (finish - start);
                ColoredMessage.blue(getTime(finish - start));
                ColoredMessage.green("  OK\n");
                return;
            } catch (TelegramApiException e) {
                if (i == 1) ColoredMessage.yellow("   Try: ");
                ColoredMessage.yellow(i + "  ");
            }
        }
        ColoredMessage.red("  ERROR\n");
        Main.errors += 1;
    }
    public SenderBot(String path){
        FilePath = path;
    }

    public static String getTime(long ms){
        if (ms < 1000) return ms + "ms";
        else return String.format("%.1f",(float) ms / 1000)  + "s";
    }

    public static String classifyFileSize(File file) {
        long fileSize = file.length();

        if (fileSize >= 1024 * 1024) {
            double sizeInMiB = (double) fileSize / (1024 * 1024);
            if (sizeInMiB > limitInMiB) return "Error" + String.format("%.2f MiB", sizeInMiB);
            return String.format("%.2f MiB", sizeInMiB);
        } else if (fileSize >= 1024) {
            double sizeInKiB = (double) fileSize / 1024;
            return String.format("%.2f KiB", sizeInKiB);
        } else {
            return fileSize + " bytes";
        }
    }

    @Override
    public void onUpdateReceived(Update update){}

    @Override
    public String getBotUsername() {
        return BotName;
    }

    @Override
    public String getBotToken() {
        return token;
    }


}
