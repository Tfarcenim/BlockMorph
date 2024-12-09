package tfar.blockmorph.network.server;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import tfar.blockmorph.PlayerDuck;

public record C2SKeyPacket() implements C2SModPacket{

    public C2SKeyPacket(FriendlyByteBuf buf) {
        this();
    }

    @Override
    public void handleServer(ServerPlayer player) {
        PlayerDuck.of(player).toggleMorph();
    }

    @Override
    public void write(FriendlyByteBuf to) {

    }
}
