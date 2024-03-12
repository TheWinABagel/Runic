package dev.bagel.runic.misc;

import com.mojang.serialization.Codec;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import net.minecraft.resources.ResourceLocation;

public class RunicCodecs {

    public static final Codec<Spell> SPELL_CODEC = Codec.unit(RunicRegistry.Spells.EMPTY::get);

    public static final Codec<SpellModifier> SPELL_MODIFIER_CODEC = Codec.unit(RunicRegistry.SpellModifiers.BLANK_MODIFIER::get);

    //More basic version of the one from ExtraCodecs
    public static final Codec<TagOrElementLocation> RESOURCE_LOCATION = Codec.STRING
            .comapFlatMap(
                    string -> ResourceLocation.read(string).map(TagOrElementLocation::new),
                    RunicCodecs.TagOrElementLocation::decoratedId
            );

    public static record TagOrElementLocation(ResourceLocation id) {
        @Override
        public String toString() {
            return this.decoratedId();
        }

        private String decoratedId() {
            return this.id.toString();
        }
    }
}
