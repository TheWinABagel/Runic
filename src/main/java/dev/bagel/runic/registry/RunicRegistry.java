package dev.bagel.runic.registry;

import dev.bagel.runic.Runic;
import dev.bagel.runic.attachments.entity.ExperienceAttachment;
import dev.bagel.runic.attachments.entity.RuneAttachment;
import dev.bagel.runic.attachments.entity.SpellAttachment;
import dev.bagel.runic.attachments.entity.SpellModifierAttachment;
import dev.bagel.runic.registry.block.RuneAltarBlock;
import dev.bagel.runic.registry.block.entity.RuneAltarBlockEntity;
import dev.bagel.runic.registry.effect.RunicEffect;
import dev.bagel.runic.registry.entity.SpellProjectileEntity;
import dev.bagel.runic.registry.item.CapacityUpgradeItem;
import dev.bagel.runic.registry.item.CastingItem;
import dev.bagel.runic.registry.item.RuneItem;
import dev.bagel.runic.registry.menu.SpellUpgradeMenu;
import dev.bagel.runic.registry.menu.SpellbookMenu;
import dev.bagel.runic.registry.rune_registry.CapacityTier;
import dev.bagel.runic.registry.rune_registry.RuneType;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import dev.bagel.runic.spell.spells.BasicBreakSpell;
import dev.bagel.runic.spell.spells.BasicDamageSpell;
import dev.bagel.runic.spell.spells.BasicEffectSpell;
import dev.bagel.runic.spell.spells.EmptySpell;
import dev.shadowsoffire.placebo.tabs.TabFillingRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.ArrayList;
import java.util.List;

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
        Entities.poke();
        Attachments.poke();
        EntityDataSerializers.poke();
    }

    public static class Items {
        public static DeferredItem<RuneItem> BLANK_RUNE = R.item("blank_rune", () -> new RuneItem(RuneType.BLANK));
        public static DeferredItem<RuneItem> FIRE_RUNE = R.item("fire_rune", () -> new RuneItem(RuneType.FIRE));
        public static DeferredItem<RuneItem> AIR_RUNE = R.item("air_rune", () -> new RuneItem(RuneType.AIR));
        public static DeferredItem<RuneItem> EARTH_RUNE = R.item("earth_rune", () -> new RuneItem(RuneType.EARTH));
        public static DeferredItem<RuneItem> WATER_RUNE = R.item("water_rune", () -> new RuneItem(RuneType.WATER));
        public static DeferredItem<RuneItem> LAW_RUNE = R.item("law_rune", () -> new RuneItem(RuneType.LAW));
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

        public static final ResourceKey<CreativeModeTab> RUNIC_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Runic.loc("creative_tab"));

        public static final DeferredHolder<CreativeModeTab, CreativeModeTab> RUNIC_TAB = R.tab("tab", () -> CreativeModeTab.builder().title(Component.translatable("runic.tab"))
                .icon(() -> new ItemStack(Items.WATER_RUNE.asItem())).build());
        static
        {
            TabFillingRegistry.register(RunicRegistry.CreativeTab.RUNIC_TAB_KEY, Items.AIR_RUNE);
        }
        private static void poke() {
        }
    }

    public static class Menus {
        public static final DeferredHolder<MenuType<?>, MenuType<SpellUpgradeMenu>> RUNE_MENU = R.menu("rune_menu", () -> new MenuType<>(SpellUpgradeMenu::new, FeatureFlagSet.of()));
        public static final DeferredHolder<MenuType<?>, MenuType<SpellbookMenu>> SPELLBOOK_MENU = R.menu("spellbook_menu", () -> new MenuType<>(SpellbookMenu::new, FeatureFlagSet.of()));
        private static void poke() {
        }
    }

    public static class Effects {
        public static final DeferredHolder<MobEffect, RunicEffect> RUNIC_EFFECT = R.effect("runic", RunicEffect::new);
        private static void poke() {
        }
    }

    public static class Entities {

        public static final DeferredHolder<EntityType<?>, EntityType<SpellProjectileEntity>> SPELL_PROJECTILE = R.entity("spell_projectile", () -> EntityType.Builder.<SpellProjectileEntity>of(SpellProjectileEntity::new, MobCategory.MISC).sized(0.5F, 0.5F).clientTrackingRange(4).updateInterval(20).build("spell_projectile"));
        private static void poke() {
        }
    }

    public static class Spells {
        public static final DeferredHolder<Spell, EmptySpell> EMPTY = R.spell("blank_spell", EmptySpell::new);
        public static final DeferredHolder<Spell, BasicBreakSpell> BREAK = R.spell("break", () -> new BasicBreakSpell(5));
        public static final DeferredHolder<Spell, BasicEffectSpell> EFFECT_SPELL = R.spell("basic_effect", () -> new BasicEffectSpell(5));
        public static final DeferredHolder<Spell, BasicDamageSpell> DAMAGE_TOUCH_SPELL = R.spell("basic_damage", () -> new BasicDamageSpell(5));

        private static void poke() {
        }
    }

    public static class CustomRegistries {
        public static final ResourceKey<Registry<Spell>> SPELL_KEY = ResourceKey.createRegistryKey(Runic.loc("spells"));
        public static final Registry<Spell> SPELL_REGISTRY = R.registry(SPELL_KEY, builder -> builder.sync(true));
        public static final ResourceKey<Registry<SpellModifier>> SPELL_MODIFIER_KEY = ResourceKey.createRegistryKey(Runic.loc("spell_modifiers"));
        public static final Registry<SpellModifier> SPELL_MODIFIER_REGISTRY = R.registry(SPELL_MODIFIER_KEY, builder -> builder.sync(true));
    }

    public static class Attachments {

        public static DeferredHolder<AttachmentType<?>, AttachmentType<ExperienceAttachment>> EXPERIENCE = R.attachment("experience",
                () -> AttachmentType.builder(ExperienceAttachment::new).serialize(ExperienceAttachment.CODEC).copyOnDeath().build());

        public static DeferredHolder<AttachmentType<?>, AttachmentType<RuneAttachment>> RUNES = R.attachment("runes",
                () -> AttachmentType.builder(RuneAttachment::new).serialize(RuneAttachment.CODEC).copyOnDeath().build());

        public static DeferredHolder<AttachmentType<?>, AttachmentType<SpellAttachment>> SPELL = R.attachment("spell",
                () -> AttachmentType.builder(() -> new SpellAttachment()).serialize(SpellAttachment.CODEC).copyOnDeath().build());
        public static DeferredHolder<AttachmentType<?>, AttachmentType<SpellModifierAttachment>> SPELL_MODIFIERS = R.attachment("spell_modifiers",
                () -> AttachmentType.builder(SpellModifierAttachment::new).serialize(SpellModifierAttachment.CODEC).copyOnDeath().build());

        private static void poke() {
        }
    }
    public static class EntityDataSerializers {
        private static final EntityDataSerializer<Spell> SPELL_SERIALIZER = new EntityDataSerializer<>() {
            @Override
            public void write(FriendlyByteBuf pBuffer, Spell pValue) {
                pBuffer.writeResourceLocation(pValue.getId());
            }
            @Override
            public Spell read(FriendlyByteBuf pBuffer) {
                return Spell.getSpellFromId(pBuffer.readResourceLocation());
            }
            @Override
            public Spell copy(Spell pValue) {
                return Spell.getSpellFromId(pValue.getId());
            }
        };
        public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<Spell>> SPELL_SERIALIZER_HOLDER = R.custom("spell_serializer", NeoForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, () -> SPELL_SERIALIZER);

        private static final EntityDataSerializer<List<SpellModifier>> SPELL_MODIFIERS_SERIALIZER = new EntityDataSerializer<List<SpellModifier>>() {
            @Override
            public void write(FriendlyByteBuf pBuffer, List<SpellModifier> pValue) {
//                pBuffer.writeInt(pValue.size());
//                for (SpellModifier modifier : pValue) {
//                    pBuffer.writeResourceLocation(SpellModifier.getIdFromModifier(modifier));
//                }

                pBuffer.writeCollection(pValue, this::write);
            }

            @Override
            public List<SpellModifier> read(FriendlyByteBuf pBuffer) {
//                int size = pBuffer.readInt();
//                List<SpellModifier> modifiers = new ArrayList<>();
//                for (int i = 0; i < size; i++) {
//                    modifiers.add(SpellModifier.getModifierFromId(pBuffer.readResourceLocation()));
//                }
                return pBuffer.readList(this::readModifier);
//                return modifiers;
            }

            @Override
            public List<SpellModifier> copy(List<SpellModifier> pValue) {
                return new ArrayList<>(List.copyOf(pValue));
            }


            private void write(FriendlyByteBuf buf, SpellModifier modifier) {
                ResourceLocation id = CustomRegistries.SPELL_MODIFIER_REGISTRY.getKey(modifier);
                if (id == null) id = Runic.loc("unknown");
                buf.writeResourceLocation(id);
            }

            private SpellModifier readModifier(FriendlyByteBuf buf) {
                ResourceLocation id = buf.readResourceLocation();
                return CustomRegistries.SPELL_MODIFIER_REGISTRY.get(id);
            }
        };
        public static final DeferredHolder<EntityDataSerializer<?>, EntityDataSerializer<List<SpellModifier>>> SPELL_MODIFIER_SERIALIZER_HOLDER = R.custom("spell_modifier_serializer", NeoForgeRegistries.Keys.ENTITY_DATA_SERIALIZERS, () -> SPELL_MODIFIERS_SERIALIZER);
        private static void poke() {
        }
    }
}
