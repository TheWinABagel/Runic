package dev.bagel.runic.registry.registry;

import dev.bagel.runic.Runic;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import dev.shadowsoffire.placebo.registry.DeferredHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.StatFormatter;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.world.Container;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.*;

import java.util.*;
import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Supplier;
/**
 * Copied from Placebo instead of extending as extending something with @SubscribeEvent is now impossible :(
 * */
public class ExtendedDefHelper {
    protected final String modid;
    protected final Map<ResourceKey<? extends Registry<?>>, List<Registrar<?>>> objects;
    public static ExtendedDefHelper create(String modid) {
        ExtendedDefHelper helper = new ExtendedDefHelper(modid);
        Runic.BUS.register(helper);
        return helper;
    }
    public ExtendedDefHelper(String modid) {
        this.modid = modid;
        this.objects = new IdentityHashMap<>();
    }

    public <T extends Spell> DeferredHolder<Spell, T> spell(String path, Supplier<T> spell) {
        return registerDH(path, RunicRegistry.CustomRegistries.SPELL_KEY, spell);
    }

    public <T extends SpellModifier> DeferredHolder<SpellModifier, T> spellModifier(String path, Supplier<T> spell) {
        return registerDH(path, RunicRegistry.CustomRegistries.SPELL_MODIFIER_KEY, spell);
    }

    public <T extends AttachmentType<?>> DeferredHolder<AttachmentType<?>, T> attachment(String path, Supplier<T> attachment) {
        return registerDH(path, NeoForgeRegistries.Keys.ATTACHMENT_TYPES, attachment);
    }

    public <T> Registry<T> registry(ResourceKey<Registry<T>> registry, Consumer<RegistryBuilder<T>> consumer) {
        var test = DeferredRegister.create(registry, Runic.MODID);
        return test.makeRegistry(consumer);
    }

    public <T extends Block> DeferredBlock<T> block(String path, Supplier<T> factory) {
        this.register(path, Registries.BLOCK, factory);
        return DeferredBlock.createBlock(new ResourceLocation(modid, path));
    }

    public <T extends Fluid> DeferredHolder<Fluid, T> fluid(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.FLUID, factory);
    }

    public <T extends Item> DeferredItem<T> item(String path, Supplier<T> factory) {
        this.register(path, Registries.ITEM, factory);
        return DeferredItem.createItem(new ResourceLocation(modid, path));
    }

    public <T extends MobEffect> DeferredHolder<MobEffect, T> effect(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.MOB_EFFECT, factory);
    }

    public <T extends SoundEvent> DeferredHolder<SoundEvent, T> sound(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.SOUND_EVENT, factory);
    }

    public Holder<SoundEvent> sound(String path) {
        return sound(path, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(modid, path)));
    }

    public <T extends Potion> DeferredHolder<Potion, T> potion(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.POTION, factory);
    }

    public <T extends Enchantment> DeferredHolder<Enchantment, T> enchant(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.ENCHANTMENT, factory);
    }

    public <U extends Entity, T extends EntityType<U>> DeferredHolder<EntityType<?>, T> entity(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.ENTITY_TYPE, factory);
    }

    public <U extends BlockEntity, T extends BlockEntityType<U>> DeferredHolder<BlockEntityType<?>, T> blockEntity(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.BLOCK_ENTITY_TYPE, factory);
    }

    public <U extends ParticleOptions, T extends ParticleType<U>> DeferredHolder<ParticleType<?>, T> particle(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.PARTICLE_TYPE, factory);
    }

    public <U extends AbstractContainerMenu, T extends MenuType<U>> DeferredHolder<MenuType<?>, T> menu(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.MENU, factory);
    }

    public <T extends PaintingVariant> DeferredHolder<PaintingVariant, T> painting(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.PAINTING_VARIANT, factory);
    }

    public <C extends Container, U extends Recipe<C>, T extends RecipeType<U>> DeferredHolder<RecipeType<?>, T> recipe(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.RECIPE_TYPE, factory);
    }

    public <C extends Container, U extends Recipe<C>, T extends RecipeSerializer<U>> DeferredHolder<RecipeSerializer<?>, T> recipeSerializer(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.RECIPE_SERIALIZER, factory);
    }

    public <T extends Attribute> DeferredHolder<Attribute, T> attribute(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.ATTRIBUTE, factory);
    }

    public <S, U extends StatType<S>, T extends StatType<U>> DeferredHolder<StatType<?>, T> stat(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.STAT_TYPE, factory);
    }

    public Holder<ResourceLocation> customStat(String path, StatFormatter formatter) {
        return this.registerDH(path, Registries.CUSTOM_STAT, () -> {
            ResourceLocation id = new ResourceLocation(this.modid, path);
            Stats.CUSTOM.get(id, formatter);
            return id;
        });
    }

    public <U extends FeatureConfiguration, T extends Feature<U>> DeferredHolder<Feature<?>, T> feature(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.FEATURE, factory);
    }

    public <T extends CreativeModeTab> DeferredHolder<CreativeModeTab, T> tab(String path, Supplier<T> factory) {
        return this.registerDH(path, Registries.CREATIVE_MODE_TAB, factory);
    }

    public <R, T extends R> DeferredHolder<R, T> custom(String path, ResourceKey<Registry<R>> registry, Supplier<T> factory) {
        return this.registerDH(path, registry, factory);
    }

    /**
     * Stages the supplier for registration.
     */
    protected <R, T extends R> void register(String path, ResourceKey<Registry<R>> regKey, Supplier<T> factory) {
        List<Registrar<?>> registrars = this.objects.computeIfAbsent(regKey, k -> new ArrayList<>());
        ResourceLocation id = new ResourceLocation(this.modid, path);
        registrars.add(new Registrar<>(id, factory));
    }

    /**
     * Stages the supplier for registration and creates a {@link DeferredHolder} pointing to it.
     */
    protected <R, T extends R> DeferredHolder<R, T> registerDH(String path, ResourceKey<Registry<R>> regKey, Supplier<T> factory) {
        register(path, regKey, factory);
        return DeferredHolder.create(regKey, new ResourceLocation(this.modid, path));
    }

    @SubscribeEvent
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public void registerToBus(RegisterEvent e) {
        this.objects.getOrDefault(e.getRegistryKey(), Collections.emptyList()).forEach(registrar -> {
            Runic.LOGGER.info("REGISTERING {}", registrar.id);
            e.register((ResourceKey) e.getRegistryKey(), registrar.id, (Supplier) registrar.factory);
        });
    }

    protected static record Registrar<T>(ResourceLocation id, Supplier<T> factory) {

    }

}
