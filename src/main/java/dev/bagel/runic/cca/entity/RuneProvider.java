package dev.bagel.runic.cca.entity;

import dev.bagel.runic.reg.rune_registry.CapacityTier;
import dev.bagel.runic.reg.rune_registry.RuneType;
import net.minecraft.world.entity.player.Player;

public class RuneProvider {

    /**
     * Adds
     * @param player
     * @param type
     * @param amount
     * @return The amount of runes successfully added. If less than the amount, it means that
     * */
    public static int addRunes(Player player, RuneType type, int amount) {
        return RuneComponent.RUNE_COMPONENT.get(player).addRunes(type, amount);
    }

    /**
     *
     * */
    public static int removeRunes(Player player, RuneType type, int amount) {
        return RuneComponent.RUNE_COMPONENT.get(player).removeRunes(type, amount);
    }

    /**
     *
     * */
    public static int getRuneLevel(Player player, RuneType type) {
        return RuneComponent.RUNE_COMPONENT.get(player).getRuneLevel(type);
    }

    public static void setCapacityTier(Player player, CapacityTier tier) {
         RuneComponent.RUNE_COMPONENT.get(player).setCapacityTier(tier.ordinal());
    }

    public static CapacityTier getCapacityTier(Player player) {
        return RuneComponent.RUNE_COMPONENT.get(player).getCapacityTier();
    }
}
