package dev.bagel.runic.reg;

import dev.bagel.runic.Runic;
import dev.bagel.runic.reg.effect.RunicEffect;
import dev.bagel.runic.reg.item.CastingItem;
import dev.bagel.runic.reg.menu.SpellbookMenu;
import dev.bagel.runic.Spellbook;
import dev.bagel.runic.reg.item.CapacityUpgradeItem;
import dev.bagel.runic.reg.item.RuneItem;
import dev.bagel.runic.reg.menu.RuneMenu;
import dev.bagel.runic.reg.registry.ExtendedRegHelper;
import dev.bagel.runic.reg.rune_registry.CapacityTier;
import dev.bagel.runic.reg.rune_registry.RuneType;
import dev.bagel.runic.spell.casting.CastType;
import dev.bagel.runic.spell.spells.BasicBreakSpell;
import dev.bagel.runic.spell.spells.BasicEffectSpell;
import dev.bagel.runic.spell.spells.EmptySpell;
import dev.shadowsoffire.placebo.tabs.TabFillingRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;

public class SBRegistry {
    private static final ExtendedRegHelper R = new ExtendedRegHelper(Runic.MODID);
    public static void init() {
        Items.poke();
        CreativeTab.poke();
        Menus.poke();
        Spells.poke();
        Effects.poke();
    }

    public static class Items {
        public static RuneItem BLANK_RUNE = R.item("blank_rune", new RuneItem());
        public static RuneItem FIRE_RUNE = R.item("fire_rune", new RuneItem());
        public static RuneItem AIR_RUNE = R.item("air_rune", new RuneItem());
        public static RuneItem EARTH_RUNE = R.item("earth_rune", new RuneItem());
        public static RuneItem WATER_RUNE = R.item("water_rune", new RuneItem());
        public static RuneItem BODY_RUNE = R.item("body_rune", new RuneItem());
        public static RuneItem MIND_RUNE = R.item("mind_rune", new RuneItem());
        public static CapacityUpgradeItem TIER_1_POUCH = R.item("tier_1_pouch", new CapacityUpgradeItem(CapacityTier.TIER_1));
        public static CapacityUpgradeItem TIER_2_POUCH = R.item("tier_2_pouch", new CapacityUpgradeItem(CapacityTier.TIER_2));
        public static CapacityUpgradeItem TIER_3_POUCH = R.item("tier_3_pouch", new CapacityUpgradeItem(CapacityTier.TIER_3));
        public static CastingItem FIRE_STAFF = R.item("fire_staff", new CastingItem(RuneType.FIRE));

        private static void poke() {
        }
    }
    public static class CreativeTab {

        public static final ResourceKey<CreativeModeTab> SPELLBOOK_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB, Runic.loc("creative_tab"));
        static {
            Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SPELLBOOK_TAB_KEY, FabricItemGroup.builder().icon(() -> new ItemStack(Items.WATER_RUNE))
                    .title(Component.translatable("spellbook.tab")).build());
            TabFillingRegistry.register(SPELLBOOK_TAB_KEY, Items.FIRE_RUNE);
        }
        private static void poke() {
        }
    }

    public static class Menus {
        public static final MenuType<RuneMenu> RUNE_MENU = R.menu("rune_menu", new MenuType<>(RuneMenu::new, FeatureFlagSet.of()));
        public static final MenuType<SpellbookMenu> SPELLBOOK_MENU = R.menu("spellbook_menu", new ExtendedScreenHandlerType<>(SpellbookMenu::new));
        private static void poke() {
        }
    }

    public static class Effects {
        public static final RunicEffect RUNIC_EFFECT = new RunicEffect();
        private static void poke() {
        }
    }

    public static class Spells {
        public static final EmptySpell EMPTY = R.spell("blank_spell", new EmptySpell());
        public static final BasicBreakSpell TOUCH_BREAK = R.spell("basic_break", new BasicBreakSpell(0, 5, CastType.TOUCH));
        public static final BasicEffectSpell EFFECT_SPELL = R.spell("basic_effect", new BasicEffectSpell(0, RuneType.BODY, 5, CastType.SELF));

        private static void poke() {
        }
    }


}
