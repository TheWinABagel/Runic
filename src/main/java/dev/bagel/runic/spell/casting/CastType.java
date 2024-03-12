package dev.bagel.runic.spell.casting;

import dev.bagel.runic.Runic;
import net.minecraft.resources.ResourceLocation;

public record CastType(ResourceLocation id) {
    public static final CastType NONE = new CastType(Runic.loc("none"));
    public static final CastType PROJECTILE = new CastType(Runic.loc("projectile"));
    public static final CastType TOUCH = new CastType(Runic.loc("touch"));
    public static final CastType CHANNEL = new CastType(Runic.loc("channel"));
    public static final CastType LAY_ON_HANDS = new CastType(Runic.loc("lay_on_hands"));
    public static final CastType SELF = new CastType(Runic.loc("self"));

    public static CastType fromId(ResourceLocation id) {
        return new CastType(id);
    }

    public static boolean canTargetSelf(CastType type) {
        return type.equals(SELF) || type.equals(LAY_ON_HANDS);
    }

    public static boolean makesProjectile(CastType type) {
        return type.equals(PROJECTILE);
    }

    public boolean is(CastType type) {
        return type.id == this.id;
    }
}
