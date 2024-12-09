package tfar.blockmorph.network;

import net.minecraft.resources.ResourceLocation;
import tfar.blockmorph.BlockMorph;
import tfar.blockmorph.network.client.S2CMorphPacket;
import tfar.blockmorph.network.server.C2SKeyPacket;
import tfar.blockmorph.platform.Services;

import java.util.Locale;

public class PacketHandler {

    public static void registerPackets() {
        Services.PLATFORM.registerClientPacket(S2CMorphPacket.class, S2CMorphPacket::new);
        Services.PLATFORM.registerServerPacket(C2SKeyPacket.class, C2SKeyPacket::new);
    }

    public static ResourceLocation packet(Class<?> clazz) {
        return BlockMorph.id(clazz.getName().toLowerCase(Locale.ROOT));
    }

}
