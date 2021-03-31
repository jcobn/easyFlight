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

    public static String getTurnOnTempMessage(Player target, String time) {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("turn-on-temp-message").replace("%player%", target.getName()).replace("%time%", time));
    }

    public static String getFlightEndsInThirtyMessage() {
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("flight-ends-in-thirty-sec"));
    }

    public static String getFlightEndsInMessage(int amount){
        return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("flight-ends-in").replace("%amount%", String.valueOf(amount)));
    }


    public static long calculateDuration(String durationStr) { //In seconds
        char unit = findUnit(durationStr);
        int unitIndex = durationStr.indexOf(unit);
        long duration = Long.parseLong(durationStr.substring(0, unitIndex == -1 ? durationStr.length() : unitIndex));

        switch (unit) {
            case 'm':
                duration *= 60;
                break;
            case 'h':
                duration *= 3600;
                break;
            case 'd':
                duration *= 86400;
                break;
            default:
                break;
        }

        return duration;
    }

    private static char findUnit(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i, i + 1).matches("[smhd]")) {
                return input.charAt(i);
            }
        }
        return 's';
    }
}
