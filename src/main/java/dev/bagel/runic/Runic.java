package dev.bagel.runic;

import dev.bagel.runic.commands.RunicCommands;
import dev.bagel.runic.datagen.BlockModelDatagen;
import dev.bagel.runic.datagen.ItemModelDatagen;
import dev.bagel.runic.net.RuneMenuOpenMessage;
import dev.bagel.runic.net.SpellbookMenuOpenMessage;
import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.modifiers.SpellModifierRegistry;
import dev.shadowsoffire.placebo.network.PayloadHelper;
import dev.shadowsoffire.placebo.tabs.TabFillingRegistry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.NewRegistryEvent;
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
        NeoForge.EVENT_BUS.register(new RunicEvents());
        RunicRegistry.init();
    }

    @SubscribeEvent
    public void init(FMLCommonSetupEvent e) {
        PayloadHelper.registerPayload(new RuneMenuOpenMessage.Provider());
        PayloadHelper.registerPayload(new SpellbookMenuOpenMessage.Provider());
        TabFillingRegistry.register(RunicRegistry.CreativeTab.RUNIC_TAB_KEY, RunicRegistry.Items.AIR_RUNE);
        SpellModifierRegistry.INSTANCE.registerToBus();
    }

    @SubscribeEvent
    public void customRegistries(NewRegistryEvent e) {
        e.register(RunicRegistry.CustomRegistries.SPELL_REGISTRY);
        e.register(RunicRegistry.CustomRegistries.SPELL_MODIFIER_REGISTRY);
    }

    @SubscribeEvent
    public void datagen(GatherDataEvent e) {
        DataGenerator gen = e.getGenerator();
        gen.addProvider(e.includeClient(), (DataProvider.Factory<ItemModelDatagen>) pOutput -> new ItemModelDatagen(pOutput, e.getExistingFileHelper()));
        gen.addProvider(e.includeClient(), (DataProvider.Factory<BlockModelDatagen>) pOutput -> new BlockModelDatagen(pOutput, e.getExistingFileHelper()));
    }

    public void registerCommands(RegisterCommandsEvent e) {
        RunicCommands.register(e.getDispatcher(), e.getBuildContext());
    }

    public static ResourceLocation loc(String id) {
        return new ResourceLocation(MODID, id);
    }
}
