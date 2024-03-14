package dev.bagel.runic;

import dev.bagel.runic.registry.client.keybind.RuneMenuKeybind;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.common.Mod.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = Runic.MODID, bus = Bus.MOD)
public class RunicClient {

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent e) {

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
