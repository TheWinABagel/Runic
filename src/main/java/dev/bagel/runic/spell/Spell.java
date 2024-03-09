package dev.bagel.runic.spell;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.bagel.runic.Runic;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.casting.CastType;
import dev.shadowsoffire.placebo.codec.CodecProvider;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Spell {


    public RuneType primaryRune = RuneType.BLANK;
    public ResourceLocation id;
    public CastType castType = CastType.TOUCH;
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

    public Spell(int level, int castXp, RuneType primaryRune, CastType castType) {
        this(level, castXp, primaryRune);
        this.castType = castType;
    }


    public int getCostForRune(RuneType type) {
        return runeCosts.getInt(type);
    }

    public Object2IntMap<RuneType> getRuneCosts() {
        return runeCosts;
    }

    public ResourceLocation getId() {
        return RunicRegistry.CustomRegistries.SPELL_REGISTRY.getKey(this);
    }

    protected Spell setCostForRune(RuneType type, int value) {
        runeCosts.put(type, value);
        return this;
    }

    public boolean canAfford(Player player) {
        AtomicBoolean canAfford = new AtomicBoolean(true);
        this.getRuneCosts().forEach((type, integer) -> {
            if (player.getData(RunicRegistry.Attachments.RUNES).getRunes(type) - integer < 0) {
                canAfford.set(false);
            }
        });
        return canAfford.get();
    }

    public boolean is(Spell other) {
        return RunicRegistry.CustomRegistries.SPELL_REGISTRY.getId(other) == RunicRegistry.CustomRegistries.SPELL_REGISTRY.getId(this);
    }

    public InteractionResult onCast(Level level, Player player, ItemStack usedStack, Spell spell) {
        return InteractionResult.PASS;
    }

    public InteractionResult onHitBlock(Level level, Player player, BlockHitResult hitResult, Spell spell) {
        return InteractionResult.SUCCESS;
    }

    public InteractionResult onHitEntity(Level level, Player player, LivingEntity target, ItemStack usedStack, Spell spell){
        return InteractionResult.SUCCESS;
    }

    @Override
    public String toString() {
        return "Spell: [" + RunicRegistry.CustomRegistries.SPELL_REGISTRY.getKey(this) + "]";
    }
}
