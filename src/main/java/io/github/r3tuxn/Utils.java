package github.r3tuxn;

public class Utils {

    // Player Messages
    private static final String messagePrefix = HologramUtility.getPlugin().getConfig().getString("message-prefix") + " ";

    public static String getInfoMessageFormat(String info) {
        return messagePrefix + "§a" + info;
    }

    public static String getErrorMessageFormat(String error) {
        return messagePrefix + "§c" + error;
    }

}
