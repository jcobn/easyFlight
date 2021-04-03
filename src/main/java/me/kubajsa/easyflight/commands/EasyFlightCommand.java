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

                player.sendMessage("§bRunning §aEasyFlight §bVersion: §a" + EasyFlight.VERSION);
                player.sendMessage("§bType §a/efly help §bfor help");
            } else if (args[0].equalsIgnoreCase("help")) {
                //Help message
                player.sendMessage("§9§l-------------------------");
                player.sendMessage("§9Commands:");
                player.sendMessage("§b/fly §9- §aToggle flight for yourself");
                player.sendMessage("§b/fly <player> [time] §9- §aToggle flight for others");
                player.sendMessage("§b/efly help §9- §aShows this message");
                player.sendMessage("§b/efly check <player> §9- §aSee if someone's flying");
                player.sendMessage("§b/efly reload §9- §aReload the config");
                player.sendMessage("§b/efly everyone <on|off> [time] §9- §aTurn on/off flight of everyone");
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
                        FlyUtils.checkFly(args[1], sender);
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

                    if (args.length == 2 || args.length == 3) {
                        if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("off")) {
                            if (args[1].equalsIgnoreCase("on")) {
                                if (args.length == 3) {
                                    String timeString = args[2];
                                    if (timeString.matches("\\d+([smhd]|seconds?|minutes?|hours?|days?)")) {
                                        long duration = Utils.calculateDuration(timeString);
                                        for (Player target : Bukkit.getOnlinePlayers()) {
                                            TempFlyUtils tempFlyUtils = new TempFlyUtils(plugin);
                                            tempFlyUtils.addTempFly(target, duration);
                                        }
                                        player.sendMessage(Utils.getTurnOnEveryoneMessage());

                                    } else {
                                        player.sendMessage("§cTime format: <amount><s|m|h|d>");
                                        player.sendMessage("§cExample: 40m (Forty minutes)");
                                        return true;
                                    }
                                } else {
                                    for (Player target : Bukkit.getOnlinePlayers()) {
                                        FlyUtils.turnOnFly(target);
                                    }
                                    player.sendMessage(Utils.getTurnOnEveryoneMessage());
                                }
                                Log.log(Log.LogLevel.SUCCESS, Utils.getTurnOnEveryoneMessage());
                            } else if (args[1].equalsIgnoreCase("off")) {
                                for (Player target : Bukkit.getOnlinePlayers()) {
                                    FlyUtils.turnOffFly(target);
                                }
                                player.sendMessage(Utils.getTurnOffEveryoneMessage());
                            }
                        } else {
                            player.sendMessage("§cUsage: /efly everyone on|off [time]");
                        }

                    } else {
                        player.sendMessage("§cUsage: /efly everyone on|off [time]");
                    }

                } else {
                    player.sendMessage(Utils.getNoPermissionMessage());
                }
            } else {
                player.sendMessage("§cUnknown command, try /efly help");
            }
        } else if (sender instanceof ConsoleCommandSender) {
            //CONSOLE sender
            if (args.length != 0) {

                if (args[0].equalsIgnoreCase("help")) {
                    Log.log(Log.LogLevel.DEFAULT, "§a-------------------------");
                    Log.log(Log.LogLevel.DEFAULT, "§bCommands:");
                    Log.log(Log.LogLevel.DEFAULT, "§b/fly - Toggle flight for yourself");
                    Log.log(Log.LogLevel.DEFAULT, "§b/fly <player> [time] - Toggle flight for others");
                    Log.log(Log.LogLevel.DEFAULT, "§b/efly help - Shows this message");
                    Log.log(Log.LogLevel.DEFAULT, "§b/efly check <player> - See if someone's flying");
                    Log.log(Log.LogLevel.DEFAULT, "§b/efly reload - Reload the config");
                    Log.log(Log.LogLevel.DEFAULT, "§b/efly everyone <on|off> [time] - Turn on/off flight of everyone");
                    Log.log(Log.LogLevel.DEFAULT, "§bPermissions:");
                    Log.log(Log.LogLevel.DEFAULT, "§beasyflight.fly - /fly");
                    Log.log(Log.LogLevel.DEFAULT, "§beasyflight.fly.others - /fly <player>");
                    Log.log(Log.LogLevel.DEFAULT, "§beasyflight.check - /efly check <player>");
                    Log.log(Log.LogLevel.DEFAULT, "§beasyflight.reload - /efly reload");
                    Log.log(Log.LogLevel.DEFAULT, "§beasyflight.everyone - /efly everyone on|off");
                    Log.log(Log.LogLevel.DEFAULT, "§a-------------------------");
                } else if (args[0].equalsIgnoreCase("reload")) {
                    FlyUtils.reload(sender);
                } else if (args[0].equalsIgnoreCase("check")) {
                    if (args.length >= 2) {
                        FlyUtils.checkFly(args[1], sender);
                    } else {
                        Log.log(Log.LogLevel.ERROR, "§cUsage: /efly check <player>");
                    }

                } else if (args[0].equalsIgnoreCase("everyone")) {
                    if (args.length == 2 || args.length == 3) {
                        if (args[1].equalsIgnoreCase("on") || args[1].equalsIgnoreCase("off")) {
                            if (args[1].equalsIgnoreCase("on")) {
                                if (args.length == 3) {
                                    String timeString = args[2];
                                    if (timeString.matches("\\d+([smhd]|seconds?|minutes?|hours?|days?)")) {
                                        long duration = Utils.calculateDuration(timeString);
                                        for (Player target : Bukkit.getOnlinePlayers()) {
                                            TempFlyUtils tempFlyUtils = new TempFlyUtils(plugin);
                                            tempFlyUtils.addTempFly(target, duration);
                                        }
                                        Log.log(Log.LogLevel.SUCCESS, Utils.getTurnOnEveryoneMessage());
                                    } else {
                                        Log.log(Log.LogLevel.ERROR, "§cTime format: <amount><s|m|h|d>");
                                        Log.log(Log.LogLevel.ERROR, "§cExample: 40m (Forty minutes)");
                                        return true;
                                    }
                                } else {
                                    for (Player target : Bukkit.getOnlinePlayers()) {
                                        FlyUtils.turnOnFly(target);
                                    }
                                    Log.log(Log.LogLevel.SUCCESS, Utils.getTurnOnEveryoneMessage());
                                }
                            } else if (args[1].equalsIgnoreCase("off")) {
                                for (Player target : Bukkit.getOnlinePlayers()) {
                                    FlyUtils.turnOffFly(target);
                                }
                                Log.log(Log.LogLevel.SUCCESS, Utils.getTurnOffEveryoneMessage());
                            }
                        } else {
                            Log.log(Log.LogLevel.ERROR, "§cUsage: /efly everyone on|off [time]");
                        }
                    } else {
                        Log.log(Log.LogLevel.ERROR, "§cUsage: /efly everyone on|off [time]");
                    }
                } else {
                    Log.log(Log.LogLevel.ERROR, "§cThis command either doesn't exist or it is only executable by a player");
                }

            } else {
                Log.log(Log.LogLevel.INFO, "§bRunning §aEasyFlight §bVersion: §a" + EasyFlight.VERSION);
                Log.log(Log.LogLevel.INFO, "§bType /efly help for help");
            }
        }


        return true;
    }
}
