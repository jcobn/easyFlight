package me.kubajsa.easyflight;

import org.bukkit.entity.Player;

public class FlyUtils {

    static EasyFlight plugin;

    public FlyUtils(EasyFlight plugin) {
        this.plugin = plugin;
    }


    public static void toggleFly(Player player){
        boolean getAllowFlight = player.getAllowFlight();
        if (getAllowFlight == false){
            player.setAllowFlight(true);
            player.setFlying(true);
        }else {
            player.setAllowFlight(false);
            player.setFlying(false);
        }
        player.sendMessage("§bToggled flight for player §a" + player.getName());
    }
}
