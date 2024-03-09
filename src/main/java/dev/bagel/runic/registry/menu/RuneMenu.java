package dev.bagel.runic.registry.menu;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.shadowsoffire.placebo.menu.PlaceboContainerMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;

public class RuneMenu extends PlaceboContainerMenu {

    protected Player player;
    protected final ContainerLevelAccess access;
    protected SimpleContainer itemInventory = new SimpleContainer(RuneType.values().length);
    public RuneMenu(int id, Inventory inv, FriendlyByteBuf buf) {
        this(id, inv);
    }

    public RuneMenu(int id, Inventory playerInv, Player player) {
        this(id, playerInv, ContainerLevelAccess.NULL);
        this.player = player;
    }

    public RuneMenu(int id, Inventory playerInv) {
        this(id, playerInv, ContainerLevelAccess.NULL);
    }

    protected RuneMenu(int id, Inventory playerInv, ContainerLevelAccess access) {
        super(RunicRegistry.Menus.RUNE_MENU.get(), id, playerInv);
        this.player = playerInv.player;
        this.access = access;
        this.addSlot(new Slot(this.itemInventory, 0, 25, 24));
        this.addPlayerSlots(playerInv, 8, 115);
    }

    @Override
    public boolean stillValid(Player player) {
        return !player.isDeadOrDying();
    }
}