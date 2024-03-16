package dev.bagel.runic.spell.modifiers.spell_modifiers;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import dev.bagel.runic.spell.modifiers.SpellModifierType;

public class BaseDig extends SpellModifier {
    public static final Codec<BaseDig> CODEC = RecordCodecBuilder.create(inst -> inst.group(
            BASE_CODEC.fieldOf("data").forGetter(a -> a.data))
            .apply(inst, BaseDig::new));

    public BaseDig(ModifierData data) {
        super(SpellModifierType.BASE_SPELL);
        this.data = data;
    }

    @Override
    public Codec<? extends SpellModifier> getCodec() {
        return CODEC;
    }
}
