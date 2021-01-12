package me.kubajsa.easyflight.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class EasyFlightCommandTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;
            if (args.length == 1){
                List<String> strings= new ArrayList<>();

                strings.add("help");
                strings.add("check");
                strings.add("reload");
                strings.add("everyone");

                return strings;
            }else if (args.length == 2 & args[0].equalsIgnoreCase("everyone")){
                List<String> strings2= new ArrayList<>();

                strings2.add("on");
                strings2.add("off");

                return strings2;
            }
        }

        return null;
    }
}
