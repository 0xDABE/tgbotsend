
public class ColoredMessage {
    public static void red(String message){
        System.out.print("\u001B[31m" + message + "\u001B[0m");
    }
    public static void green(String message){
        System.out.print("\u001B[32m" + message + "\u001B[0m");
    }
    public static void blue(String message){
        System.out.print("\u001b[36m" + message + "\u001B[0m");
    }
    public static void yellow(String message){
        System.out.print("\u001B[33m" + message + "\u001B[0m");
    }
}
