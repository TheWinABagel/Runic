package dev.bagel.runic.registry.client;

import dev.bagel.runic.Runic;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.client.screens.RuneScreen;
import dev.bagel.runic.registry.client.screens.SpellbookScreen;
import dev.bagel.runic.registry.registry.ExtendedDefHelper;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientRunicRegistry {
    private static final ExtendedDefHelper R = ExtendedDefHelper.create(Runic.MODID);
    public static void init() {
        Screens.poke();
    }

    @OnlyIn(Dist.CLIENT)
    public static class Screens {
        static {
            MenuScreens.register(RunicRegistry.Menus.RUNE_MENU.get(), RuneScreen::new);
            MenuScreens.register(RunicRegistry.Menus.SPELLBOOK_MENU.get(), SpellbookScreen::new);

        }
        private static void poke() {
        }
    }
}
