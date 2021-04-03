package me.kubajsa.easyflight.utils;

import me.kubajsa.easyflight.EasyFlight;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyUtils {

    static EasyFlight plugin;

    public FlyUtils(EasyFlight plugin) {
        this.plugin = plugin;
    }


    public static void toggleFly(Player player) {
        boolean getAllowFlight = player.getAllowFlight();
        if (getAllowFlight == false) {
            player.setAllowFlight(true);
            player.setFlying(true);
        } else {
            player.setAllowFlight(false);
            player.setFlying(false);
        }
        player.sendMessage(Utils.getToggleMessage(player));
    }

    public static void turnOnFly(Player player) {
        player.setAllowFlight(true);
        player.setFlying(true);
        player.sendMessage(Utils.getTurnOnMessage(player));
    }

    public static void turnOffFly(Player player) {
        player.setAllowFlight(false);
        player.setFlying(false);
        player.sendMessage(Utils.getTurnOffMessage(player));
    }

    public static void reload(CommandSender sender) {
        if (sender instanceof Player){
            Player player = (Player) sender;
            player.sendMessage("§bReloading config...");
            plugin.reloadConfig();
            player.sendMessage(" ");
            player.sendMessage("§bConfig reloaded!");
        }
        else {
            Log.log(Log.LogLevel.DEFAULT, "§bReloading config...");
            plugin.reloadConfig();
            Log.log(Log.LogLevel.DEFAULT, " ");
            Log.log(Log.LogLevel.SUCCESS, "§bConfig Reloaded!");
        }
    }

    public static void checkFly(String name, CommandSender sender) {
        Player target = Bukkit.getPlayer(name);
        if (target == null) {
            sender.sendMessage("§cUsage: /efly check <player>");
        } else {
            boolean isAllowed = target.getAllowFlight();
            boolean isFlying = target.isFlying();
            String one = "§bIs §a" + target.getName() + " §ballowed to fly? §a" + isAllowed;
            String two = "§bIs §a" + target.getName() + " §bflying? §a" + isFlying;
            sender.sendMessage(one.replace("true", "Yes").replace("false", "No"));
            sender.sendMessage(two.replace("true", "Yes").replace("false", "No"));
        }
    }
}
