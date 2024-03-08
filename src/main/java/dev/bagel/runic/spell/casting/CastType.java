package dev.bagel.runic.spell.casting;

import dev.bagel.runic.Runic;
import net.minecraft.resources.ResourceLocation;

public record CastType(ResourceLocation id) {
    public static final CastType NONE = new CastType(Runic.loc("none"));
    public static final CastType PROJECTILE = new CastType(Runic.loc("projectile"));
    public static final CastType TOUCH = new CastType(Runic.loc("touch"));
    public static final CastType CHANNEL = new CastType(Runic.loc("channel"));

    public static final CastType SELF = new CastType(Runic.loc("self"));

    public static CastType fromId(ResourceLocation id) {
        return new CastType(id);
    }

}
