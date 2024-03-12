package dev.bagel.runic.spell.modifiers;

import dev.bagel.runic.misc.NBTHelper;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastContext;
import dev.bagel.runic.spell.casting.CastType;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class SpellModifier {
    private Spell spell;
    private Object2IntMap<RuneType> runeCosts;
    private CastType castType;
    public SpellModifier() {

    }

    public void apply(CastContext context){

    }

    public void setCastType(CastType type) {
        this.castType = type;
    }

    public void setRuneCost(RuneType type, int cost) {
        runeCosts.put(type, cost);
    }

    public CompoundTag save() {
        CompoundTag tag = new CompoundTag();
        NBTHelper.saveSpell(tag, spell);
        NBTHelper.saveCastType(tag, castType);
        return tag;
    }

    public Spell getSpell() {
        return spell;
    }

    @Override
    public String toString() {
        ResourceLocation id = RunicRegistry.CustomRegistries.SPELL_MODIFIER_REGISTRY.getKey(this);
        if (id == null) return "runic:unknown";
        return id.toString();
    }

    @Nullable
    public static SpellModifier getModifierFromStringId(String id) {
        return RunicRegistry.CustomRegistries.SPELL_MODIFIER_REGISTRY.get(new ResourceLocation(id));
    }

    @Nullable
    public static SpellModifier getModifierFromId(ResourceLocation id) {
        return RunicRegistry.CustomRegistries.SPELL_MODIFIER_REGISTRY.get(id);
    }

    public static ResourceLocation getIdFromModifier(SpellModifier modifier) {
        return RunicRegistry.CustomRegistries.SPELL_MODIFIER_REGISTRY.getKey(modifier);
    }

    //    public static SpellModifier load(CompoundTag tag) {
//
//    }

//    public static <T extends SpellModifier> ModifierBuilder<T> getBuilder(Spell modifiedSpell, T modifier) {
//        return new ModifierBuilder<T>(modifiedSpell, modifier);
//    }
//    public static class ModifierBuilder<T extends SpellModifier> {
//        private T modifier;
//        private Object2IntMap<RuneType> runeCosts;
//        private CastType castType;
//        private final Spell baseSpell;
//        private ModifierBuilder(Spell spell, T modifier) {
//            this.baseSpell = spell;
//            this.modifier = modifier;
//            this.castType = CastType.NONE;
//            this.runeCosts = new Object2IntOpenHashMap<>();
//        }
//
//        public ModifierBuilder setCastType(CastType type) {
//            this.castType = type;
//            return this;
//        }
//
//        public ModifierBuilder setRuneCost(RuneType type, int cost) {
//            runeCosts.put(type, cost);
//            return this;
//        }
//
//
//        public ModifierBuilder set() {
//            return this;
//        }
//
//        public SpellModifier build() {
//            return new SpellModifier(this);
//        }
//    }
}
