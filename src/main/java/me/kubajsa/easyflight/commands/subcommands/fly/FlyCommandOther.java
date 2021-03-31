package me.kubajsa.easyflight.commands.subcommands.fly;

import me.kubajsa.easyflight.utils.FlyUtils;
import me.kubajsa.easyflight.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommandOther {

    public boolean otherSubCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (player.hasPermission("easyflight.fly.others")) {

            try {
                Player target = Bukkit.getPlayer(args[0]);
                FlyUtils.toggleFly(target);
                player.sendMessage(Utils.getToggleMessageSender(target));
            } catch (Exception e) {
                player.sendMessage("§cUsage: /fly <player> [time]");
            }

        } else {
            player.sendMessage(Utils.getNoPermissionMessage());
        }

        return false;
    }

}
