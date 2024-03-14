package dev.bagel.runic.attachments.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.bagel.runic.misc.RunicCodecs;
import dev.bagel.runic.registry.item.RuneItem;
import dev.bagel.runic.registry.rune_registry.CapacityTier;
import dev.bagel.runic.registry.rune_registry.RuneType;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;

import java.util.Map;
import java.util.Objects;

public class RuneAttachment {
    public static final Codec<RuneAttachment> CODEC = RecordCodecBuilder.create(inst -> inst
            .group(Codec.STRING.xmap(str -> Objects.requireNonNull(CapacityTier.valueOf(str)), CapacityTier::name).fieldOf("capacityTier")
            .forGetter(RuneAttachment::getCapacityTier), Codec.unboundedMap(RunicCodecs.RUNE_TYPE_CODEC, Codec.INT).fieldOf("runeMap").forGetter(obj -> obj.runeMap))
        .apply(inst, RuneAttachment::new));
    private CapacityTier capacityTier;
    private Map<RuneType, Integer> runeMap = new Object2IntOpenHashMap<>();
    public RuneAttachment() {
        this.capacityTier = CapacityTier.TIER_0;
        for (RuneType type : RuneType.values()) {
            runeMap.putIfAbsent(type, 0);
        }
    }

    public RuneAttachment(CapacityTier capacityTier, Map<RuneType, Integer> runeMap) {
        this.capacityTier = capacityTier;
        this.runeMap = runeMap;
    }

    public CapacityTier getCapacityTier() {
        return capacityTier;
    }

    public void setCapacityTier(CapacityTier tier) {
        capacityTier = tier;
    }

    public int getRunes(RuneType type) {
        return runeMap.get(type);
    }

    public int setRunes(RuneType type, int value) {
        return runeMap.put(type, value);
    }

    public ItemStack addRunes(ItemStack stack) {
        if (!(stack.getItem() instanceof RuneItem rune)) return stack;
        int count = stack.getCount();
        int result = addRunesInternal(rune.type, count);
        if (result != count) {
            stack.setCount(Mth.abs(count - result));
            return stack;
        }
        else return ItemStack.EMPTY;

    }

    public int addRunes (RuneType type, int amount) {
        return addRunesInternal(type, amount);
    }

    public int removeRunes(RuneType type, int amount) {
        return addRunesInternal(type, -amount);
    }

//    public boolean canAfford(Spell spell) {
//        AtomicBoolean canAfford = new AtomicBoolean(true);
//        spell.getRuneCosts().forEach(cost -> {
//            if (getRunes(cost.type()) - cost.cost() < 0) {
//                canAfford.set(false);
//            }
//        });
//        return canAfford.get();
//    }

    private int addRunesInternal(RuneType type, int amount) {
        int totalAmount = runeMap.get(type) + amount;
        totalAmount = Mth.clamp(totalAmount, 0, capacityTier.maxRunes);
        return runeMap.put(type, totalAmount);

    }
}
