package me.twisti.template.util.brigadier;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An abstract class representing a Minecraft command using the Brigadier command system.
 */
public abstract class BrigadierCommand {
    /**
     * The main command name
     *
     * @return the name of the command
     */
    public abstract @NotNull String name();

    /**
     * Returns a {@link LiteralArgumentBuilder<CommandSourceStack>} representing the command.
     *
     * @param literal the base {@link LiteralArgumentBuilder<CommandSourceStack>} for the command
     * @return the modified {@link LiteralArgumentBuilder<CommandSourceStack>} for the command
     */
    public abstract @NotNull LiteralArgumentBuilder<CommandSourceStack> literal(LiteralArgumentBuilder<CommandSourceStack> literal);

    /**
     * The commands aliases
     *
     * @return an array of aliases for the command, or null if there are no aliases
     */
    public @Nullable String[] aliases() {
        return null;
    }

    /**
     * Registers the command using the {@link BrigadierCommandManager} class.
     */
    public BrigadierCommand() {
        BrigadierCommandManager.addCommand(name(), literal(LiteralArgumentBuilder.literal(name())), aliases());
    }
}
