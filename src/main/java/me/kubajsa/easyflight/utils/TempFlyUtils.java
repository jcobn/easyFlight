package me.kubajsa.easyflight.utils;

import me.kubajsa.easyflight.EasyFlight;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TempFlyUtils {

    static EasyFlight plugin;

    public TempFlyUtils(EasyFlight plugin) {
        this.plugin = plugin;
    }

    long count = 0;
    int task;

    public void addTempFly(Player target, long duration) {

        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            @Override
            public void run() {
                Log.log(Log.LogLevel.INFO, "e" + duration + " " + count); //TODO Debug message
                count++;
                if (count == duration){
                    target.setFlying(false);
                    target.setAllowFlight(false);
                    count = 0;
                    Bukkit.getScheduler().cancelTask(task);
                }
            }
        }, 0, 20);
    }

}
