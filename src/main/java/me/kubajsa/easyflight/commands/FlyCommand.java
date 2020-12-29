package me.kubajsa.easyflight.commands;

import me.kubajsa.easyflight.FlyUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (args.length == 0){
                if (player.hasPermission("easyflight.fly")){
                    FlyUtils.toggleFly(player);
                }else {player.sendMessage("§cYou do not have permission to do that!");}
            } if (args.length == 1){
                if (player.hasPermission("easyflight.fly.others")){

                    try {
                        Player target = Bukkit.getPlayer(args[0]);
                        FlyUtils.toggleFly(target);
                        player.sendMessage("§bToggled flight for player §a" + target.getName());
                    }catch (Exception e){
                        player.sendMessage("§cUsage: /fly <player>");
                    }

                }else {player.sendMessage("§cYou do not have permission to do that!");}
            }

        }

        return true;
    }
}
