package dev.bagel.runic.registry.rune_registry;

public enum RuneType {
    BLANK("blank"),
    FIRE("fire"),
    AIR("air"),
    EARTH("earth"),
    WATER("water"),
    BODY("body"),
    MIND("mind"),
    LAW("law");

    private final String id;

    RuneType(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id;
    }
}
