package me.kubajsa.easyflight;

import me.kubajsa.easyflight.commands.EasyFlightCommand;
import me.kubajsa.easyflight.commands.EasyFlightCommandTabCompleter;
import me.kubajsa.easyflight.commands.FlyCommand;
import me.kubajsa.easyflight.commands.subcommands.fly.FlyCommandTemp;
import me.kubajsa.easyflight.utils.FlyUtils;
import me.kubajsa.easyflight.utils.Log;
import me.kubajsa.easyflight.utils.TempFlyUtils;
import me.kubajsa.easyflight.utils.Utils;
import org.bukkit.plugin.java.JavaPlugin;

public final class EasyFlight extends JavaPlugin {

    public static final String VERSION = "1.8-b1";

    @Override
    public void onEnable() {
        getCommand("fly").setExecutor(new FlyCommand(this));
        getCommand("easyflight").setExecutor(new EasyFlightCommand(this));
        getCommand("easyflight").setTabCompleter(new EasyFlightCommandTabCompleter());

        MetricsLite metricsLite = new MetricsLite(this, 10112);
        //Init config

        new FlyUtils(this);
        new Utils(this);
        new TempFlyUtils(this);
        new FlyCommandTemp(this);
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Log.log(Log.LogLevel.INFO, "Enabling EasyFlight v" + VERSION);
        Log.log(Log.LogLevel.INFO, "Author: Kubajsa");
        Log.log(Log.LogLevel.INFO, "Use /efly help for more info");
        Log.log(Log.LogLevel.SUCCESS, "Finished loading EasyFlight");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
