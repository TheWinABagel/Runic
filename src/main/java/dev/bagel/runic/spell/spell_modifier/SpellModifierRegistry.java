package dev.bagel.runic.spell.spell_modifier;

import dev.bagel.runic.Runic;
import dev.shadowsoffire.placebo.reload.DynamicRegistry;

public class SpellModifierRegistry extends DynamicRegistry<SpellModifierType> {
    public static final SpellModifierRegistry INSTANCE = new SpellModifierRegistry();
    private SpellModifierRegistry() {
        super(Runic.LOGGER, "spell_modifier", false, false);
    }

    @Override
    protected void registerBuiltinCodecs() {
        this.registerDefaultCodec(Runic.loc("spell_modifiers"), SpellModifierType.CODEC);
    }
}
