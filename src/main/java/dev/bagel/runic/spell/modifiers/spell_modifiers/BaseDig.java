package dev.bagel.runic.spell.modifiers.spell_modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import dev.bagel.runic.spell.modifiers.SpellModifierType;

public class BaseDig extends SpellModifier {
    private int test;
    public static final Codec<BaseDig> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            BASE_CODEC.fieldOf("data").forGetter(a -> a.data),
                    Codec.INT.fieldOf("test").forGetter(a -> a.test))
            .apply(inst, BaseDig::new));

    public BaseDig(ModifierData data, int test) {
        super(SpellModifierType.BASE_SPELL);
        this.test = test;
    }

    @Override
    public Codec<? extends SpellModifier> getCodec() {
        return CODEC;
    }
}
