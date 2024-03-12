package dev.bagel.runic.registry.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import dev.bagel.runic.Runic;
import dev.bagel.runic.net.RuneMenuOpenMessage;
import dev.shadowsoffire.placebo.packets.PatreonDisableMessage;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.event.TickEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import org.lwjgl.glfw.GLFW;

public class RuneMenuKeybind {
    public static final KeyMapping RUNE_MENU = new KeyMapping("key." + Runic.MODID + ".open_rune_menu", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O,
            "key.categories." + Runic.MODID);

    @SubscribeEvent
    public static void clientTick(TickEvent.ClientTickEvent e) {
        if (Minecraft.getInstance().player == null) return;

        while (RUNE_MENU.consumeClick()) {
            if (Minecraft.getInstance().screen == null) {
                PacketDistributor.SERVER.noArg().send(new RuneMenuOpenMessage());
            }
        }
    }

    @SubscribeEvent
    public static void keyInput(InputEvent.Key e) {
        if (e.getAction() == InputConstants.PRESS && RUNE_MENU.matches(e.getKey(), e.getScanCode()) && Minecraft.getInstance().getConnection() != null) {
            PacketDistributor.SERVER.noArg().send(new RuneMenuOpenMessage());
        }
    }
}
