package me.kubajsa.easyflight.commands;

import me.kubajsa.easyflight.EasyFlight;
import me.kubajsa.easyflight.commands.subcommands.fly.FlyCommandOther;
import me.kubajsa.easyflight.commands.subcommands.fly.FlyCommandTemp;
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

public class FlyCommand implements CommandExecutor {

    static EasyFlight plugin;

    public FlyCommand(EasyFlight plugin) {
        this.plugin = plugin;
    }

    FlyCommandOther flyCommandOther = new FlyCommandOther();
    FlyCommandTemp flyCommandTemp = new FlyCommandTemp(plugin);

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
                if (flyCommandOther.otherSubCommand(sender, command, label, args)) return true;

            } else if (args.length == 2) {
                if (flyCommandTemp.timeSubCommand(sender, command, label, args)) return true;
            }

        } else if (sender instanceof ConsoleCommandSender) {
            if (args.length == 1) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    FlyUtils.toggleFly(target);
                    Log.log(Log.LogLevel.SUCCESS, "§bYou have toggled flight for §a" + target.getName());
                } else {
                    Log.log(Log.LogLevel.ERROR, "§cCouldn't find that player");
                }
            } else if (args.length == 2) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    Log.log(Log.LogLevel.ERROR, "§cCouldn't find that player");
                    return true;
                }
                String timeString = args[1];
                if (timeString.matches("\\d+([smhd]|seconds?|minutes?|hours?|days?)")) {
                    long duration = Utils.calculateDuration(timeString);
                    FlyUtils.turnOnFly(target);
                    Log.log(Log.LogLevel.SUCCESS, Utils.getTurnOnTempMessage(target, timeString));
                    TempFlyUtils tempFlyUtils = new TempFlyUtils(plugin);
                    tempFlyUtils.addTempFly(target, duration);
                } else {
                    Log.log(Log.LogLevel.ERROR, "§cTime format: <amount><s|m|h|d>");
                    Log.log(Log.LogLevel.ERROR, "§cExample: 40m (Forty minutes)");
                }

            }
        } else {
            Log.log(Log.LogLevel.ERROR, "§cUsage: fly <player> [time]");
        }
        return true;
    }
}

