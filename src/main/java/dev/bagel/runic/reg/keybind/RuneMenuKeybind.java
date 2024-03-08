package dev.bagel.runic.reg.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import dev.bagel.runic.Runic;
import dev.bagel.runic.Spellbook;
import dev.bagel.runic.net.RuneMenuOpenMessage;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.event.TickEvent;
import org.lwjgl.glfw.GLFW;

public class RuneMenuKeybind {
    public static final KeyMapping TOGGLE_RADIAL = new KeyMapping("key." + Runic.MODID + ".toggle_radial_mining", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O,
            "key.categories." + Runic.MODID);


    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent e) {
        e.register(TOGGLE_RADIAL);
    }

    @SubscribeEvent
    public void clientTick(TickEvent.ClientTickEvent e) {
        if (Minecraft.getInstance().player == null) return;

        while (TOGGLE_RADIAL.consumeClick()) {
            if (Minecraft.getInstance().screen == null) {
                ClientPlayNetworking.send(new RuneMenuOpenMessage());
            }
        }
    }
}
