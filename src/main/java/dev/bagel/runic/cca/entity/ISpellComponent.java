package dev.bagel.runic.cca.entity;

import dev.bagel.runic.spell.Spell;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.CompoundTag;

public interface ISpellComponent extends AutoSyncedComponent {

    CompoundTag tagFromSpell(Spell spell);

    Spell spellFromTag(CompoundTag tag);

    Spell getSpell();

    void setSpell(Spell spell);
}
