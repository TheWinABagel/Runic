package dev.bagel.runic.spell.modifiers;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Tuple;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SpellModifier {
    protected final boolean isGeneric;
    @Nullable
    protected final Spell spell;
    protected List<Tuple<RuneType, Integer>> runeCosts;
    protected CastType castType;
    protected float xpModifier = 1f;
    protected int maxLevel;
    protected List<ResourceLocation> requirements;
    protected int requiredLevels;

    public SpellModifier(ModifierBuilder builder) {
        this(builder.spell);
        this.runeCosts = builder.bonuscost;
        this.castType = builder.castType;
        this.xpModifier = builder.xpModifier;
        this.maxLevel = builder.maxLevel;
        this.requirements = builder.requirements;
        this.requiredLevels = builder.requiredLevels;
    }

    public SpellModifier(@Nullable Spell targetedSpell) {
        this.spell = targetedSpell;
        this.isGeneric = targetedSpell == null;
    }



    public void setCastType(CastType type) {
        this.castType = type;
    }

    public CastType getCastType() {
        return castType;
    }

    public void addRuneCost(RuneType type, int cost) {
        runeCosts.add(new Tuple<>(type, cost));
    }

    public List<Tuple<RuneType, Integer>> getRuneCosts() {
        return runeCosts;
    }

    @Nullable
    public Spell getSpell() {
        return spell;
    }

    public boolean isGeneric() {
        return isGeneric;
    }

    public float getXpModifier() {
        return xpModifier;
    }

    public void setXpModifier(float xpModifier) {
        this.xpModifier *= xpModifier;
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

    public static ModifierBuilder getBuilder(@Nullable Spell spell) {
        return new ModifierBuilder(spell);
    }
    public static class ModifierBuilder {
        protected final boolean isGeneric;
        @Nullable
        protected final Spell spell;
        protected List<Tuple<RuneType, Integer>> bonuscost;
        protected CastType castType;
        protected float xpModifier = 1f;
        protected int maxLevel;
        protected List<ResourceLocation> requirements;
        protected int requiredLevels;
        private ModifierBuilder(Spell spell) {
            this.spell = spell;
            this.isGeneric = spell == null;
        }

        public ModifierBuilder setBonuscost(List<Tuple<RuneType, Integer>> bonuscost) {
            this.bonuscost = bonuscost;
            return this;
        }

        public ModifierBuilder setCastType(CastType castType) {
            this.castType = castType;
            return this;
        }

        public ModifierBuilder setXpModifier(float xpModifier) {
            this.xpModifier = xpModifier;
            return this;
        }

        public ModifierBuilder setMaxLevel(int maxLevel) {
            this.maxLevel = maxLevel;
            return this;
        }

        public ModifierBuilder setRequirements(List<ResourceLocation> requirements) {
            this.requirements = requirements;
            return this;
        }

        public ModifierBuilder setRequiredLevels(int requiredLevels) {
            this.requiredLevels = requiredLevels;
            return this;
        }

        public SpellModifier build() {
            return new SpellModifier(this);
        }
    }
}
