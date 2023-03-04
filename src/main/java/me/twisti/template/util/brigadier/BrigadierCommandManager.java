package me.twisti.template.util.brigadier;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import me.twisti.template.Main;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.server.MinecraftServer;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * A class that provides a static method for registering commands using Brigadier.
 */
public class BrigadierCommandManager {
    private static boolean hasThrown = false;
    private static boolean hasLoaded = false;

    /**
     * The Minecraft server that the commands will be registered with.
     */
    private static @Nullable MinecraftServer minecraftServer;

    /**
     * Private constructor to prevent instantiation.
     */
    private BrigadierCommandManager(){
    }

    /**
     * Sets the {@link MinecraftServer} instance.
     * This method should be called in your {@link JavaPlugin#onLoad()} method, before any {@link BrigadierCommand}´s are registered.
     *
     * @param server The {@link MinecraftServer} instance to register commands with.
     */
    public static void onLoad(MinecraftServer server){
        if (hasLoaded) throw new IllegalStateException("Called 'onLoad' method more than once");
         minecraftServer = server;
         hasLoaded = true;
    }


    /**
     * Registers a new {@link BrigadierCommand}
     *
     * @param commandName the main name of the command
     * @param literal     the {@link LiteralArgumentBuilder<CommandSourceStack>} representing the command
     * @param aliases     an array of aliases for the command, or null if there are no aliases
     */
    public static void addCommand(@NotNull String commandName, @NotNull LiteralArgumentBuilder<CommandSourceStack> literal, @Nullable String[] aliases){
        if (minecraftServer == null){
            if (hasThrown) return;
            hasThrown = true;
            throw new IllegalStateException("'minecraftServer' is null! Are you sure that you´ve called the 'onLoad' method already?");
        }
       try {
           CommandDispatcher<CommandSourceStack> commandDispatcher = minecraftServer.vanillaCommandDispatcher.getDispatcher();

           if (aliases != null) {
               for (String alias : aliases) {
                   LiteralArgumentBuilder<CommandSourceStack> aliasBuilder = LiteralArgumentBuilder.literal(alias);
                   aliasBuilder.redirect(literal.build());
               }
           }

           commandDispatcher.register(literal);
       } catch (NullPointerException e) {
           Main.getInstance().getLogger().warning("Failed to register command '" + commandName + "'. See stacktrace below: ");
           e.printStackTrace();
       }
    }
}
