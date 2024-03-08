package dev.bagel.runic.spell;

import dev.bagel.runic.Spellbook;
import dev.bagel.runic.reg.registry.CustomRegistries;
import dev.bagel.runic.reg.rune_registry.RuneType;
import dev.bagel.runic.spell.casting.CastType;
import dev.bagel.runic.spell.spells.EmptySpell;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public abstract class Spell {

    public RuneType primaryRune = RuneType.BLANK;
    public CastType castType = CastType.PROJECTILE;
    private final Object2IntMap<RuneType> runeCosts = new Object2IntOpenHashMap<>();
    public int level;
    public int castXp;

    public Spell(int level, int castXp) {
        this.level = level;
        this.castXp = castXp;
        for (RuneType type : RuneType.values()) {
            runeCosts.put(type, 0);
        }
    }
    public Spell(int level, int castXp, RuneType primaryRune) {
        this(level, castXp);
        this.primaryRune = primaryRune;
    }

    public Spell(int level, int castXp, RuneType primaryRune, CastType type) {
        this(level, castXp, primaryRune);
        this.castType = type;
    }

    public int getCostForRune(RuneType type) {
        return runeCosts.getInt(type);
    }

    public Object2IntMap<RuneType> getRuneCosts() {
        return runeCosts;
    }

    public ResourceLocation getId(){
        return CustomRegistries.SPELL_REGISTRY.getKey(this);
    }

    protected int setCostForRune(RuneType type, int value) {
        return runeCosts.put(type, value);
    }

    public boolean is(Spell other) {
        return CustomRegistries.SPELL_REGISTRY.getId(other) == CustomRegistries.SPELL_REGISTRY.getId(this);
    }

    public InteractionResult onSpellCast(Level level, Player player, ItemStack usedStack) {
        return InteractionResult.PASS;
    }

    public InteractionResult onHitBlock(Level level, Player player, BlockHitResult hitResult) {
        return InteractionResult.PASS;
    }

    public InteractionResult onHitEntity(Level level, Player player, LivingEntity target, ItemStack usedStack) {
        return InteractionResult.PASS;
    }

}
