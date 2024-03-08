package dev.bagel.runic.cca.entity;

import dev.bagel.runic.Runic;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.player.Player;

public class ExperienceComponent implements IExperienceComponent {
    public static final ComponentKey<ExperienceComponent> EXPERIENCE_COMPONENT = ComponentRegistry.getOrCreate(Runic.loc("experience"), ExperienceComponent.class);
    private int level = 0;
    private int experience = 0;
    public ExperienceComponent(Player player) {
    }

    @Override
    public void readFromNbt(CompoundTag tag) {
        this.level = tag.getInt("level");
        this.experience = tag.getInt("experience");
    }

    @Override
    public void writeToNbt(CompoundTag tag) {
        tag.putInt("level", this.level);
        tag.putInt("experience", this.experience);
    }

    public static int getXpForLevel(int nextLevel) {
        return (nextLevel - 1 + 50 * 2 * ((nextLevel - 1) / 7))/4;
    }
}
