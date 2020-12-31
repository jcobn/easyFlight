package me.kubajsa.easyflight;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("toggle-message")).replace("%player%", player.getName()));
    }

    public static void turnOnFly(Player player){
        player.setAllowFlight(true);
        player.setFlying(true);
        //TODO Add custom message to the config
    }

    public static void turnOffFly(Player player){
        player.setAllowFlight(false);
        player.setFlying(false);
        //TODO Add custom message to the config
    }

    public static void reload(Player player) {
        player.sendMessage("§bReloading config...");
        plugin.reloadConfig();
        player.sendMessage(" ");
        player.sendMessage("§bConfig reloaded!");
    }

    public static void checkFly(String name, Player player) {
            Player target = Bukkit.getPlayer(name);
            if (target == null) {
                player.sendMessage("§cUsage: /efly check <player>");
            } else {
                boolean isAllowed = target.getAllowFlight();
                boolean isFlying = target.isFlying();
                String one = "§bIs §a" + target.getName() + " §ballowed to fly? §a" + isAllowed;
                String two = "§bIs §a" + target.getName() + " §bflying? §a" + isFlying;
                player.sendMessage(one.replace("true", "Yes").replace("false", "No"));
                player.sendMessage(two.replace("true", "Yes").replace("false", "No"));
            }

}
}
