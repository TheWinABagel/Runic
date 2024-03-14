package dev.bagel.runic.spell.spells;

import dev.bagel.runic.Runic;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import dev.bagel.runic.spell.modifiers.SpellModifierRegistry;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
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
    public Object2IntMap<SpellModifier> defaultModifier() {
        Object2IntMap<SpellModifier> map = new Object2IntOpenHashMap<>();
        map.put(SpellModifierRegistry.INSTANCE.getValue(Runic.loc("base_modifier")), 1);
        return map;
    }

    @Override
    public InteractionResult onHitEntity(Level level, Player player, ItemStack usedStack, Entity hitEntity, Spell spell) {
        hitEntity.hurt(player.damageSources().playerAttack(player), 5f);
        hitEntity.invulnerableTime = 0; //funni
        return InteractionResult.SUCCESS;
    }


}
