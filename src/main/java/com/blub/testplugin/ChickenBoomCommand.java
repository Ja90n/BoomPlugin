package com.blub.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class ChickenBoomCommand implements CommandExecutor {

    private TestPlugin main;

    public ChickenBoomCommand(TestPlugin main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        main.getConfig();

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "You can't use this command as console!");
        } else {
            Player p = (Player) sender;
            if (p.hasPermission("boomplugin.chickenboom")) {
                if (args.length == 1) {
                    try {
                        //if it is an int check the max Chickenpower
                        int boompower = Integer.parseInt(args[0]);
                        int connumber = main.getConfig().getInt("Chickenpower-Limit");
                        if (boompower < connumber) {
                            String config = (String) main.getConfig().get("Chickenboom-Message");
                            if (!(config.equals(""))) {
                                p.sendMessage(ChatColor.RED + config);
                            }
                            for (int i = 0; i < boompower; i++){
                                p.getWorld().getEntities();
                                Entity e = (Chicken) p.getWorld().spawnEntity(p.getLocation().add(0, 2, 0), EntityType.CHICKEN);
                                e.setInvulnerable(true);
                            }
                        } else {
                            //if arg 0 is higher than the boompower
                            p.sendMessage(ChatColor.RED + "Choose a number smaller than " + main.getConfig().getInt("Chickenpower-Limit"));
                        }
                        //if it is not an int
                    } catch (IllegalArgumentException e) {
                        p.sendMessage(ChatColor.RED + "You need to fill in a number");
                    }
                } else {
                    p.sendMessage(ChatColor.RED + "Invalid arguments. Try: /chickenboom <chickenpower>");
                }
            } else {
                p.sendMessage(ChatColor.RED + "You don't have permission to use this command!");
            }
        }
        return false;
    }
}