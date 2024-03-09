package dev.bagel.runic.registry;

import dev.bagel.runic.Runic;
import dev.bagel.runic.attachments.entity.ExperienceAttachment;
import dev.bagel.runic.attachments.entity.RuneAttachment;
import dev.bagel.runic.attachments.entity.SpellAttachment;
import dev.bagel.runic.registry.block.RuneAltarBlock;
import dev.bagel.runic.registry.block.entity.RuneAltarBlockEntity;
import dev.bagel.runic.registry.effect.RunicEffect;
import dev.bagel.runic.registry.item.CastingItem;
import dev.bagel.runic.registry.menu.SpellbookMenu;
import dev.bagel.runic.registry.item.CapacityUpgradeItem;
import dev.bagel.runic.registry.item.RuneItem;
import dev.bagel.runic.registry.menu.RuneMenu;
import dev.bagel.runic.registry.registry.ExtendedDefHelper;
import dev.bagel.runic.registry.rune_registry.CapacityTier;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.casting.CastType;
import dev.bagel.runic.spell.spells.BasicBreakSpell;
import dev.bagel.runic.spell.spells.BasicEffectSpell;
import dev.bagel.runic.spell.spells.EmptySpell;
import dev.shadowsoffire.placebo.tabs.TabFillingRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class RunicRegistry {
    private static final ExtendedDefHelper R = ExtendedDefHelper.create(Runic.MODID);
    public static void init() {
        Items.poke();
        Blocks.poke();
        BlockEntities.poke();
        CreativeTab.poke();
        Menus.poke();
        Spells.poke();
        Effects.poke();
        Attachments.poke();
    }

    public static class Items {
        public static DeferredItem<RuneItem> BLANK_RUNE = R.item("blank_rune", () -> new RuneItem(RuneType.BLANK));
        public static DeferredItem<RuneItem> FIRE_RUNE = R.item("fire_rune", () -> new RuneItem(RuneType.FIRE));
        public static DeferredItem<RuneItem> AIR_RUNE = R.item("air_rune", () -> new RuneItem(RuneType.AIR));
        public static DeferredItem<RuneItem> EARTH_RUNE = R.item("earth_rune", () -> new RuneItem(RuneType.EARTH));
        public static DeferredItem<RuneItem> WATER_RUNE = R.item("water_rune", () -> new RuneItem(RuneType.WATER));
        public static DeferredItem<RuneItem> BODY_RUNE = R.item("body_rune", () -> new RuneItem(RuneType.BODY));
        public static DeferredItem<RuneItem> MIND_RUNE = R.item("mind_rune", () -> new RuneItem(RuneType.MIND));
        public static DeferredItem<RuneItem> LAW_RUNE = R.item("law_rune", () -> new RuneItem(RuneType.MIND));
        public static DeferredItem<CapacityUpgradeItem> TIER_1_POUCH = R.item("tier_1_pouch", () -> new CapacityUpgradeItem(CapacityTier.TIER_1));
        public static DeferredItem<CapacityUpgradeItem> TIER_2_POUCH = R.item("tier_2_pouch", () -> new CapacityUpgradeItem(CapacityTier.TIER_2));
        public static DeferredItem<CapacityUpgradeItem> TIER_3_POUCH = R.item("tier_3_pouch", () -> new CapacityUpgradeItem(CapacityTier.TIER_3));
        public static DeferredItem<CastingItem> FIRE_STAFF = R.item("fire_staff", () -> new CastingItem(RuneType.FIRE));

        private static void poke() {
        }
    }
    public static class Blocks {
        public static DeferredBlock<RuneAltarBlock> RUNE_ALTAR = R.block("rune_altar", () -> new RuneAltarBlock());
        private static void poke() {
        }
    }
    public static class BlockEntities {
        public static DeferredHolder<BlockEntityType<?>, BlockEntityType<RuneAltarBlockEntity>> RUNE_ALTAR_BE = R.blockEntity("rune_altar", () -> BlockEntityType.Builder.of(RuneAltarBlockEntity::new, Blocks.RUNE_ALTAR.get()).build(null));
        private static void poke() {
        }
    }
    public static class CreativeTab {

        public static final ResourceKey<CreativeModeTab> SPELLBOOK_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Runic.loc("creative_tab"));

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> SPELLBOOK_TAB = R.tab("tab", () -> CreativeModeTab.builder().title(Component.translatable("spellbook.tab"))
                .icon(() -> new ItemStack(Items.WATER_RUNE.asItem())).build());
        static
        {
            TabFillingRegistry.register(RunicRegistry.CreativeTab.SPELLBOOK_TAB_KEY, Items.AIR_RUNE, Items.BODY_RUNE);
        }
        private static void poke() {
        }
    }

    public static class Menus {
        public static final DeferredHolder<MenuType<?>, MenuType<RuneMenu>> RUNE_MENU = R.menu("rune_menu", () -> new MenuType<>(RuneMenu::new, FeatureFlagSet.of()));
        public static final DeferredHolder<MenuType<?>, MenuType<SpellbookMenu>> SPELLBOOK_MENU = R.menu("spellbook_menu", () -> new MenuType<>(SpellbookMenu::new, FeatureFlagSet.of()));
        private static void poke() {
        }
    }

    public static class Effects {
        public static final DeferredHolder<MobEffect, RunicEffect> RUNIC_EFFECT = R.effect("runic", RunicEffect::new);
        private static void poke() {
        }
    }

    public static class Spells {
        public static final DeferredHolder<Spell, EmptySpell> EMPTY = R.spell("blank_spell", EmptySpell::new);
        public static final DeferredHolder<Spell, BasicBreakSpell> TOUCH_BREAK = R.spell("basic_break", () -> new BasicBreakSpell(0, 5, CastType.TOUCH));
        public static final DeferredHolder<Spell, BasicEffectSpell> EFFECT_SPELL = R.spell("basic_effect", () -> new BasicEffectSpell(0, RuneType.BODY, 5, CastType.SELF));

        private static void poke() {
        }
    }

    public static class CustomRegistries {
        public static final ResourceKey<Registry<Spell>> SPELL_KEY = ResourceKey.createRegistryKey(Runic.loc("spells"));
        public static final Registry<Spell> SPELL_REGISTRY = R.registry(SPELL_KEY, builder -> builder.sync(true));
    }

    public static class Attachments {

        public static DeferredHolder<AttachmentType<?>, AttachmentType<ExperienceAttachment>> EXPERIENCE = R.attachment("experience",
                () -> AttachmentType.builder(ExperienceAttachment::new).serialize(ExperienceAttachment.Serializer.INSTANCE).copyOnDeath().build());

        public static DeferredHolder<AttachmentType<?>, AttachmentType<RuneAttachment>> RUNES = R.attachment("runes",
                () -> AttachmentType.builder(() -> new RuneAttachment()).serialize(RuneAttachment.Serializer.INSTANCE).copyOnDeath().build());

        public static DeferredHolder<AttachmentType<?>, AttachmentType<SpellAttachment>> SPELL = R.attachment("spell",
                () -> AttachmentType.builder(() -> new SpellAttachment()).serialize(SpellAttachment.Serializer.INSTANCE).copyOnDeath().build());

        private static void poke() {

        }
    }
}
