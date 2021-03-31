package me.kubajsa.easyflight.commands.subcommands.fly;

import me.kubajsa.easyflight.EasyFlight;
import me.kubajsa.easyflight.utils.FlyUtils;
import me.kubajsa.easyflight.utils.TempFlyUtils;
import me.kubajsa.easyflight.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommandTemp {

    static EasyFlight plugin;

    public  FlyCommandTemp(EasyFlight plugin){
        this.plugin = plugin;
    }

    public boolean timeSubCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("easyflight.fly.others")) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                player.sendMessage("§cUsage: /fly <player> [time]");
                return true;
            }
            String timeString = args[1];
            if (timeString.matches("\\d+([smhd]|seconds?|minutes?|hours?|days?)")) {
                long duration = Utils.calculateDuration(timeString);
                player.sendMessage(Utils.getTurnOnTempMessage(target, timeString));
                TempFlyUtils tempFlyUtils = new TempFlyUtils(plugin);
                tempFlyUtils.addTempFly(target, duration);
            } else {
                player.sendMessage("§cTime format: <amount><s|m|h|d>");
                player.sendMessage("§cExample: 40m (Forty minutes)");
            }
        } else {
            player.sendMessage(Utils.getNoPermissionMessage());
        }

        return false;
    }

}
