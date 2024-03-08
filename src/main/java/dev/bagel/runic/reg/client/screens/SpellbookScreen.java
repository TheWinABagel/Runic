package dev.bagel.runic.reg.client.screens;

import dev.bagel.runic.reg.menu.SpellbookMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class SpellbookScreen extends AbstractContainerScreen<SpellbookMenu> {
    public SpellbookScreen(SpellbookMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {

    }
}
