package dev.bagel.runic;

import dev.bagel.runic.commands.RunicCommands;
import dev.bagel.runic.multiblock.MultiblockRegistry;
import dev.bagel.runic.net.RuneMenuOpenMessage;
import dev.bagel.runic.net.SpellbookMenuOpenMessage;
import dev.bagel.runic.registry.RunicRegistry;
import dev.shadowsoffire.placebo.network.PayloadHelper;
import dev.shadowsoffire.placebo.tabs.TabFillingRegistry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Runic.MODID)
public class Runic {
    public static final String MODID = "runic";
    public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static IEventBus BUS;

    public Runic(IEventBus bus) {
        bus.register(this);
        BUS = bus;
        NeoForge.EVENT_BUS.addListener(this::registerCommands);
        RunicRegistry.init();
    }

    @SubscribeEvent
    public void init(FMLCommonSetupEvent e) {
        PayloadHelper.registerPayload(new RuneMenuOpenMessage.Provider());
        PayloadHelper.registerPayload(new SpellbookMenuOpenMessage.Provider());
        TabFillingRegistry.register(RunicRegistry.CreativeTab.SPELLBOOK_TAB_KEY, RunicRegistry.Items.AIR_RUNE);
        MultiblockRegistry.INSTANCE.registerToBus();
    }

    @SubscribeEvent
    public void customRegistries(NewRegistryEvent e) {
        e.register(RunicRegistry.CustomRegistries.SPELL_REGISTRY);
    }

    public void registerCommands(RegisterCommandsEvent e) {
        RunicCommands.register(e.getDispatcher(), e.getBuildContext());
    }

    public static ResourceLocation loc(String id) {
        return new ResourceLocation(MODID, id);
    }
}
