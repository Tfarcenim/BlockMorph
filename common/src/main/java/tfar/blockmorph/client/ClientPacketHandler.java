package tfar.blockmorph.client;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.lwjgl.glfw.GLFW;
import tfar.blockmorph.PlayerDuck;
import tfar.blockmorph.network.client.S2CMorphPacket;

public class ClientPacketHandler {

    public static final KeyMapping TOGGLE = new KeyMapping("Toggle Morph", GLFW.GLFW_KEY_Y,"Block Morph");

    public static void handle(S2CMorphPacket packet) {
        Level level = Minecraft.getInstance().level;
        if (level != null) {
            Player player = level.getPlayerByUUID(packet.uuid());
            if (player != null) {
                PlayerDuck.of(player).toggleMorph();
            }
        }
    }
}
