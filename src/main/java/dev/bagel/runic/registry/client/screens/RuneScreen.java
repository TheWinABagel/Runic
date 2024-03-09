package dev.bagel.runic.registry.client.screens;

import dev.bagel.runic.Runic;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.menu.RuneMenu;
import dev.bagel.runic.registry.rune_registry.RuneType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;

public class RuneScreen extends AbstractContainerScreen<RuneMenu> {
    public static final ResourceLocation TEXTURE = Runic.loc("textures/gui/working_gui.png");
    protected Player player;
    public RuneScreen(RuneMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.player = playerInventory.player;
        this.width = this.imageWidth = 176;
        this.height = this.imageHeight = 197;
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 1) {
            player.getData(RunicRegistry.Attachments.RUNES).addRunes(RuneType.AIR, 5);
        }
        if (button == 0) {
            player.getData(RunicRegistry.Attachments.RUNES).addRunes(RuneType.AIR, 5);
        }
        if (button == 3) {
            player.getData(RunicRegistry.Attachments.RUNES).removeRunes(RuneType.AIR, 50);
        }
        return super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    public void render(GuiGraphics gfx, int mouseX, int mouseY, float partialTick) {
        super.render(gfx, mouseX, mouseY, partialTick);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
//        super.renderLabels(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics gfx, float partialTick, int mouseX, int mouseY) {
        int xCenter = (this.width - this.imageWidth) / 2;
        int slotsX = xCenter + 60;
        int yCenter = (this.height - this.imageHeight) / 2;
        gfx.blit(TEXTURE, xCenter, yCenter, 0, 0, this.imageWidth, this.imageHeight);

    }
}
