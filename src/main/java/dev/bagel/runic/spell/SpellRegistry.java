package dev.bagel.runic.spell;

import dev.bagel.runic.Runic;
import dev.shadowsoffire.placebo.reload.DynamicRegistry;
import org.apache.logging.log4j.Logger;

public class SpellRegistry extends DynamicRegistry<Spell> {
    public static final SpellRegistry INSTANCE = new SpellRegistry();
    public SpellRegistry() {
        super(Runic.LOGGER, "spells", true, false);
    }

    @Override
    protected void registerBuiltinCodecs() {
        this.registerDefaultCodec(Runic.loc("spells"), Spell.CODEC);
    }
}
