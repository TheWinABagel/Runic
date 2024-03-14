package dev.bagel.runic.spell.casting;

import dev.bagel.runic.Runic;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CastType {
    private final boolean targetsOthers;
    private final boolean makesProjectile;
    private final boolean canTargetSelf;
    private final ResourceLocation id;
    private static final Map<ResourceLocation, CastType> castTypeMap = new HashMap<>();
    private CastType(CastTypeBuilder builder) {
        this.id = builder.id;
        this.targetsOthers = builder.targetsOthers;
        this.makesProjectile = builder.makesProjectile;
        this.canTargetSelf = builder.canTargetSelf;
    }

    public boolean targetsOthers() {
        return targetsOthers;
    }

    public boolean makesProjectile() {
        return makesProjectile;
    }

    public boolean canTargetSelf() {
        return canTargetSelf;
    }

    public static final CastType NONE = register(CastType.getBuilder().id(Runic.loc("none")).build());
    public static final CastType PROJECTILE = register(CastType.getBuilder().id(Runic.loc("projectile")).build());
    public static final CastType TOUCH = register(CastType.getBuilder().id(Runic.loc("touch")).build());
    public static final CastType CHANNEL = register(CastType.getBuilder().id(Runic.loc("channel")).build());
    public static final CastType LAY_ON_HANDS = register(CastType.getBuilder().id(Runic.loc("lay_on_hands")).build());
    public static final CastType SELF = register(CastType.getBuilder().id(Runic.loc("self")).build());

    public static CastType register(CastType type) {
        return castTypeMap.put(type.id, type);
    }

    public static CastType get(ResourceLocation id) {
        return castTypeMap.get(id);
    }


    public static boolean makesProjectile(CastType type) {
        return type.equals(PROJECTILE);
    }

    public static CastTypeBuilder getBuilder() {
        return new CastTypeBuilder();
    }

    public static class CastTypeBuilder {
        private boolean targetsOthers;
        private boolean makesProjectile;
        private boolean canTargetSelf;
        private ResourceLocation id;
        private CastTypeBuilder() {}

        public CastTypeBuilder canTargetSelf() {
            this.canTargetSelf = true;
            return this;
        }

        public CastTypeBuilder makesProjectile() {
            this.makesProjectile = true;
            this.targetsOthers = true;
            return this;
        }

        public CastTypeBuilder targetsOthers() {
            this.makesProjectile = true;
            return this;
        }

        public CastTypeBuilder id(ResourceLocation id) {
            this.id = id;
            return this;
        }

        public CastType build() {
            if (this.id == null) {
                throw new RuntimeException("Cast type has no id!");
            }
            return new CastType(this);
        }
    }
}
