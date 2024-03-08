package dev.bagel.runic.spell.spellbooks;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.shadowsoffire.placebo.codec.CodecProvider;

public record SpellbookType(String id) implements CodecProvider<SpellbookType> {

    public static Codec<SpellbookType> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Codec.STRING.fieldOf("id").forGetter(g -> g.id))
            .apply(inst, SpellbookType::new));

    @Override
    public Codec<? extends SpellbookType> getCodec() {
        return CODEC;
    }
}
