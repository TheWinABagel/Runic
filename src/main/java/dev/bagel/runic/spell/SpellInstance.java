package dev.bagel.runic.spell;

import dev.bagel.runic.registry.entity.SpellProjectileEntity;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

import java.util.List;

public record SpellInstance(Spell spell, List<SpellModifier> modifiers, Player player) {

    public void onHitEntity(ItemStack stack, Entity hitEntity) {
        this.spell().onHitEntity(this.player().level(), this.player(), stack, hitEntity, this.spell());
    }

    public void onSummonProjectile(ItemStack castStack, SpellProjectileEntity entity, ItemStack simulatedUsedStack) {
        this.spell().onSummonProjectile(this.player().level(), player(), castStack, entity, simulatedUsedStack, spell());
    }

    public InteractionResult onHitBlock(ItemStack usedStack, UseOnContext hitResult) {
        return this.spell().onHitBlock(this.player().level(), this.player(), usedStack, this.spell(), hitResult);
    }

    public void onCastTail(ItemStack stack) {
        this.spell().onCastHead(this.player().level(), this.player(), stack, this.spell());
    }
}
