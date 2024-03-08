package dev.bagel.runic.reg.item;

import dev.bagel.runic.cca.entity.SpellProvider;
import dev.bagel.runic.reg.rune_registry.RuneType;
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


public class CastingItem extends Item {
    public final RuneType effectiveType;
    public CastingItem(RuneType type) {
        super(new Properties().stacksTo(1).durability(250));
        this.effectiveType = type;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (level.isClientSide()) return InteractionResultHolder.pass(stack);
        return new InteractionResultHolder<>(SpellProvider.getSelectedSpell(player).onSpellCast(level, player, stack), stack);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        BlockHitResult hitResult = new BlockHitResult(ctx.getClickLocation(), ctx.getHorizontalDirection(), ctx.getClickedPos(), ctx.isInside());
        if (ctx.getLevel().isClientSide()) return InteractionResult.PASS;
        return SpellProvider.getSelectedSpell(ctx.getPlayer()).onHitBlock(ctx.getLevel(), ctx.getPlayer(), hitResult);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand usedHand) {
        if (player.level().isClientSide()) return InteractionResult.PASS;
        return SpellProvider.getSelectedSpell(player).onHitEntity(target.level(), player, target, stack);
    }
}
