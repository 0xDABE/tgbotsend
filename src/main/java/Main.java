import java.io.File;

public class Main {
    public static long total = 0;
    public static int errors = 0;

    public static void main(String[] args){
        String jarPath = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        try {
            String absoluteJarPath = new File(jarPath).getCanonicalPath();
            File jarFile = new File(absoluteJarPath);
            jarPath = jarFile.getParent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (args.length > 0){
            CfgLoader.load(jarPath + File.separator + "sndconfig.txt");
            for (String arg : args) recDir.exec(new File(arg));
            if (errors == 0) ColoredMessage.green("Done " + SenderBot.getTime(Main.total));
            else ColoredMessage.yellow("Done " + SenderBot.getTime(Main.total) + "\nErrors: " + errors);
        }
        else {
            System.out.print("""
                                     
                                                    ******************
                                                    *      HELP      *
                                                    ******************
                                
                    Description:
                        "snd" is the script (may be binary or kinda bash or bat, but must be in PATH) that uses this java utility to recursively send files from specified folders or just a files with TelegramAPI
                        
                    Usage:
                        snd [pathsORfiles]
                        Count of paths cannot be more than 9 (more will be ignored)
                        Paths must be separated with spaces
                        
                    Examples:
                        snd movies
                        snd documents music photos
                        snd file1.txt videos
                        
                                                    *******************
                                                    *  snd by 0xDABE  *
                                                    *******************""");
        }
    }
}



