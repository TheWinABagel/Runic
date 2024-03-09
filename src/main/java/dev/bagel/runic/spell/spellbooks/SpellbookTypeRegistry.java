package dev.bagel.runic.spell.spellbooks;

import dev.bagel.runic.Runic;
import dev.bagel.runic.Spellbook;
import dev.shadowsoffire.placebo.reload.DynamicRegistry;

public class SpellbookTypeRegistry extends DynamicRegistry<SpellbookType> {
    public static final SpellbookTypeRegistry INSTANCE = new SpellbookTypeRegistry();
    public SpellbookTypeRegistry() {
        super(Runic.LOGGER, "spellbooks", true, true);
    }

    @Override
    protected void registerBuiltinCodecs() {
        this.registerDefaultCodec(Runic.loc("spellbooks"), SpellbookType.CODEC);
//        this.registerCodec(Spellbook.loc("spellbooks"), );
    }
}
