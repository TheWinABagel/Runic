package dev.bagel.runic.cca.entity;

import dev.bagel.runic.Runic;
import dev.bagel.runic.cca.item.StaffComponent;
import dev.bagel.runic.reg.SBRegistry;
import dev.bagel.runic.reg.registry.CustomRegistries;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;

public class SpellComponent implements ISpellComponent {
    public static final ComponentKey<SpellComponent> SPELL_COMPONENT = ComponentRegistry.getOrCreate(Runic.loc("spell"), SpellComponent.class);
    private Spell spell = SBRegistry.Spells.EMPTY;
    public SpellComponent(Player player) {
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.spell = spellFromTag(tag);
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.merge(tagFromSpell(this.spell));
    }

    @Override
    public CompoundTag tagFromSpell(Spell spell) {
        CompoundTag tag = new CompoundTag();
        tag.putString("spell", spell.getId().toString());
        tag.putInt("level", spell.level);
        tag.putString("cast_type", spell.castType.id().toString());
        tag.putString("primary_rune", spell.primaryRune.getId());
        spell.getRuneCosts().forEach((type, val) -> tag.putInt(type.getId() + "_cost", val));
        return tag;
    }

    @Override
    public Spell spellFromTag(CompoundTag tag) {
        Spell spell = CustomRegistries.SPELL_REGISTRY.get(new ResourceLocation(tag.getString("spell")));
        if (spell == null) {
            return SBRegistry.Spells.EMPTY;
        }
        spell.level = tag.getInt("level");
        spell.castType = CastType.fromId(new ResourceLocation(tag.getString("cast_type")));
        return spell;
    }

    @Override
    public Spell getSpell() {
        return this.spell;
    }

    @Override
    public void setSpell(Spell spell) {
        this.spell = spell;
    }
}
