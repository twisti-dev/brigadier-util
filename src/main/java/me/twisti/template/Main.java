package me.twisti.template;

import me.twisti.template.main.command.CommandManager;
import me.twisti.template.main.listener.ListenerManager;
import me.twisti.template.util.brigadier.BrigadierCommandManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.commands.CloneCommands;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    private static Main instance;
    private static MinecraftServer minecraftServer;
    private CommandManager commandManager;
    private ListenerManager listenerManager;

    @Override
    public void onLoad() {
        instance = this;
        minecraftServer = MinecraftServer.getServer();
        BrigadierCommandManager.onLoad(minecraftServer);
        commandManager = new CommandManager();
        listenerManager = new ListenerManager(this);
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        minecraftServer = null;
        commandManager = null;
        listenerManager = null;
        instance = null;
    }


    public static MinecraftServer getMinecraftServer(){
        return minecraftServer;
    }

    public static Main getInstance() {
        return instance;
    }

    public Main(){
        if (instance != null) throw new IllegalStateException("Plugin already initialized!");
        instance = this;
    }
}
