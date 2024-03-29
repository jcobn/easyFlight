package me.kubajsa.easyflight.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Log {

    public static void log(LogLevel level, String message) {
        if (message == null) {
            return;
        }

        switch (level) {
            case CRITICAL:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "[EasyFlight] &8[&4&lCRITICAL&r&8] &r" + message));
                break;
            case ERROR:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "[EasyFlight] &8[&c&lERROR&r&8] &r" + message));
                break;
            case WARNING:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "[EasyFlight] &8[&6&lWARNING&r&8] &r" + message));
                break;
            case INFO:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "[EasyFlight] &8[&e&lINFO&r&8] &r" + message));
                break;
            case SUCCESS:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "[EasyFlight] &8[&a&lSUCCESS&r&8] &r" + message));
                break;
            case DEFAULT:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "[EasyFlight] " + message));
                break;
            case PLAIN:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', message));
                break;
            case OUTLINE:
                Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&r" + message));
                break;
        }
    }

    public enum LogLevel {ERROR, WARNING, INFO, SUCCESS, OUTLINE, DEFAULT, PLAIN, CRITICAL}

}