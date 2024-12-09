package tfar.blockmorph.network.client;


import tfar.blockmorph.network.ModPacket;

public interface S2CModPacket extends ModPacket {

    void handleClient();

}
