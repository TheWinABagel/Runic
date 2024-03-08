package dev.bagel.runic.net;

import dev.bagel.runic.Runic;
import dev.bagel.runic.reg.menu.RuneMenu;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleMenuProvider;

public record RuneMenuOpenMessage() implements FabricPacket {
    public static final PacketType<RuneMenuOpenMessage> TYPE = PacketType.create(Runic.loc("open_rune_menu"), RuneMenuOpenMessage::new);
    public RuneMenuOpenMessage(FriendlyByteBuf buf) {
        this();
    }

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(TYPE, ((packet, player, responseSender) -> {
            if (player == null) return;
            player.openMenu(new SimpleMenuProvider(RuneMenu::new, Component.literal("menuuu")));
        }));
    }

    @Override
    public void write(FriendlyByteBuf buf) {}

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

}
