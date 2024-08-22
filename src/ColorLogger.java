public class ColorLogger {

    public static void logSuccess(String logging) {
        System.out.println("\u001B[32;1m" + logging + "\u001B[0m"); // Bright Green
    }
    
    public static void logDebug(String logging) {
        System.out.println("\u001B[34m" + logging + "\u001B[0m"); // Blue
    }

    public static void logInfo(String logging) {
        System.out.println("\u001B[32m" + logging + "\u001B[0m"); // Green
    }
    
    public static void logError(String logging) {
        System.out.println("\u001B[31m" + logging + "\u001B[0m"); // Red
    }

    public static void logWarning(String logging) {
        System.out.println("\u001B[38;5;214m" + logging + "\u001B[0m"); // Orange
    }

}