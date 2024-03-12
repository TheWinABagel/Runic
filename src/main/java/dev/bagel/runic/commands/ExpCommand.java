package dev.bagel.runic.commands;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class ExpCommand {
    public static void register(LiteralArgumentBuilder<CommandSourceStack> builder) {
        builder.then(Commands.literal("exp").requires(c -> c.hasPermission(2))
                .then(Commands.argument("xp", ResourceLocationArgument.id()).suggests(SetSpellCommand.SUGGEST_SPELL).executes(ctx -> {
                    Player p = ctx.getSource().getPlayerOrException();
                    Spell spell = Spell.getSpellFromId(ctx.getArgument("xp", ResourceLocation.class));
                    if (spell != null) {
                        var xpComponent = p.getData(RunicRegistry.Attachments.EXPERIENCE);
                        boolean levelled = xpComponent.addExperience(spell, 10D);
                        String xp = String.format("Adding 10 XP, for a total of %s" + (levelled ? " and you got a level!" : ""), xpComponent.getXP(spell));
                        p.sendSystemMessage(Component.literal(xp));
                    }
                    return 0;
                }
        )));
    }
}
