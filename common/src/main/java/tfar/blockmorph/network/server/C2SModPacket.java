package tfar.blockmorph.network.server;

import net.minecraft.server.level.ServerPlayer;
import tfar.blockmorph.network.ModPacket;

public interface C2SModPacket extends ModPacket {

    void handleServer(ServerPlayer player);

}
