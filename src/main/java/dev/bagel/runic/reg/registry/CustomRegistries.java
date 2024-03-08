package dev.bagel.runic.reg.registry;

import dev.bagel.runic.Runic;
import dev.bagel.runic.spell.Spell;
import net.fabricmc.fabric.api.event.registry.FabricRegistryBuilder;
import net.fabricmc.fabric.api.event.registry.RegistryAttribute;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class CustomRegistries {
    public static final ResourceKey<Registry<Spell>> KEY = ResourceKey.createRegistryKey(Runic.loc("spells"));
    public static final HashMap<ResourceLocation, Spell> SPELLS = new HashMap<>();
    public static final Registry<Spell> SPELL_REGISTRY = FabricRegistryBuilder.createSimple(KEY).attribute(RegistryAttribute.SYNCED).buildAndRegister();

}
