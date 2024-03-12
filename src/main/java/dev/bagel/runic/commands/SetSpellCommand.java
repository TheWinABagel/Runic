package dev.bagel.runic.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class SetSpellCommand {
    public static final SuggestionProvider<CommandSourceStack> SUGGEST_SPELL = (ctx, builder) -> SharedSuggestionProvider.suggest(RunicRegistry.CustomRegistries.SPELL_REGISTRY.keySet().stream().map(ResourceLocation::toString), builder);

    public static void register(LiteralArgumentBuilder<CommandSourceStack> builder) {
        builder.then(Commands.literal("spells").requires(c -> c.hasPermission(2))
                .then(Commands.argument("spell", ResourceLocationArgument.id()).suggests(SUGGEST_SPELL).executes(ctx -> {
                            Player p = ctx.getSource().getPlayerOrException();
                            Spell spell = RunicRegistry.CustomRegistries.SPELL_REGISTRY.get(ctx.getArgument("spell", ResourceLocation.class));
                            if (spell != null) {
                                p.getData(RunicRegistry.Attachments.SPELL).setSpell(spell);
                                p.sendSystemMessage(Component.literal("Set spell to " + spell.getId()));
                            }
                            return 0;
                        }))
                );
    }
}
