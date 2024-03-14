package dev.bagel.runic.misc;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.rune_registry.RuneCost;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.shadowsoffire.placebo.codec.PlaceboCodecs;
import net.minecraft.util.ExtraCodecs;

public class RunicCodecs {

    public static final Codec<Spell> SPELL_CODEC = RunicRegistry.CustomRegistries.SPELL_REGISTRY.byNameCodec();

    public static final Codec<RuneType> RUNE_TYPE_CODEC = PlaceboCodecs.enumCodec(RuneType.class);

    public static final Codec<RuneCost> RUNE_COST_CODEC = ExtraCodecs.strictOptionalField(RecordCodecBuilder.create(inst -> inst.group(
                    RunicCodecs.RUNE_TYPE_CODEC.fieldOf("type").forGetter(RuneCost::type),
                    Codec.INT.fieldOf("cost").forGetter(RuneCost::cost))
            .apply(inst, RuneCost::new)), "", new RuneCost(RuneType.BLANK, 0)).codec();

}
