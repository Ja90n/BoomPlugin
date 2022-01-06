package com.blub.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BoomCommand implements CommandExecutor {

    private TestPlugin main;

    public BoomCommand(TestPlugin main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        main.getConfig();

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You can't use this command as console!");
        } else {
            Player p = (Player) sender;
            if (p.hasPermission("boomplugin.boom")) {
                if (args.length == 1) {
                    try {
                        //if it is an int check the max boompower
                        int boompower = Integer.parseInt(args[0]);
                        int connumber = main.getConfig().getInt("Boompower-Limit");
                        if (boompower < connumber) {
                            String config = (String) main.getConfig().get("Boom-Message");
                            if (!(config.equals(""))) {
                                p.sendMessage(ChatColor.RED + config);
                            }
                                p.getWorld().createExplosion(p.getLocation(), boompower);
                            } else {
                                //if arg 0 is higher than the boompower
                                p.sendMessage(ChatColor.RED + "Choose a number smaller than " + main.getConfig().getInt("Boompower-Limit"));
                            }
                            //if it is not an int
                        } catch (IllegalArgumentException e) {
                            p.sendMessage(ChatColor.RED + "You need to fill in a number");
                        }
                    } else {
                        p.sendMessage(ChatColor.RED + "Invalid arguments. Try: /boom <boompower>");
                    }
                }
            }
        return false;
    }
}
