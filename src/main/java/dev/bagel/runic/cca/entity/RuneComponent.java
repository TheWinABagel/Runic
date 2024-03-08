package dev.bagel.runic.cca.entity;

import dev.bagel.runic.Runic;
import dev.bagel.runic.reg.rune_registry.CapacityTier;
import dev.bagel.runic.reg.rune_registry.RuneType;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class RuneComponent implements IRuneComponent {

    public static final ComponentKey<RuneComponent> RUNE_COMPONENT = ComponentRegistry.getOrCreate(Runic.loc("runes"), RuneComponent.class);
    public RuneComponent(Player player) {
        for (RuneType type : RuneType.values()) {
            runeMap.put(type, 0);
        }
    }
    private int capacityTier = 0;
    private final Object2IntMap<RuneType> runeMap = new Object2IntOpenHashMap<>();
    @Override
    public void readFromNbt(CompoundTag tag) {
        capacityTier = tag.getInt("capacity_tier");
        for (RuneType type : RuneType.values()) {
            runeMap.put(type, tag.getInt(type.getId()));
        }
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putInt("capacity_tier", capacityTier);
        runeMap.forEach((type, val) -> {
            tag.putInt(type.getId(), val);
        });
    }

    @Override
    public int getRuneLevel(RuneType type) {
        return runeMap.getInt(type);
    }

    @Override
    public int addRunes(RuneType type, int value) {
        int currentValue = runeMap.getInt(type);
        if ((currentValue + value) < 0) {
            runeMap.put(type, 0);
            return value - (currentValue + value);
        } else if ((currentValue + value) < 10000) {
            runeMap.put(type, 0);
            return value - (currentValue + value);
        } else {
            runeMap.put(type, currentValue + value);
            return value;
        }
    }

    @Override
    public int removeRunes(RuneType type, int value) {
       return this.addRunes(type, -value);
    }

    @Override
    public void setCapacityTier(int tier) {
        capacityTier = tier;
    }

    @Override
    public CapacityTier getCapacityTier() {
        return CapacityTier.values()[capacityTier];
    }
}
