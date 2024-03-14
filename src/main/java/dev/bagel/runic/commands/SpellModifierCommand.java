package dev.bagel.runic.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import dev.bagel.runic.spell.spell_modifier.SpellModifierRegistry;
import dev.bagel.runic.spell.spell_modifier.SpellModifierType;
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
            SpellModifierType modifier = SpellModifierRegistry.INSTANCE.getValue(ctx.getArgument("modifier", ResourceLocation.class));
            StringBuilder builder1 = new StringBuilder();
            builder1.append("\nSpell: ").append((modifier.getSpell() != null) ? modifier.getSpell() : "null :(");
            builder1.append("\nMaxLevel: ").append(modifier.getMaxLevel());
            builder1.append("\nX: ").append(modifier.getX()).append(", Y: ").append(modifier.getY());
            builder1.append("\nRequirements: ").append(modifier.getRequirements());
            builder1.append("\nMainEffects: ").append(modifier.getEffects_of());
            String strrg = builder1.toString();
            p.sendSystemMessage(Component.literal(strrg));
            return 0;
        })));
    }
}
