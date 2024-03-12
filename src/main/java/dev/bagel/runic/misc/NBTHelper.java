package dev.bagel.runic.misc;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import it.unimi.dsi.fastutil.objects.Object2DoubleMap;
import it.unimi.dsi.fastutil.objects.Object2DoubleOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class NBTHelper {
    public static CompoundTag saveCastType(CompoundTag tag, CastType type) {
        tag.putString("cast_type", type.id().toString());
        return tag;
    }

    public static CastType loadCastType(CompoundTag tag) {
        return CastType.fromId(new ResourceLocation(tag.getString("cast_type")));
    }

    public static CompoundTag saveSpell(CompoundTag tag, Spell spell) {
        if (!(Spell.getIdFromSpell(spell) == null)) {
            tag.putString("spell", Spell.getIdFromSpell(spell).toString());
        }
        else {
            tag.putString("spell", RunicRegistry.Spells.EMPTY.getId().toString());
        }
        return tag;
    }

    public static Spell loadSpell(CompoundTag tag) {
        ResourceLocation id = new ResourceLocation(tag.getString("spell"));
        return Spell.getSpellFromId(id);
    }

    public static <T> CompoundTag saveIntMap(CompoundTag addedTag, Object2IntMap<T> map, String name) {
        CompoundTag tag = new CompoundTag();
        map.forEach((tee, val) -> tag.putInt(tee.toString(), val));
        addedTag.put(name, tag);
        return addedTag;
    }

    public static <T> Object2IntMap<T> loadIntMap(CompoundTag tag, String name, Function<ResourceLocation, T> function) {
        Object2IntMap<T> map = new Object2IntOpenHashMap<>();
        CompoundTag listTag = tag.getCompound(name);
        for (String key : listTag.getAllKeys()) {
            map.put(function.apply(new ResourceLocation(key)), listTag.getInt(key));
        }
        return map;
    }

    public static <T> CompoundTag saveDoubleMap(CompoundTag addedTag, Object2DoubleMap<T> map, String name) {
        CompoundTag tag = new CompoundTag();
        map.forEach((tee, val) -> tag.putDouble(tee.toString(), val));
        addedTag.put(name, tag);
        return addedTag;
    }

    public static <T> Object2DoubleOpenHashMap<T> loadDoubleMap(CompoundTag tag, String name, Function<ResourceLocation, T> function) {
        Object2DoubleOpenHashMap<T> map = new Object2DoubleOpenHashMap<>();
        CompoundTag listTag = tag.getCompound(name);
        for (String key : listTag.getAllKeys()) {
            map.put(function.apply(new ResourceLocation(key)), listTag.getDouble(key));
        }
        return map;
    }

    public static <T, V> CompoundTag saveMap(CompoundTag addedTag, Map<T, V> map, String name, Function<V, Tag> tagFunction) {
        CompoundTag tag = new CompoundTag();
        map.forEach((tee, val) -> tag.put(tee.toString(), tagFunction.apply(val)));
        addedTag.put(name, tag);
        return addedTag;
    }

    public static <T, V> Map<T, V> loadMap(CompoundTag tag, String name, Function<String, T> stringFunction, Function<CompoundTag, V> tagFunction) {
        Map<T, V> map = new HashMap<>();
        CompoundTag compoundTag = tag.getCompound(name);
        for (String key : compoundTag.getAllKeys()) {
            map.put(stringFunction.apply(key), tagFunction.apply(compoundTag));
        }
        return map;
    }

    public static SpellModifier loadModifier(CompoundTag tag) {
        Spell spell = NBTHelper.loadSpell(tag);
        CastType castType = NBTHelper.loadCastType(tag);
        ResourceLocation loc = NBTHelper.loadLocation(tag, "spell_modifier");
        SpellModifier.getModifierFromId(loc);
        SpellModifier modifier = SpellModifier.getModifierFromId(loc);
        modifier.setCastType(castType);
        return modifier;

    }

    public static ResourceLocation loadLocation(CompoundTag tag, String tagId) {
        return new ResourceLocation(tag.getString(tagId));
    }
}
