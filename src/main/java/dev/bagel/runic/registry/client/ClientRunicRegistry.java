package dev.bagel.runic.registry.client;

import dev.bagel.runic.Runic;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.registry.client.entity.ProjectileEntityRenderer;
import dev.bagel.runic.registry.client.screen.RuneScreen;
import dev.bagel.runic.registry.client.screen.SpellbookScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Runic.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientRunicRegistry {


    @SubscribeEvent
    public static void screens(RegisterMenuScreensEvent event) {
        event.register(RunicRegistry.Menus.RUNE_MENU.get(), RuneScreen::new);
        event.register(RunicRegistry.Menus.SPELLBOOK_MENU.get(), SpellbookScreen::new);
    }

    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(RunicRegistry.Entities.SPELL_PROJECTILE.get(), ProjectileEntityRenderer::new);
    }
}
