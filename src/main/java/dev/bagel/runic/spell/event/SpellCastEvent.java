package dev.bagel.runic.spell.event;

import dev.bagel.runic.registry.entity.SpellProjectileEntity;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellCastEvent extends PlayerEvent {
    private final Player player;
    private ItemStack usedStack;
    private @Nullable Entity hitEntity;
    private @Nullable SpellProjectileEntity spellProjectile;
    private Spell spell;
    private List<SpellModifier> modifiers;
    private CastType type;
    private Map<RuneType, Integer> costs;
    private float xpModifier = 1f;
    public SpellCastEvent(Player player, Spell spell, ItemStack usedStack, @Nullable Entity hitEntity, @Nullable SpellProjectileEntity spellProjectile,
                          List<SpellModifier> modifiers, CastType type) {
        super(player);
        this.player = player;
        this.spell = spell;
        this.usedStack = usedStack;
        this.hitEntity = hitEntity;
        this.spellProjectile = spellProjectile;
        this.modifiers = modifiers;
        this.costs = new HashMap<>();
        this.type = type;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public ItemStack getUsedStack() {
        return usedStack;
    }

    public void setUsedStack(ItemStack usedStack) {
        this.usedStack = usedStack;
    }

    public Entity getHitEntity() {
        return hitEntity;
    }

    public void setHitEntity(Entity hitEntity) {
        this.hitEntity = hitEntity;
    }

    public SpellProjectileEntity getSpellProjectile() {
        return spellProjectile;
    }

    public void setSpellProjectile(SpellProjectileEntity spellProjectile) {
        this.spellProjectile = spellProjectile;
    }

    public List<SpellModifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<SpellModifier> modifiers) {
        this.modifiers = modifiers;
    }

    public CastType getCastType() {
        return type;
    }

    public void setCastType(CastType type) {
        this.type = type;
    }


    public float getXpModifier() {
        return xpModifier;
    }

    public void setXpModifier(float xpModifier) {
        this.xpModifier = xpModifier;
    }

    public enum InteractionType {
        BLOCK,
        ENTITY,
        CAST,
        PROJECTILE;
    }
}
