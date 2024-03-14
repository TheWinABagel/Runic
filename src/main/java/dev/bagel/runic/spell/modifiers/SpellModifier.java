package dev.bagel.runic.spell.modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.bagel.runic.misc.RunicCodecs;
import dev.bagel.runic.registry.rune_registry.RuneCost;
import dev.bagel.runic.spell.Spell;
import dev.shadowsoffire.placebo.codec.CodecProvider;
import dev.shadowsoffire.placebo.codec.PlaceboCodecs;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public abstract class SpellModifier implements CodecProvider<SpellModifier> {

    protected ModifierData data;
    protected final SpellModifierType type;
    public SpellModifier(SpellModifierType type) {
        this.type = type;
    }

    public final ResourceLocation getId() {
        return SpellModifierRegistry.INSTANCE.getKey(this);
    }

    public ModifierData getModifierData() {
        return data;
    }

    protected static final Codec<ModifierData> BASE_CODEC = RecordCodecBuilder.create(inst -> inst.group(
            RunicCodecs.SPELL_CODEC.fieldOf("spell").forGetter(a -> a.spell),
            Codec.INT.fieldOf("maxLevel").forGetter(a -> a.maxLevel),
            ExtraCodecs.strictOptionalField(PlaceboCodecs.setOf(ResourceLocation.CODEC), "requirements", Collections.emptySet()).forGetter(a -> a.requirements),
            Codec.INT.fieldOf("requiredLevels").forGetter(a -> a.requiredLevels),
            ExtraCodecs.strictOptionalField(RunicCodecs.RUNE_COST_CODEC.listOf(), "runeCosts", Collections.emptyList()).forGetter(e -> e.runeCosts),
            Codec.INT.fieldOf("x").forGetter(a -> a.x),
            Codec.INT.fieldOf("y").forGetter(a -> a.y)).apply(inst, ModifierData::new));
    public record ModifierData(Spell spell, int maxLevel, Set<ResourceLocation> requirements, int requiredLevels, List<RuneCost> runeCosts, int x, int y) {
    }
}
