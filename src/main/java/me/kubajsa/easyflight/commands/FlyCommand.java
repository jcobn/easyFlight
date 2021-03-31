package me.kubajsa.easyflight.commands;

import me.kubajsa.easyflight.EasyFlight;
import me.kubajsa.easyflight.utils.FlyUtils;
import me.kubajsa.easyflight.utils.Log;
import me.kubajsa.easyflight.utils.TempFlyUtils;
import me.kubajsa.easyflight.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.time.temporal.TemporalAccessor;

public class FlyCommand implements CommandExecutor {

    static EasyFlight plugin;

    public FlyCommand(EasyFlight plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                if (player.hasPermission("easyflight.fly")) {
                    FlyUtils.toggleFly(player);
                } else {
                    player.sendMessage(Utils.getNoPermissionMessage());
                }
            }
            if (args.length == 1) {
                if (player.hasPermission("easyflight.fly.others")) {

                    try {
                        Player target = Bukkit.getPlayer(args[0]);
                        FlyUtils.toggleFly(target);
                        player.sendMessage(Utils.getToggleMessageSender(target));
                    } catch (Exception e) {
                        player.sendMessage("§cUsage: /fly <player> [time]");
                    }

                } else {
                    player.sendMessage(Utils.getNoPermissionMessage());
                }
            } else if (args.length == 2) {
                if (player.hasPermission("easyflight.fly.others")) {
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage("§cUsage: /fly <player> [time]");
                        return true;
                    }
                    String timeString = args[1];
                    if (timeString.matches("\\d+([smhd]|seconds?|minutes?|hours?|days?)")) {
                        long duration = Utils.calculateDuration(timeString);
                        FlyUtils.turnOnFly(target);
                        player.sendMessage("§bTurned on flight for " + target.getName() + " for " + timeString);
                        TempFlyUtils tempFlyUtils = new TempFlyUtils(plugin);
                        tempFlyUtils.addTempFly(target, duration);
                    } else {
                        player.sendMessage("§cTime format: <amount><s|m|h|d>");
                        player.sendMessage("§cExample: 40m (Forty minutes)");
                    }
                } else {
                    player.sendMessage(Utils.getNoPermissionMessage());
                }
            }

        } else if (sender instanceof ConsoleCommandSender) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    FlyUtils.toggleFly(target);
                    Log.log(Log.LogLevel.SUCCESS, "You have toggled flight for " + target.getName());
                } else {
                    Log.log(Log.LogLevel.ERROR, "Couldn't find that player");
                }
            } else {
                Log.log(Log.LogLevel.ERROR, "Usage: fly <player> [time]");//TODO Time
            }
        }

        return true;
    }
}
