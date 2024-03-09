package dev.bagel.runic.registry.rune_registry;

import net.minecraft.network.chat.Component;

public enum CapacityTier {
    TIER_0(500),
    TIER_1(1000),
    TIER_2(2500),
    TIER_3(10000);

    public final int maxRunes;

    CapacityTier(int maxRunes) {
        this.maxRunes = maxRunes;
    }

    public static boolean isMax(CapacityTier tier) {
        return tier.ordinal() == values().length;
    }

    public Component toComponent() {
        return Component.translatable("spellbook.runes.capacity_tier", this.ordinal());
    }
}
