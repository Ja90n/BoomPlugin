package com.blub.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BoomCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player p = (Player) sender;

            //sellect
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("test")) {
                    p.sendMessage(ChatColor.LIGHT_PURPLE + "Test succesfull, ready for takeoff");
                } else{
                    try{
                        int boompower = Integer.parseInt(args[0]);
                        if (boompower < 500){
                            p.sendMessage(ChatColor.RED + "Hehe boom");
                            p.getWorld().createExplosion(p.getLocation(), boompower);
                        }else{
                            p.sendMessage(ChatColor.DARK_RED + "Choose a number smaller than 500");
                        }
                    } catch (IllegalArgumentException e){
                        p.sendMessage(ChatColor.RED + "You need to fill in a number");
                    }
                }
            } else{
                p.sendMessage(ChatColor.RED + "Invalid arguments. Try: /boom <boompower>");
            }

        } else{
            sender.sendMessage(ChatColor.DARK_RED + "You are not a player!");
        }

        return false;
    }
}
