package dev.bagel.runic.net;

import dev.bagel.runic.Runic;
import dev.bagel.runic.registry.menu.RuneMenu;
import dev.shadowsoffire.placebo.network.PayloadProvider;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.SimpleMenuProvider;
import net.neoforged.neoforge.network.handling.PlayPayloadContext;

import java.util.List;
import java.util.Optional;

public class SpellbookMenuOpenMessage implements CustomPacketPayload {
    public static final ResourceLocation ID = Runic.loc("open_spellbook_menu");


    @Override
    public void write(FriendlyByteBuf buf) {}

    @Override
    public ResourceLocation id() {
        return ID;
    }

    public static class Provider implements PayloadProvider<RuneMenuOpenMessage, PlayPayloadContext> {

        @Override
        public ResourceLocation id() {
            return ID;
        }

        @Override
        public RuneMenuOpenMessage read(FriendlyByteBuf buf) {
            return new RuneMenuOpenMessage();
        }

        @Override
        public void handle(RuneMenuOpenMessage msg, PlayPayloadContext ctx) {
            ctx.player().ifPresent(player -> {
                player.openMenu(new SimpleMenuProvider(RuneMenu::new, Component.literal("menuuuuuuuuu")));
            });
        }

        @Override
        public List<ConnectionProtocol> getSupportedProtocols() {
            return List.of(ConnectionProtocol.PLAY);
        }

        @Override
        public Optional<PacketFlow> getFlow() {
            return Optional.of(PacketFlow.SERVERBOUND);
        }
    }
}