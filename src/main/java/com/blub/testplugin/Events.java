package com.blub.testplugin;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import org.bukkit.event.player.*;

public class Events implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        e.setJoinMessage(e.getPlayer().getDisplayName() + ChatColor.LIGHT_PURPLE + " has joined the server!");
    }

    @EventHandler
    public void onJoin(PlayerQuitEvent e){
        e.setQuitMessage(e.getPlayer().getDisplayName() + ChatColor.LIGHT_PURPLE + " has left the server");
    }
}
