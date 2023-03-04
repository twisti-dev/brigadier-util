package me.twisti.template.main.listener;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class ListenerManager {
    public ListenerManager(Plugin plugin) {
        PluginManager manager = plugin.getServer().getPluginManager();
    }
}
