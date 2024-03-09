package dev.bagel.runic.attachments.entity;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

public class SpellAttachment {
    private Spell spell;
    public SpellAttachment() {
        this.spell = RunicRegistry.Spells.EMPTY.get();
    }

    public SpellAttachment(String spellId) {
        Spell spell = RunicRegistry.CustomRegistries.SPELL_REGISTRY.get(new ResourceLocation(spellId));
        this.spell = spell == null ? RunicRegistry.Spells.EMPTY.get() : spell;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public static class Serializer implements IAttachmentSerializer<CompoundTag, SpellAttachment> {
        public static final Serializer INSTANCE = new SpellAttachment.Serializer();

        @Override
        public SpellAttachment read(IAttachmentHolder attachment, CompoundTag tag) {
            return new SpellAttachment(tag.getString("spell"));
        }

        @Override
        public CompoundTag write(SpellAttachment attachment) {
            CompoundTag tag = new CompoundTag();
            tag.putString("spell", attachment.getSpell().getId().toString());
            return tag;
        }
    }

}
