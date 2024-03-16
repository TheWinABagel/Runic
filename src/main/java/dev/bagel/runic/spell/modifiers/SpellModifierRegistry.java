package dev.bagel.runic.spell.modifiers;

import dev.bagel.runic.Runic;
import dev.bagel.runic.spell.modifiers.spell_modifiers.BaseDig;
import dev.shadowsoffire.placebo.reload.DynamicRegistry;

public class SpellModifierRegistry extends DynamicRegistry<SpellModifier> {
    public static final SpellModifierRegistry INSTANCE = new SpellModifierRegistry();
    private SpellModifierRegistry() {
        super(Runic.LOGGER, "spell_modifier", false, true);
    }

    @Override
    protected void registerBuiltinCodecs() {
        this.registerDefaultCodec(Runic.loc("spell_modifier"), BaseDig.CODEC);
//        this.registerCodec(Runic.loc("base_dig"), BaseDig.CODEC);
    }

}
