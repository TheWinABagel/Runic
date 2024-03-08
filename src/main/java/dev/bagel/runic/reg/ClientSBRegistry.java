package dev.bagel.runic.reg;

import dev.bagel.runic.Runic;
import dev.bagel.runic.Spellbook;
import dev.bagel.runic.reg.client.screens.RuneScreen;
import dev.bagel.runic.reg.client.screens.SpellbookScreen;
import dev.bagel.runic.reg.menu.SpellbookMenu;
import dev.bagel.runic.reg.registry.ExtendedRegHelper;
import net.minecraft.client.gui.screens.MenuScreens;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ClientSBRegistry {
    private static final ExtendedRegHelper R = new ExtendedRegHelper(Runic.MODID);
    public static void init() {
        Screens.poke();
    }

    @OnlyIn(Dist.CLIENT)
    public static class Screens {
        static {
            MenuScreens.register(SBRegistry.Menus.RUNE_MENU, RuneScreen::new);
            MenuScreens.register(SBRegistry.Menus.SPELLBOOK_MENU, SpellbookScreen::new);

        }
        private static void poke() {
        }
    }
}
