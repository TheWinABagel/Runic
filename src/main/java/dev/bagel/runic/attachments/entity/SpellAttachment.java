package dev.bagel.runic.attachments.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import net.minecraft.resources.ResourceLocation;

public class SpellAttachment {
    public static final Codec<SpellAttachment> CODEC = RecordCodecBuilder.create(inst -> inst.group(Codec.STRING.fieldOf("spellId")
            .forGetter(a -> Spell.getIdFromSpell(a.spell).toString())).apply(inst, SpellAttachment::new));


    private Spell spell;
    public SpellAttachment() {
        this.spell = RunicRegistry.Spells.EMPTY.get();
    }

    public SpellAttachment(String spellId) {
        Spell spell = Spell.getSpellFromId(new ResourceLocation(spellId));
        this.spell = spell == null ? RunicRegistry.Spells.EMPTY.get() : spell;
    }

    public Spell getSpell() {
        return spell;
    }


    public void setSpell(Spell spell) {
        this.spell = spell;
    }




}
