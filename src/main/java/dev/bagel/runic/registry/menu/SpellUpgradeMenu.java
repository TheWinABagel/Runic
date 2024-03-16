package dev.bagel.runic.registry.menu;

import dev.bagel.runic.registry.RunicRegistry;
import dev.shadowsoffire.placebo.menu.PlaceboContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class SpellUpgradeMenu extends PlaceboContainerMenu {

    protected Player player;

    public SpellUpgradeMenu(int id, Inventory playerInv, Player player) {
        this(id, playerInv);
        this.player = player;
    }

    public SpellUpgradeMenu(int id, Inventory playerInv) {
        super(RunicRegistry.Menus.RUNE_MENU.get(), id, playerInv);
        this.player = playerInv.player;
    }

    @Override
    public boolean stillValid(Player player) {
        return !player.isDeadOrDying();
    }

    @Override
    public boolean clickMenuButton(Player pPlayer, int pId) {
        return super.clickMenuButton(pPlayer, pId);
    }
}
