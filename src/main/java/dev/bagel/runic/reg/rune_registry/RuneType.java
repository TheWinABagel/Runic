package dev.bagel.runic.reg.rune_registry;

import dev.bagel.runic.Spellbook;
import dev.bagel.runic.reg.SBRegistry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public enum RuneType {
    BLANK(SBRegistry.Items.BLANK_RUNE, "blank"),
    FIRE(SBRegistry.Items.FIRE_RUNE, "fire"),
    AIR(SBRegistry.Items.AIR_RUNE, "air"),
    EARTH(SBRegistry.Items.EARTH_RUNE, "earth"),
    WATER(SBRegistry.Items.WATER_RUNE, "water"),
    BODY(SBRegistry.Items.BODY_RUNE, "body"),
    MIND(SBRegistry.Items.MIND_RUNE, "mind");

    private final Item runeItem;
    private final String id;

    RuneType(Item rune, String id) {
        this.runeItem = rune;
        this.id = id;
    }

    public Item getAsItem() {
        return runeItem;
    }


    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
