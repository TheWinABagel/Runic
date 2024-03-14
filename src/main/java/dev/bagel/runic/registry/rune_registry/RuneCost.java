package dev.bagel.runic.registry.rune_registry;
public record RuneCost(RuneType type, int cost) {


    @Override
    public boolean equals(Object obj) {
        return obj instanceof RuneCost runeCost && runeCost.type == type();
    }

    @Override
    public String toString() {
        return type() + ", cost: " + cost();
    }
}
