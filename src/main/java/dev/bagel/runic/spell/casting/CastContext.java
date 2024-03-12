package dev.bagel.runic.spell.casting;

import dev.bagel.runic.registry.entity.SpellProjectileEntity;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

//certified no setters in record moment
public class CastContext {
    private Level level;
    private Player caster;
    private Spell spell;
    private ItemStack usedStack;
    @Nullable
    private Entity hitEntity;

    @Nullable
    private SpellProjectileEntity spellProjectile;
    private ArrayList<SpellModifier> modifiers;
    private CastType type;
    public CastContext(Level level, Player caster, Spell spell, ItemStack usedStack, @Nullable Entity hitEntity,
                       @Nullable SpellProjectileEntity spellProjectile, ArrayList<SpellModifier> modifiers, CastType type) {
        this.level = level;
        this.caster = caster;
        this.spell = spell;
        this.usedStack = usedStack;
        this.hitEntity = hitEntity;
        this.spellProjectile = spellProjectile;
        this.modifiers = modifiers;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Player getCaster() {
        return caster;
    }

    public void setCaster(Player caster) {
        this.caster = caster;
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

    public ArrayList<SpellModifier> getModifiers() {
        return modifiers;
    }

    public void setModifiers(ArrayList<SpellModifier> modifiers) {
        this.modifiers = modifiers;
    }

    public CastType getCastType() {
        return type;
    }

    public void setCastType(CastType type) {
        this.type = type;
    }
}
