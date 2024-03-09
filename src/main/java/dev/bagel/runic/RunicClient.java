package dev.bagel.runic;

import dev.bagel.runic.registry.client.ClientRunicRegistry;
import dev.bagel.runic.registry.keybind.RuneMenuKeybind;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Runic.MODID, bus = Bus.MOD)
public class RunicClient {

    public RunicClient(IEventBus bus) {
        ClientRunicRegistry.init();
    }

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent e) {
        NeoForge.EVENT_BUS.register(RuneMenuKeybind.class);
    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent e) {
        e.register(RuneMenuKeybind.RUNE_MENU);
    }
}
