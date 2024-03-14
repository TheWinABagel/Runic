package dev.bagel.runic.misc;

import com.mojang.serialization.Codec;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;

public class RunicCodecs {

    public static final Codec<Spell> SPELL_CODEC = RunicRegistry.CustomRegistries.SPELL_REGISTRY.byNameCodec();

    public static final Codec<SpellModifier> SPELL_MODIFIER_CODEC = RunicRegistry.CustomRegistries.SPELL_MODIFIER_REGISTRY.byNameCodec();

}
