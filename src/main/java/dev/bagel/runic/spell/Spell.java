package dev.bagel.runic.spell;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.bagel.runic.Runic;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.rune_registry.RuneCost;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.casting.CastType;
import dev.shadowsoffire.placebo.codec.CodecProvider;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Spell {
    public ResourceLocation id;
    public CastType castType;
    private final HashSet<RuneCost> runeCosts = new HashSet<>();
    public final int castXp;

    public Spell(int baseCastXp, CastType castType) {
        this.castXp = baseCastXp;
        this.castType = castType;
    }

    public int getCostForRune(RuneType type) {
        int cost = 0;
        while (runeCosts.iterator().hasNext()) {
            RuneCost rCost = runeCosts.iterator().next();
            if (rCost.type() == type) {
                cost = rCost.cost();
                break;
            }
        }
        return cost;
    }

    public Set<RuneCost> getRuneCosts() {
        return runeCosts;
    }

    public ResourceLocation getId() {
        return getIdFromSpell(this);
    }

    public void setCostForRune(RuneType type, int value) {
        runeCosts.removeIf(runeCost -> runeCost.type() == type);
        runeCosts.add(new RuneCost(type, value));
    }

    public boolean canAfford(Player player) {
        boolean canAfford = true;
        for (RuneCost cost : this.runeCosts) {
            if (player.getData(RunicRegistry.Attachments.RUNES).getRunes(cost.type()) - cost.cost() < 0) {
                canAfford = false;
            }
        }
        return canAfford;
    }


    public InteractionResult onCast(Level level, Player player, ItemStack usedStack, Spell spell) {
        return InteractionResult.PASS;
    }

    public InteractionResult onHitBlock(Level level, Player player, BlockHitResult hitResult, ItemStack usedStack, Spell spell) {
        return InteractionResult.PASS;
    }

    public InteractionResult onHitEntity(Level level, Player player, Entity target, ItemStack usedStack, Spell spell) {
        return InteractionResult.PASS;
    }


    public CompoundTag save(CompoundTag tag) {
        if (!(this.id == null)) {
            tag.putString("spell", id.toString());
        }
        else {
            tag.putString("spell", RunicRegistry.Spells.EMPTY.getId().toString());
        }
        return tag;
    }

    public static Spell load(CompoundTag tag) {
        ResourceLocation id = new ResourceLocation(tag.getString("spell"));
        return Spell.getSpellFromId(id);
    }

    @Override
    public String toString() {
        return "Spell: [" + getIdFromSpell(this) + "]";
    }

    public static Spell getSpellFromId(ResourceLocation id) {
        return RunicRegistry.CustomRegistries.SPELL_REGISTRY.get(id);
    }

    public static ResourceLocation getIdFromSpell(Spell spell) {
        return RunicRegistry.CustomRegistries.SPELL_REGISTRY.getKey(spell);
    }
}
