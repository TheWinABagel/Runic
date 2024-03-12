package dev.bagel.runic.attachments.entity;

import dev.bagel.runic.misc.NBTHelper;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SpellAttachment {

    private Spell spell;
    private Map<Spell, List<SpellModifier>> modifiers;
    public SpellAttachment() {
        this.spell = RunicRegistry.Spells.EMPTY.get();
        this.modifiers = new HashMap<>();
    }

    public SpellAttachment(String spellId, Map<Spell, List<SpellModifier>> modifiers) {
        Spell spell = Spell.getSpellFromId(new ResourceLocation(spellId));
        this.spell = spell == null ? RunicRegistry.Spells.EMPTY.get() : spell;
        this.modifiers = modifiers;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.modifiers.clear();
        this.spell = spell;
        List<SpellModifier> list = new ArrayList<>();
        list.add(spell.defaultModifier());
        this.modifiers.put(spell, list);
    }

    public List<SpellModifier> getModifiersForSpell(Spell spell) {
        List<SpellModifier> list = new ArrayList<>();
        if (!(this.modifiers.get(spell) == null)) {
            list = this.modifiers.get(spell);
        }
        else {
            list.add(spell.defaultModifier());
        }
        return list;
    }

    public static class Serializer implements IAttachmentSerializer<CompoundTag, SpellAttachment> {
        public static final Serializer INSTANCE = new SpellAttachment.Serializer();

        @Override
        public SpellAttachment read(IAttachmentHolder attachment, CompoundTag tag) {
            Map<Spell, List<SpellModifier>> map = NBTHelper.loadMap(tag, "modifiers",
                    Spell::getSpellFromStringId, tagg -> readModifiers(tag, tagg));

            return new SpellAttachment(tag.getString("spell"), map);
        }

        @Override
        public CompoundTag write(SpellAttachment attachment) {
            CompoundTag tag = new CompoundTag();
            NBTHelper.saveMap(tag, attachment.modifiers, "modifiers", list -> writeModifiers(list, tag));
            tag.putString("spell", attachment.getSpell().toString());
            return tag;
        }
    }

    private static List<SpellModifier> readModifiers(CompoundTag compound, Tag tag) {
        List<SpellModifier> list = new ArrayList<>();
        for (String string : compound.getAllKeys()) {
            list.add(NBTHelper.loadModifier(((CompoundTag)tag).getCompound(string)));
        }

        return list;
    }

    private static Tag writeModifiers(List<SpellModifier> modifiers, CompoundTag tag) {
        for (SpellModifier modifier : modifiers) {
            tag.put(modifier.toString(), modifier.save());
        }
        return tag;
    }

}
