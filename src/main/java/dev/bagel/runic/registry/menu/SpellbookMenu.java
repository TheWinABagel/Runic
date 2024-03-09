package dev.bagel.runic.registry.menu;

import dev.bagel.runic.registry.RunicRegistry;
import dev.shadowsoffire.placebo.menu.PlaceboContainerMenu;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class SpellbookMenu extends PlaceboContainerMenu {
    private final Player player;

    public SpellbookMenu(int id, Inventory pInv) {
        this(id, pInv, pInv.player);
    }

    public SpellbookMenu(int id, Inventory pInv, Player player) {
        super(RunicRegistry.Menus.SPELLBOOK_MENU.get(), id, pInv);
        this.player = player;
    }

    @Override
    public boolean stillValid(Player player) {
        return !player.isDeadOrDying();
    }


}
