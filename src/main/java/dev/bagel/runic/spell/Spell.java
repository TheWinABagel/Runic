package dev.bagel.runic.spell;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.rune_registry.RuneCost;
import dev.bagel.runic.spell.casting.CastType;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.util.HashSet;
import java.util.Set;

public abstract class Spell {
    public CastType castType = CastType.TOUCH;
    private final HashSet<RuneCost> runeCosts = new HashSet<>();
    public final int castXp;

    public Spell(int baseCastXp) {
        this.castXp = baseCastXp;
    }

    public Set<RuneCost> getRuneCosts() {
        return runeCosts;
    }

    public ResourceLocation getId() {
        return getIdFromSpell(this);
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

    public abstract SpellModifier defaultModifier();

    public InteractionResult onCast(Level level, Player player, ItemStack usedStack, Spell spell) {
        return InteractionResult.PASS;
    }

    public InteractionResult onHitBlock(Level level, Player player, BlockHitResult hitResult, ItemStack usedStack, Spell spell) {
        return InteractionResult.PASS;
    }

    public InteractionResult onHitEntity(Level level, Player player, Entity target, ItemStack usedStack, Spell spell) {
        return InteractionResult.PASS;
    }

    @Override
    public String toString() {
        return getIdFromSpell(this).toString();
    }

    @Nullable
    public static Spell getSpellFromStringId(String id) {
        return RunicRegistry.CustomRegistries.SPELL_REGISTRY.get(new ResourceLocation(id));
    }

    @Nullable
    public static Spell getSpellFromId(ResourceLocation id) {
        return RunicRegistry.CustomRegistries.SPELL_REGISTRY.get(id);
    }

    public static ResourceLocation getIdFromSpell(Spell spell) {
        return RunicRegistry.CustomRegistries.SPELL_REGISTRY.getKey(spell);
    }
}
