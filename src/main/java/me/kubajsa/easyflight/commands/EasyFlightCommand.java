package me.kubajsa.easyflight.commands;

import me.kubajsa.easyflight.EasyFlight;
import me.kubajsa.easyflight.utils.FlyUtils;
import me.kubajsa.easyflight.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class EasyFlightCommand implements CommandExecutor {

    static EasyFlight plugin;

    public EasyFlightCommand(EasyFlight plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length == 0) {
                player.sendMessage("§bRunning §aEasyFlight §bVersion: §a" + EasyFlight.version);
                player.sendMessage("§bType §a/efly help §bfor help");
                if (EasyFlight.isBeta) {
                    player.sendMessage("§bEasyFlight: §cWarning: Running a beta version, expect bugs!");
                }//Warning message if isBeta is true
            } else if (args[0].equalsIgnoreCase("help")) {
                //Help message
                player.sendMessage("§9§l-------------------------");
                player.sendMessage("§9Commands:");
                player.sendMessage("§b/fly §9- §aToggle flight for yourself");
                player.sendMessage("§b/fly <player> §9- §aToggle flight for others");
                player.sendMessage("§b/efly help §9- §aShows this message");
                player.sendMessage("§b/efly check <player> §9- §aSee if someone's flying");
                player.sendMessage("§b/efly reload §9- §aReload the config");
                player.sendMessage("§b/efly everyone on|off §9- §aTurn on/off flight of everyone");
                player.sendMessage("§9Permissions:");
                player.sendMessage("§beasyflight.fly - §a/fly");
                player.sendMessage("§beasyflight.fly.others - §a/fly <player>");
                player.sendMessage("§beasyflight.check - §a/efly check <player>");
                player.sendMessage("§beasyflight.reload - §a/efly reload");
                player.sendMessage("§beasyflight.everyone - §a/efly everyone on|off");
                player.sendMessage("§9§l-------------------------");
                //Help message
            } else if (args[0].equalsIgnoreCase("check")) {

                if (player.hasPermission("easyflight.check")) {

                    if (args.length >= 2) {
                        FlyUtils.checkFly(args[1], player);
                    } else {
                        player.sendMessage("§cUsage: /efly check <player>");
                    }

                } else {
                    player.sendMessage(Utils.getNoPermissionMessage());
                }

            } else if (args[0].equalsIgnoreCase("reload")) {

                if (player.hasPermission("easyflight.reload")) {
                    FlyUtils.reload(player);
                } else {
                    player.sendMessage(Utils.getNoPermissionMessage());
                }

            } else if (args[0].equalsIgnoreCase("everyone")) {
                if (player.hasPermission("easyflight.everyone")) {

                    if (args.length == 2) {
                        if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("off")) {
                            if (args[1].equalsIgnoreCase("on")) {
                                for (Player target : Bukkit.getOnlinePlayers()) {
                                    FlyUtils.turnOnFly(target);
                                }
                                player.sendMessage(Utils.getTurnOnEveryoneMessage());
                            } else if (args[1].equalsIgnoreCase("off")) {
                                for (Player target : Bukkit.getOnlinePlayers()) {
                                    FlyUtils.turnOffFly(target);
                                }
                                player.sendMessage(Utils.getTurnOffEveryoneMessage());
                            }
                        } else {
                            player.sendMessage("§cUsage: /efly everyone on|off");
                        }

                    } else {
                        player.sendMessage("§cUsage: /efly everyone on|off");
                    }

                } else {
                    player.sendMessage(Utils.getNoPermissionMessage());
                }
            } else {
                player.sendMessage("§cUnknown command, try /efly help");
            }
        } else {
            //CONSOLE sender
            if (sender instanceof ConsoleCommandSender) {
                if (args.length != 0) {

                    if (args[0].equalsIgnoreCase("help")) {
                        System.out.println("-------------------------");
                        System.out.println("Commands:");
                        System.out.println("/fly - Toggle flight for yourself");
                        System.out.println("/fly <player> - Toggle flight for others");
                        System.out.println("/efly help - Shows this message");
                        System.out.println("/efly check <player> - See if someone's flying");
                        System.out.println("/efly reload - Reload the config");
                        System.out.println("/efly everyone on|off - Turn on/off flight of everyone");
                        System.out.println("Permissions:");
                        System.out.println("easyflight.fly - /fly");
                        System.out.println("easyflight.fly.others - /fly <player>");
                        System.out.println("easyflight.check - /efly check <player>");
                        System.out.println("easyflight.reload - /efly reload");
                        System.out.println("easyflight.everyone - /efly everyone on|off");
                        System.out.println("-------------------------");
                    } else {
                        System.out.println("This command either doesn't exist or it is only executable by a player");
                    }

                } else {
                    System.out.println("Running EasyFlight Version: " + EasyFlight.version);
                    System.out.println("Type /efly help for help");
                    if (EasyFlight.isBeta) {
                        System.out.println("Warn: Running a beta version, expect bugs!");
                    }
                }
            }
        }


        return true;
    }
}
