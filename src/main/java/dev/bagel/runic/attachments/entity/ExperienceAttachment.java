package dev.bagel.runic.attachments.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.bagel.runic.misc.RunicCodecs;
import dev.bagel.runic.spell.Spell;
import net.minecraft.util.Mth;

import java.util.HashMap;
import java.util.Map;

public class ExperienceAttachment {
    public static final Codec<ExperienceAttachment> CODEC = RecordCodecBuilder.create(inst ->
            inst.group(Codec.unboundedMap(RunicCodecs.SPELL_CODEC, Codec.INT).fieldOf("levels").forGetter(obj -> obj.levels)
                    , Codec.unboundedMap(RunicCodecs.SPELL_CODEC, Codec.DOUBLE).fieldOf("experience").forGetter(obj -> obj.experience))
                    .apply(inst, ExperienceAttachment::new));
    public static final double MAX_EXPERIENCE = 1_000_000;
    private final Map<Spell, Double> experience;
    private final Map<Spell, Integer> levels;

    public ExperienceAttachment() {
        this.experience = new HashMap<>();
        this.levels = new HashMap<>();
    }

    public ExperienceAttachment(Map<Spell, Integer> levels, Map<Spell, Double> experience) {
        this.experience = new HashMap<>(experience);
        this.levels = new HashMap<>(levels);
    }

    private void levelUp(Spell spell) {
        this.levels.put(spell, this.levels.get(spell) + 1);
    }

    public boolean addExperience(Spell spell, double added) {
        boolean leveledUp = false;
        double currentTotal = this.experience.get(spell);
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
        return this.xpCalculation(levels.get(spell) + 1);
    }

    //ugly
    public double getXP(Spell spell) {
        double result = experience.get(spell);
        return result;
    }

    public int getLevel(Spell spell) {
        return levels.get(spell);
    }

    public Map<Spell, Double> getExperienceMap() {
        return experience;
    }

    public Map<Spell, Integer> getLevelsMap() {
        return levels;
    }
}
