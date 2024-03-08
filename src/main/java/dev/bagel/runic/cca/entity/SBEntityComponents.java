package dev.bagel.runic.cca.entity;

import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;

public class SBEntityComponents implements EntityComponentInitializer {
    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(RuneComponent.RUNE_COMPONENT, RuneComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(ExperienceComponent.EXPERIENCE_COMPONENT, ExperienceComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(SpellComponent.SPELL_COMPONENT, SpellComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }
}
