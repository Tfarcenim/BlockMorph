package tfar.blockmorph.network.client;

import net.minecraft.network.FriendlyByteBuf;
import tfar.blockmorph.client.ClientPacketHandler;

import java.util.UUID;

public record S2CMorphPacket(UUID uuid, boolean morph) implements S2CModPacket {

    public S2CMorphPacket(FriendlyByteBuf buf) {
        this(buf.readUUID(),buf.readBoolean());
    }

    @Override
    public void handleClient() {
        ClientPacketHandler.handle(this);
    }

    @Override
    public void write(FriendlyByteBuf to) {
        to.writeUUID(uuid);
        to.writeBoolean(morph);
    }
}
