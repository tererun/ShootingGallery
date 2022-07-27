package run.tere.plugin.shootinggallery.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import run.tere.plugin.shootinggallery.utils.ChatUtil;

public class ShootingGalleryMainCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equalsIgnoreCase("sg")) return false;
        if (!sender.isOp()) {
            ChatUtil.sendMessage(sender, "§c必要な権限がありません");
            return false;
        }
        if (args.length == 0) {
            sender.sendMessage("§f========== [§c§l射的§f] ==========");
            sender.sendMessage("§a/sg§f: このヘルプを表示");
            sender.sendMessage("§a/sg list§f: 射的のリストを表示");
            sender.sendMessage("§f==================================");
        } else {
            if (args[0].equalsIgnoreCase("list")) {
                if (!(sender instanceof Player)) {
                    ChatUtil.sendMessage(sender, "§cプレイヤーからのみ実行可能です");
                    return true;
                }
                Player player = (Player) sender;
                // TODO: 2022/07/28 open inventory
            } else {
                ChatUtil.sendMessage(sender, "§cコマンドが存在しません");
            }
        }
        return true;
    }

}
