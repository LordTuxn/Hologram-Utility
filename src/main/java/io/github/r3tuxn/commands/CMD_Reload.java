package github.r3tuxn.commands;

import github.r3tuxn.HologramDisplay;
import github.r3tuxn.HologramUtility;
import github.r3tuxn.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CMD_Reload implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(sender.hasPermission("hologramUtility.reload")) {
            HologramUtility.getPlugin().saveConfig();
            HologramUtility.getPlugin().reloadConfig();
            sender.sendMessage(Utils.getInfoMessageFormat("Successfully reloaded config!"));

            HologramUtility.getHologramDisplays().forEach(HologramDisplay::updateLeaderboard);
            sender.sendMessage(Utils.getInfoMessageFormat("Successfully reloaded holograms!"));
        }
        return true;
    }
}
