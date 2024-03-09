package dev.bagel.runic.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public class RunicCommands {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher, CommandBuildContext ctx) {
        LiteralArgumentBuilder<CommandSourceStack> builder = Commands.literal("runic");
        SetSpellCommand.register(builder);
        MultiblockCommand.register(builder);
        dispatcher.register(builder);
    }
}
