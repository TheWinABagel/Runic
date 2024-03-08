package dev.bagel.runic.net;

import dev.bagel.runic.Runic;
import dev.bagel.runic.reg.menu.SpellbookMenu;
import net.fabricmc.fabric.api.networking.v1.FabricPacket;
import net.fabricmc.fabric.api.networking.v1.PacketType;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.world.SimpleMenuProvider;

public record SpellbookMenuOpenMessage() implements FabricPacket {
    public static final PacketType<SpellbookMenuOpenMessage> TYPE = PacketType.create(Runic.loc("open_spellbook_menu"), SpellbookMenuOpenMessage::new);
    public SpellbookMenuOpenMessage(FriendlyByteBuf buf) {
        this();
    }

    public static void init() {
        ServerPlayNetworking.registerGlobalReceiver(TYPE, ((packet, player, responseSender) -> {
            if (player == null) return;
            player.openMenu(new SimpleMenuProvider(SpellbookMenu::new, Component.literal("menuuuuuuu")));
        }));
    }

    @Override
    public void write(FriendlyByteBuf buf) {}

    @Override
    public PacketType<?> getType() {
        return TYPE;
    }

}
