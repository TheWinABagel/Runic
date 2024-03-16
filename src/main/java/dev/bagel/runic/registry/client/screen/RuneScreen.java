package dev.bagel.runic.registry.client.screen;

import dev.bagel.runic.Runic;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.menu.SpellUpgradeMenu;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import dev.bagel.runic.spell.modifiers.SpellModifierRegistry;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RuneScreen extends AbstractContainerScreen<SpellUpgradeMenu> {
    public static final ResourceLocation TEXTURE = Runic.loc("textures/gui/working_gui.png");
    protected Player player;
    public RuneScreen(SpellUpgradeMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
        this.player = playerInventory.player;
        this.width = this.imageWidth = 256;
        this.height = this.imageHeight = 180;
    }

    @Override
    public void render(GuiGraphics gfx, int x, int y, float partial) {
//        super.render(gfx, x, y, partial);
        this.renderBackground(gfx, x, y, partial);
        for (SpellModifier mod : SpellModifierRegistry.INSTANCE.getValues()) {
            SpellModifier.ModifierData data = mod.getModifierData();
            int slotX = data.x() + getGuiLeft();
            int slotY = data.y() + getGuiTop();
            gfx.renderItem(new ItemStack(RunicRegistry.Items.AIR_RUNE.get()), slotX, slotY);
        }
        super.render(gfx, x, y, partial);
        this.renderTooltip(gfx, x, y);
    }

    @Override
    protected void renderLabels(GuiGraphics gfx, int pMouseX, int pMouseY) {
//        pGuiGraphics.drawString(this.font, this.title, this.titleLabelX, this.titleLabelY, 4210752, false);
//        pGuiGraphics.drawString(this.font, this.playerInventoryTitle, this.inventoryLabelX, this.inventoryLabelY, 4210752, false);
    }

    @Override
    protected void renderTooltip(GuiGraphics gfx, int mouseX, int mouseY) {
        super.renderTooltip(gfx, mouseX, mouseY);
        SpellModifier modifier = this.getHoveredSpell(mouseX, mouseY);
        if (modifier != null) {
            List<Component> list = new ArrayList<>();
            MutableComponent name = Component.translatable(SpellModifierRegistry.INSTANCE.getKey(modifier).toLanguageKey()).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFFFFAA)));
            list.add(name);
            list.add(Component.literal(""));
            list.add(Component.literal("woo"));
            gfx.renderComponentTooltip(this.font, list, mouseX, mouseY);
        }

    }

    @Nullable
    public SpellModifier getHoveredSpell(int mouseX, int mouseY) {
        for (SpellModifier mod : SpellModifierRegistry.INSTANCE.getValues()) {
            if (this.isHovering(mod.getModifierData().x(), mod.getModifierData().y(), 17, 17, mouseX, mouseY)) return mod;
        }
        return null;
    }

    @Override
    protected void renderBg(GuiGraphics gfx, float partial, int x, int y) {
        int xCenter = (this.width - this.imageWidth) / 2;
        int yCenter = (this.height - this.imageHeight) / 2;
        gfx.blit(TEXTURE, xCenter, yCenter, 0, 0, this.imageWidth, this.imageHeight);
    }

}
