package dev.bagel.runic.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class MultiblockCommand {

    public static void register(LiteralArgumentBuilder<CommandSourceStack> builder) {
        builder.then(Commands.literal("multiblocks").requires(c -> c.hasPermission(2)).then(Commands.argument("multiblock", ResourceLocationArgument.id()).requires(c -> c.hasPermission(2)).executes(ctx -> {
            Player p = ctx.getSource().getPlayerOrException();
            p.sendSystemMessage(Component.literal("nyi"));
            return 0;
        })));
    }
}
