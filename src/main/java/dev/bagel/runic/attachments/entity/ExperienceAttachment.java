package dev.bagel.runic.attachments.entity;

import dev.bagel.runic.misc.NBTHelper;
import dev.bagel.runic.spell.Spell;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

public class ExperienceAttachment {
    public static final double MAX_EXPERIENCE = 1_000_000;
    private final Object2DoubleMap<Spell> experience;
    private final Object2IntMap<Spell> levels;

    public ExperienceAttachment() {
        this.experience = new Object2DoubleOpenHashMap<>();
        this.levels = new Object2IntOpenHashMap<>();
    }

    public ExperienceAttachment(Object2IntMap<Spell> levels, Object2DoubleOpenHashMap<Spell> experience) {
        this.experience = experience;
        this.levels = levels;
    }

    private void levelUp(Spell spell) {
        this.levels.put(spell, this.levels.getInt(spell) + 1);
    }

    public boolean addExperience(Spell spell, double added) {
        boolean leveledUp = false;
        double currentTotal = this.experience.getDouble(spell);
        //Clamps to max - current to make it so you cant add more than max
        added = Mth.clamp(added, 0d, MAX_EXPERIENCE - currentTotal);
        //sets current xp to new clamped experience plus current XP
        this.experience.put(spell, added + currentTotal);
        //Sets new level if it changed
        for (double newTotal = currentTotal + added; newTotal > 0;) {
            //If new amount is more than required XP to level up
            if (newTotal >= requiredXpForNextLevel(spell)) {
                this.levelUp(spell);
                leveledUp = true;
            }
            newTotal-= added;
        }
        return leveledUp;
    }

    public double xpCalculation(double level) {
        return level - 1 + 50 * (2 * ((level - 1D) / 7));
    }

    public double requiredXpForNextLevel(Spell spell) {
        return this.xpCalculation(levels.getInt(spell) + 1);
    }

    //ugly
    public double getXP(Spell spell) {
        double result = experience.getDouble(spell);
        return result;
    }

    public int getLevel(Spell spell) {
        return levels.getInt(spell);
    }

    public Object2DoubleMap<Spell> getExperienceMap() {
        return experience;
    }

    public Object2IntMap<Spell> getLevelsMap() {
        return levels;
    }

    public static class Serializer implements IAttachmentSerializer<CompoundTag, ExperienceAttachment> {
        public static final Serializer INSTANCE = new Serializer();
        private Serializer() {
        }

        @Override
        public ExperienceAttachment read(IAttachmentHolder holder, CompoundTag tag) {
            Object2IntMap<Spell> levelMap = NBTHelper.loadIntMap(tag, "levels", Spell::getSpellFromId);
            Object2DoubleOpenHashMap<Spell> xpMap = NBTHelper.loadDoubleMap(tag, "experience", Spell::getSpellFromId);
            return new ExperienceAttachment(levelMap, xpMap);
        }

        @Override
        public CompoundTag write(ExperienceAttachment attachment) {
            CompoundTag tag = new CompoundTag();
            NBTHelper.saveIntMap(tag, attachment.getLevelsMap(), "levels");
            NBTHelper.saveDoubleMap(tag, attachment.getExperienceMap(), "experience");
            return tag;
        }
    }
}
