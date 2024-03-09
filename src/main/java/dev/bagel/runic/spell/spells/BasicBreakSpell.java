package dev.bagel.runic.spell.spells;

import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class BasicBreakSpell extends Spell {
    public BasicBreakSpell(int level, int xp, CastType type) {
        super(level, xp, RuneType.EARTH, type);
        setCostForRune(RuneType.BODY, 1);
        setCostForRune(RuneType.EARTH, 1);
    }

    @Override
    public InteractionResult onHitBlock(Level level, Player caster, BlockHitResult hitResult, Spell spell) {
        if (level.isClientSide()) return InteractionResult.PASS;
        level.destroyBlock(hitResult.getBlockPos(), true, caster);
        return InteractionResult.SUCCESS;
    }
}
