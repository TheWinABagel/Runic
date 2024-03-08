package dev.bagel.runic.cca.entity;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;

public interface IExperienceComponent extends AutoSyncedComponent {


    static int getXpForLevel(int nextLevel) {
        return (nextLevel - 1 + 50 * 2 * ((nextLevel - 1) / 7))/4;
    }
}
