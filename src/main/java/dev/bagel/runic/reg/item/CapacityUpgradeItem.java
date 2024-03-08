package dev.bagel.runic.reg.item;

import dev.bagel.runic.cca.entity.RuneComponent;
import dev.bagel.runic.cca.entity.RuneProvider;
import dev.bagel.runic.reg.rune_registry.CapacityTier;
import dev.shadowsoffire.placebo.tabs.ITabFiller;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
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
        CapacityTier currentTier = RuneProvider.getCapacityTier(player);
        if (!CapacityTier.isMax(currentTier)) {
            RuneProvider.setCapacityTier(player, this.tier);
            player.sendSystemMessage(Component.translatable("spellbook.info.upgrading_tier", this.tier.toComponent(), this.tier.maxRunes));
            if (!player.isCreative()) stack.shrink(1);
            return InteractionResultHolder.success(stack);
        } else {
            player.sendSystemMessage(Component.translatable("spellbook.info.already_max"));
        }
        return InteractionResultHolder.pass(stack);
    }
}
