package dev.bagel.runic.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import dev.bagel.runic.spell.modifiers.SpellModifierRegistry;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class SpellModifierCommand {
    public static final SuggestionProvider<CommandSourceStack> SUGGEST_MODIFIERS = (ctx, builder) -> SharedSuggestionProvider.suggest(SpellModifierRegistry.INSTANCE.getKeys().stream().map(ResourceLocation::toString), builder);
    public static void register(LiteralArgumentBuilder<CommandSourceStack> builder) {
        builder.then(Commands.literal("modifiers").requires(c -> c.hasPermission(2)).then(Commands.argument("modifier", ResourceLocationArgument.id()).requires(c -> c.hasPermission(2)).suggests(SUGGEST_MODIFIERS).executes(ctx -> {
            Player p = ctx.getSource().getPlayerOrException();
            DynamicHolder<SpellModifier> modifierHolder = SpellModifierRegistry.INSTANCE.holder(ctx.getArgument("modifier", ResourceLocation.class));
            if (!modifierHolder.isBound()) return 0;
            SpellModifier.ModifierData data = modifierHolder.get().getModifierData();
            StringBuilder builder1 = new StringBuilder();
            builder1.append("\nSpell: ").append(data.spell());
            builder1.append("\nMaxLevel: ").append(data.maxLevel());
            builder1.append("\nX: ").append(data.x()).append(", Y: ").append(data.y());
            builder1.append("\nRequirements: ").append(data.requirements());
            builder1.append("\nAdded RuneCosts: ").append(data.runeCosts());
            String strrg = builder1.toString();
            p.sendSystemMessage(Component.literal(strrg));
            return 0;
        })));
    }
}
