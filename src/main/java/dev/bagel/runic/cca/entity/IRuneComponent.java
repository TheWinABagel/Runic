package dev.bagel.runic.cca.entity;

import dev.bagel.runic.reg.rune_registry.CapacityTier;
import dev.bagel.runic.reg.rune_registry.RuneType;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;

public interface IRuneComponent extends AutoSyncedComponent {

    int getRuneLevel(RuneType type);


    int addRunes(RuneType type, int value);

    int removeRunes(RuneType type, int value);

    void setCapacityTier(int tier);

    CapacityTier getCapacityTier();
}
