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

        target.setAllowFlight(true);
        target.setFlying(true);
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            count++;
            if (count == duration - 30){
                target.sendMessage(Utils.getFlightEndsInThirtyMessage());
            }
            if (count >= duration - 10){
                target.sendMessage(Utils.getFlightEndsInMessage((int) (duration - count)));
            }
            if (count == duration){
                target.setFlying(false);
                target.setAllowFlight(false);
                count = 0;
                Bukkit.getScheduler().cancelTask(task);
            }
        }, 0, 20);
    }

    public void addTempFly(Player target, long duration, boolean toggle) {

        target.setAllowFlight(toggle);
        target.setFlying(toggle);
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            count++;
            if (count == duration - 30){
                target.sendMessage(Utils.getFlightEndsInThirtyMessage());
            }
            if (count >= duration - 10){
                target.sendMessage(Utils.getFlightEndsInMessage((int) (duration - count)));
            }
            if (count == duration){
                target.setFlying(!toggle);
                target.setAllowFlight(!toggle);
                count = 0;
                Bukkit.getScheduler().cancelTask(task);
            }
        }, 0, 20);
    }

}
