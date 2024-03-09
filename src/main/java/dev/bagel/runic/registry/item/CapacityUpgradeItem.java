package dev.bagel.runic.registry.item;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.rune_registry.CapacityTier;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class CapacityUpgradeItem extends Item {

    private final CapacityTier tier;

    public CapacityUpgradeItem(CapacityTier tier) {
        super(new Properties().stacksTo(1));
        this.tier = tier;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (level.isClientSide()) return InteractionResultHolder.pass(stack);
        CapacityTier currentTier = player.getData(RunicRegistry.Attachments.RUNES).getCapacityTier();
        if (!CapacityTier.isMax(currentTier)) {
            player.getData(RunicRegistry.Attachments.RUNES).setCapacityTier(this.tier);
            player.sendSystemMessage(Component.translatable("spellbook.info.upgrading_tier", this.tier.toComponent(), this.tier.maxRunes));
            if (!player.isCreative()) stack.shrink(1);
            return InteractionResultHolder.success(stack);
        } else {
            player.sendSystemMessage(Component.translatable("spellbook.info.already_max"));
        }
        return InteractionResultHolder.pass(stack);
    }
}
