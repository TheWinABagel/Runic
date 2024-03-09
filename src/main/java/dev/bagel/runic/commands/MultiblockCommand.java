package dev.bagel.runic.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import dev.bagel.runic.multiblock.Multiblock;
import dev.bagel.runic.multiblock.MultiblockRegistry;
import dev.bagel.runic.registry.RunicRegistry;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class MultiblockCommand {
    public static final SuggestionProvider<CommandSourceStack> SUGGEST_MULTIBLOCKS = (ctx, builder) -> SharedSuggestionProvider.suggest(MultiblockRegistry.INSTANCE.getKeys().stream().map(ResourceLocation::toString), builder);

    public static void register(LiteralArgumentBuilder<CommandSourceStack> builder) {
        builder.then(Commands.literal("multiblocks").requires(c -> c.hasPermission(2)).then(Commands.argument("multiblock", ResourceLocationArgument.id()).requires(c -> c.hasPermission(2)).suggests(SUGGEST_MULTIBLOCKS).executes(ctx -> {
            Player p = ctx.getSource().getPlayerOrException();
            Multiblock multiblock = MultiblockRegistry.INSTANCE.getValue(ctx.getArgument("multiblock", ResourceLocation.class));
            if (multiblock != null) {
                multiblock.list.forEach(string -> {
                    p.sendSystemMessage(Component.literal("Line: " + string));
                });
                multiblock.map.forEach((character, block) -> {
                    p.sendSystemMessage(Component.literal("Map: '" + character + "', block: " + block));
                });
            }
            return 0;
        })));
    }
}
