package dev.bagel.runic.registry.item;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.entity.SpellProjectileEntity;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.SpellHelper;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;


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
        var spellHolder = player.getData(RunicRegistry.Attachments.SPELL);
        Spell spell = spellHolder.getSpell();

        SpellProjectileEntity entity = null;

        if (spell.castType.makesProjectile()) {
            entity = new SpellProjectileEntity(level, player);
            entity.setSpell(spell);
            entity.setStack(stack);
            entity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0F, 0.7F, 0F);
            level.addFreshEntity(entity);
        }

        return new InteractionResultHolder<>(spell.onCastTail(level, player, stack, spell), stack);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        if (ctx.getLevel().isClientSide()) return InteractionResult.PASS;
        Player p = ctx.getPlayer();
        if (p == null) return InteractionResult.PASS;
        var spellData = p.getData(RunicRegistry.Attachments.SPELL);
        Spell spell = spellData.getSpell();
        return SpellHelper.getSpells(p).get(spell).onHitBlock(ctx.getItemInHand(), ctx);


//        return spell.onHitBlock(p.level(), p, ctx.getItemInHand(), spell, ctx);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player player, LivingEntity target, InteractionHand usedHand) {
        if (player.level().isClientSide()) return InteractionResult.PASS;
        var spellHolder = player.getData(RunicRegistry.Attachments.SPELL);
        Spell spell = spellHolder.getSpell();
//        var event = new SpellCastEvent(player, spell, stack, target, null, spellHolder.getSelectedModifiersForSpell(spell), spell.castType);
//        NeoForge.EVENT_BUS.post(event);
        return spell.onHitEntity(player.level(), player, stack, target, spell);
    }

}
