package dev.bagel.runic.spell.spell_modifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.bagel.runic.misc.RunicCodecs;
import dev.bagel.runic.spell.Spell;
import dev.shadowsoffire.placebo.codec.CodecProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class SpellModifierType implements CodecProvider<SpellModifierType> {
    public static final Codec<SpellModifierType> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            RunicCodecs.SPELL_CODEC.fieldOf("spell").forGetter(a -> a.spell),
            ResourceLocation.CODEC.fieldOf("effects_of").forGetter(a -> a.effects_of),
            Codec.INT.fieldOf("maxLevel").forGetter(a -> a.maxLevel),
                    ResourceLocation.CODEC.listOf().fieldOf("requirements").forGetter(a -> a.requirements),
            Codec.INT.fieldOf("requiredLevels").forGetter(a -> a.requiredLevels),
            Codec.INT.fieldOf("x").forGetter(a -> a.x),
            Codec.INT.fieldOf("y").forGetter(a -> a.y))
            .apply(inst, SpellModifierType::new));

    protected Spell spell;
    protected ResourceLocation effects_of;
    protected int maxLevel;
    protected List<ResourceLocation> requirements;
    protected int requiredLevels;
    protected int x, y;

    public SpellModifierType(Spell spell, ResourceLocation effects_of, int maxLevel, List<ResourceLocation> requirements, int requiredLevels, int x, int y) {
        this.spell = spell;
        this.effects_of = effects_of;
        this.maxLevel = maxLevel;
        this.requirements = requirements;
        this.requiredLevels = requiredLevels;
        this.x = x;
        this.y = y;
    }

    public Spell getSpell() {
        return spell;
    }

    public ResourceLocation getEffects_of() {
        return effects_of;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public List<ResourceLocation> getRequirements() {
        return requirements;
    }

    public int getRequiredLevels() {
        return requiredLevels;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public Codec<? extends SpellModifierType> getCodec() {
        return CODEC;
    }
}
