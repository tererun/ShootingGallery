package run.tere.plugin.shootinggallery.utils;

import org.bukkit.command.CommandSender;

public class ChatUtil {

    public static final String PREFIX = "§f[§c§l射的§f] ";

    public static void sendMessage(CommandSender commandSender, String message) {
        commandSender.sendMessage(PREFIX + message);
    }

    public static void sendClerkMessage(CommandSender commandSender, String message) {
        commandSender.sendMessage("§f射的屋台のおっちゃん§a: §r" + message);
    }

}
