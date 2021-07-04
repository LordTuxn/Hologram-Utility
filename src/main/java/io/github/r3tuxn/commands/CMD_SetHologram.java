package github.r3tuxn.commands;

import github.r3tuxn.HologramDisplay;
import github.r3tuxn.HologramUtility;
import github.r3tuxn.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMD_SetHologram implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player)sender;
            if (sender.hasPermission("hologramUtility.set")){
                if (args.length == 1) {
                    if(HologramUtility.getHologramDisplays().stream().anyMatch(holo -> holo.getHologramName().equalsIgnoreCase(args[0]))) {
                        HologramDisplay hologram = HologramUtility.getHologramDisplays().stream().filter(holo -> holo.getHologramName().equalsIgnoreCase(args[0])).findFirst().get();
                        hologram.setLocation(player.getLocation());
                        player.sendMessage(Utils.getInfoMessageFormat("Successfully set hologram location of §f" + hologram.getHologramName() + "§7!"));
                    } else {
                        player.sendMessage(Utils.getErrorMessageFormat("Could not find hologram with the name §f" + args[0] + "§7!"));
                    }
                } else {
                    player.sendMessage(Utils.getErrorMessageFormat("§lUsage: §c/sethologram <name>"));
                    player.sendMessage("§7------- §6§lHologram List §7-------");
                    for(HologramDisplay holo : HologramUtility.getHologramDisplays()) {
                        player.sendMessage("§a> §f" + holo.getHologramName());
                    }
                    player.sendMessage("§7-----------------------------");
                }

            }
        }
        return true;
    }
}
