package dev.bagel.runic.reg.registry;

import dev.bagel.runic.spell.Spell;
import dev.shadowsoffire.placebo.registry.DeferredHelper;

public class ExtendedRegHelper extends DeferredHelper {
    public ExtendedRegHelper(String modid) {
        super(modid);
    }



    public <T extends Spell> T spell(String path, T spell) {
        return create(this.modid, path, CustomRegistries.SPELL_REGISTRY, spell);
    }

}
