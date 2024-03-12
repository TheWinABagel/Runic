package dev.bagel.runic.spell.spells;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BasicDamageSpell extends Spell {
    public BasicDamageSpell(int castXp) {
        super(castXp);

    }

    @Override
    public SpellModifier defaultModifier() {
        return RunicRegistry.SpellModifiers.PROJECTILE_MODIFIER.get();
    }

    @Override
    public InteractionResult onHitEntity(Level level, Player player, Entity target, ItemStack usedStack, Spell spell) {
        target.hurt(level.damageSources().playerAttack(player), 5f);
        return InteractionResult.SUCCESS;
    }


}
