package dev.bagel.runic.attachments.entity;

import dev.bagel.runic.registry.item.RuneItem;
import dev.bagel.runic.registry.rune_registry.CapacityTier;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

import java.util.concurrent.atomic.AtomicBoolean;

public class RuneAttachment {
    private CapacityTier capacityTier;
    private final Object2IntMap<RuneType> runeMap = new Object2IntOpenHashMap<>();
    public RuneAttachment() {
        this.capacityTier = CapacityTier.TIER_0;
        for (RuneType type : RuneType.values()) {
            runeMap.put(type, 0);
        }
    }

    public RuneAttachment(CapacityTier capacityTier) {
        this();
        this.capacityTier = capacityTier;
    }

    public CapacityTier getCapacityTier() {
        return capacityTier;
    }

    public void setCapacityTier(CapacityTier tier) {
        capacityTier = tier;
    }

    public int getRunes(RuneType type) {
        return runeMap.getInt(type);
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

    public boolean canAfford(Spell spell) {
        AtomicBoolean canAfford = new AtomicBoolean(true);
        spell.getRuneCosts().forEach((type, integer) -> {
            if (getRunes(type) - integer < 0) {
                canAfford.set(false);
            }
        });
        return canAfford.get();
    }

    private int addRunesInternal(RuneType type, int amount) {
        int totalAmount = runeMap.getInt(type) + amount;
        totalAmount = Mth.clamp(totalAmount, 0, capacityTier.maxRunes);
        return runeMap.put(type, totalAmount);

    }

    public static class Serializer implements IAttachmentSerializer<CompoundTag, RuneAttachment> {
        public static Serializer INSTANCE = new Serializer();
        private Serializer() {
        }

        @Override
        public RuneAttachment read(IAttachmentHolder holder, CompoundTag tag) {
            RuneAttachment result = new RuneAttachment(CapacityTier.values()[tag.getInt("tier")]);
            for (RuneType type : RuneType.values()) {
               result.runeMap.put(type, tag.getInt("rune_" + type.toString()));
            }
            return result;
        }

        @Override
        public CompoundTag write(RuneAttachment attachment) {
            CompoundTag tag = new CompoundTag();
            attachment.runeMap.forEach((type, value) -> tag.putInt("rune_" + type.toString(), value));
            tag.putInt("tier", attachment.capacityTier.ordinal());
            return tag;
        }
    }
}
