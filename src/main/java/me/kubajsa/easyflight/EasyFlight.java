package me.kubajsa.easyflight;

import me.kubajsa.easyflight.commands.EasyFlightCommand;
import me.kubajsa.easyflight.commands.EasyFlightCommandTabCompleter;
import me.kubajsa.easyflight.commands.FlyCommand;
import me.kubajsa.easyflight.utils.FlyUtils;
import me.kubajsa.easyflight.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class EasyFlight extends JavaPlugin {

    public static String version = "1.7.2";
    public static Boolean isBeta = false;

    @Override
    public void onEnable() {
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("easyflight").setExecutor(new EasyFlightCommand(this));
        getCommand("easyflight").setTabCompleter(new EasyFlightCommandTabCompleter());

        MetricsLite metricsLite = new MetricsLite(this, 10112);
        //Init config

        FlyUtils utils = new FlyUtils(this);
        Utils utils1 = new Utils(this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Easy Flight Enabled");
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Version: " + version);
        if (isBeta) {
            getServer().getConsoleSender().sendMessage("§bEasyFlight: §cWarning: Running a beta version, expect bugs!");
        } //Warning message if isBeta is true
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Easy Flight Disabled");
        getServer().getConsoleSender().sendMessage(ChatColor.GOLD + "Version: " + version);
    }
}
