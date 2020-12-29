package me.kubajsa.easyflight.commands;

import me.kubajsa.easyflight.EasyFlight;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class EasyFlightCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                player.sendMessage("§bRunning §aEasyFlight §bVersion: §a" + EasyFlight.version);
                player.sendMessage("§bType §a/efly help §bfor help");
                if (EasyFlight.isBeta){player.sendMessage("§bEasyFlight: §cWarning: Running a beta version, expect bugs!");}
            }else if (args.length == 1 && args[0].equalsIgnoreCase("help")){
                player.sendMessage("§9§l-------------------------");
                player.sendMessage("§9Commands:");
                player.sendMessage("§b/fly §9- §aToggle flight for yourself; §beasyflight.fly");
                player.sendMessage("§b/fly <player> §9- §aToggle flight for others; §beasyflight.fly.others");
                player.sendMessage("§b/efly help §9- §aShows this message");
                player.sendMessage("§b/efly check <player> §9- §aSee if someone's flying; §beasyflight.check");
                player.sendMessage("§9§l-------------------------");
            }else if (args[0].equalsIgnoreCase("check")){
                if (player.hasPermission("easyflight.check")){

                    try {
                        Player target = Bukkit.getPlayer(args[1]);
                        player.sendMessage("§bIs §a" + target.getName() + " §bFlying? §a" + target.getAllowFlight());
                    }catch (Exception e){
                        player.sendMessage("§cUsage: /efly check <player>");
                    }
                }else {player.sendMessage("§cYou do not have permission to do that!");}
            }
        }


        return true;
    }
}
