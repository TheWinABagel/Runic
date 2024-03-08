package dev.bagel.runic.reg.menu;

import dev.bagel.runic.reg.SBRegistry;
import dev.shadowsoffire.placebo.menu.PlaceboContainerMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuType;

public class SpellbookMenu extends PlaceboContainerMenu {
    private final Player player;

    public SpellbookMenu(int syncId, Inventory inventory, FriendlyByteBuf buf) {
        this(syncId, inventory, inventory.player);

    }

    public SpellbookMenu(int id, Inventory pInv, Player player) {
        super(SBRegistry.Menus.SPELLBOOK_MENU, id, pInv);
        this.player = player;
    }

    @Override
    public boolean stillValid(Player player) {
        return !player.isDeadOrDying();
    }


}
