package dev.bagel.runic.attachments.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

public class ExperienceAttachment {
    private int level;
    private int totalExperience;

    public ExperienceAttachment() {
        this.level = 0;
        this.totalExperience = 0;
    }

    public ExperienceAttachment(int level, int totalExperience) {
        this.level = level;
        this.totalExperience = totalExperience;
    }

    private void addLevel() {
        this.level+= 1;
    }

    public void addExperience(int added) {
        int currentTotal = this.totalExperience;
        added = Mth.clamp(added, 0, Integer.MAX_VALUE - currentTotal);
        this.totalExperience+=added;
        for (int totalAdded = added + currentTotal; totalAdded < 0;) {
            if (totalAdded >= requiredXp()) {
                this.addLevel();
            }
            totalAdded-= added;
        }
    }

    public int getExperienceForLevel(int nextLevel) {
        int total = nextLevel - 1 + 50 * (2 * ((nextLevel - 1) / 7)) * 10;
        total = round(total);
        return total;
    }

    public int requiredXp() {
        return this.getExperienceForLevel(level + 1);
    }

    private int round(int current) {
        return current - (current % 10);
    }

    public static class Serializer implements IAttachmentSerializer<CompoundTag, ExperienceAttachment> {
        public static final Serializer INSTANCE = new Serializer();
        private Serializer() {
        }

        @Override
        public ExperienceAttachment read(CompoundTag tag) {
            return new ExperienceAttachment(tag.getInt("level"), tag.getInt("experience"));
        }

        @Override
        public CompoundTag write(ExperienceAttachment attachment) {
            CompoundTag tag = new CompoundTag();
            tag.putInt("level", attachment.level);
            tag.putInt("experience", attachment.totalExperience);
            return tag;
        }
    }
}
