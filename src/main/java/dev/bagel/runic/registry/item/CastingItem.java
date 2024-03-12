package dev.bagel.runic.registry.item;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.entity.SpellProjectileEntity;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastContext;
import dev.bagel.runic.spell.casting.CastType;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

import java.util.ArrayList;


public class CastingItem extends Item {
    public final RuneType effectiveType;
    InteractionResult result = InteractionResult.PASS;
    public CastingItem(RuneType type) {
        super(new Properties().stacksTo(1).durability(250));
        this.effectiveType = type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        //for each modifier check all cast types then fire all
        //have each cast modify the spell cast context, so it iterates all modifiers in order and applies them
        ItemStack stack = player.getItemInHand(usedHand);
        if (level.isClientSide()) return InteractionResultHolder.pass(stack);
        Spell spell = player.getData(RunicRegistry.Attachments.SPELL).getSpell();
//        if (!canCast(player, spell)) return new InteractionResultHolder<>(InteractionResult.PASS, stack);
        var context = new CastContext(level, player, spell, stack, null, null, new ArrayList<>(), spell.castType);
        SpellProjectileEntity entity = null;
        for (SpellModifier modifier : player.getData(RunicRegistry.Attachments.SPELL).getModifiersForSpell(spell)) {
            context.setCastType(CastType.PROJECTILE);
            if (CastType.makesProjectile(context.getCastType())) {
                entity = new SpellProjectileEntity(level);
                entity.setOwner(player);
                entity.setSpell(spell);
                entity.setStack(stack);
                entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, 0.7F, 0F);
                level.addFreshEntity(entity);
                context.setSpellProjectile(entity);
            }
        }

        if (context.getSpellProjectile() != null) {

        }
         else {

        }

        if (spell.castType.is(CastType.PROJECTILE)) {
            player.sendSystemMessage(Component.literal("projectile cast"));

            return new InteractionResultHolder<>(InteractionResult.SUCCESS, stack);
        } else {
            player.sendSystemMessage(Component.literal("normal cast"));
            return new InteractionResultHolder<>(spell.onCast(level, player, stack, spell), stack);
        }
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        BlockHitResult hitResult = new BlockHitResult(ctx.getClickLocation(), ctx.getHorizontalDirection(), ctx.getClickedPos(), ctx.isInside());
        if (ctx.getLevel().isClientSide()) return InteractionResult.PASS;
        Player player = ctx.getPlayer();
        if (player == null) return InteractionResult.PASS;
        Spell spell = player.getData(RunicRegistry.Attachments.SPELL).getSpell();
        if (!canCast(player, spell)) return InteractionResult.PASS;

        player.sendSystemMessage(Component.literal("block"));

        return spell.onHitBlock(ctx.getLevel(), ctx.getPlayer(), hitResult, ctx.getItemInHand(), spell);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand usedHand) {
        if (player.level().isClientSide()) return InteractionResult.PASS;
        Spell spell = player.getData(RunicRegistry.Attachments.SPELL).getSpell();
        if (!canCast(player, spell)) return InteractionResult.PASS;
        player.sendSystemMessage(Component.literal("spellProjectile"));
        return spell.onHitEntity(target.level(), player, target, stack, spell);
    }

    private boolean canCast(Player player, Spell spell) {
        if (!spell.canAfford(player)) {
            player.sendSystemMessage(Component.translatable("Cannot afford spell: %s, %s", spell.toString(), spell.getRuneCosts()));
            return false;
        }
        spell.getRuneCosts().forEach(cost -> player.getData(RunicRegistry.Attachments.RUNES).removeRunes(cost.type(), cost.cost()));
        return true;
    }
}
