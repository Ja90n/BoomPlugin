package com.blub.testplugin;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class TestPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        getCommand("boom").setExecutor(new BoomCommand(this));
        getCommand("chickenboom").setExecutor(new ChickenBoomCommand(this));
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Boom plugin has been loaded!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Bukkit.getConsoleSender().sendMessage(ChatColor.LIGHT_PURPLE + "Boom plugin has been stopped");
    }
}
