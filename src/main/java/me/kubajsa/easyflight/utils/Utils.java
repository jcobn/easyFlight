package me.kubajsa.easyflight.utils;

import me.kubajsa.easyflight.EasyFlight;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Utils {

    static EasyFlight plugin;

    public Utils(EasyFlight plugin) {
        this.plugin = plugin;
    }

    public static String getToggleMessage(Player player) {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("toggle-message")).replace("%player%", player.getName());
    }

    public static String getTurnOnMessage(Player player) {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("turn-on-message").replace("%player%", player.getName()));
    }

    public static String getTurnOffMessage(Player player) {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("turn-off-message").replace("%player%", player.getName()));
    }

    public static String getNoPermissionMessage() {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString(("no-permission-message")));
    }

    public static String getToggleMessageSender(Player player) {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("toggle-message-sender")).replace("%player%", player.getName());
    }

    public static String getTurnOnEveryoneMessage() {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("turn-on-everyone-message"));
    }

    public static String getTurnOffEveryoneMessage() {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("turn-off-everyone-message"));
    }
}
