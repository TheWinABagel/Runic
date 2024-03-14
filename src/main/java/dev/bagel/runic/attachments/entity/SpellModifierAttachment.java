package dev.bagel.runic.attachments.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.bagel.runic.misc.RunicCodecs;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import dev.bagel.runic.spell.modifiers.SpellModifierRegistry;
import dev.shadowsoffire.placebo.reload.DynamicHolder;
import net.minecraft.resources.ResourceLocation;

import java.util.List;

public class SpellModifierAttachment {
    public static final Codec<SpellModifierAttachment> CODEC = RecordCodecBuilder.create(inst -> inst.group(
                    RunicCodecs.SPELL_CODEC.fieldOf("spell").forGetter(a -> a.spell),
                    SpellModifierRegistry.INSTANCE.holderCodec().fieldOf("modifier").forGetter(a -> a.modifier),
                    Codec.INT.fieldOf("maxLevel").forGetter(a -> a.maxLevel),
                    ResourceLocation.CODEC.listOf().fieldOf("requirements").forGetter(a -> a.requirements),
                    Codec.INT.fieldOf("requiredLevels").forGetter(a -> a.requiredLevels))
            .apply(inst, SpellModifierAttachment::new));

    protected Spell spell;
    protected DynamicHolder<SpellModifier> modifier;
    protected int maxLevel;
    protected List<ResourceLocation> requirements;
    protected int requiredLevels;

    public SpellModifierAttachment(Spell spell, DynamicHolder<SpellModifier> modifier, int maxLevel, List<ResourceLocation> requirements, int requiredLevels) {
        this.spell = spell;
        this.modifier = modifier;
        this.maxLevel = maxLevel;
        this.requirements = requirements;
        this.requiredLevels = requiredLevels;
    }

    public SpellModifierAttachment() {
    }
}
