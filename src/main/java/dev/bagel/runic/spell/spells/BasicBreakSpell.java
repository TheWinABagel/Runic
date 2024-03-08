package dev.bagel.runic.spell.spells;

import dev.bagel.runic.Spellbook;
import dev.bagel.runic.reg.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class BasicBreakSpell extends Spell {
    public BasicBreakSpell(int level, int xp, CastType type) {
        super(level, xp, RuneType.EARTH, type);
    }

    @Override
    public InteractionResult onHitBlock(Level level, Player caster, BlockHitResult hitResult) {
        if (level.isClientSide()) return InteractionResult.PASS;
        level.destroyBlock(hitResult.getBlockPos(), true, caster);
        return InteractionResult.SUCCESS;
    }
}
