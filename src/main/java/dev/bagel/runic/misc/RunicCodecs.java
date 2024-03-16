package dev.bagel.runic.misc;

import com.mojang.serialization.Codec;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.shadowsoffire.placebo.codec.PlaceboCodecs;

import java.util.Map;

public class RunicCodecs {

    public static final Codec<Spell> SPELL_CODEC = RunicRegistry.CustomRegistries.SPELL_REGISTRY.byNameCodec();

    public static final Codec<RuneType> RUNE_TYPE_CODEC = PlaceboCodecs.enumCodec(RuneType.class);

    public static final Codec<Map<RuneType, Integer>> RUNE_COST_CODEC_2 = Codec.unboundedMap(RUNE_TYPE_CODEC, Codec.INT);
}
