package tfar.blockmorph.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import tfar.blockmorph.PlayerDuck;
import tfar.blockmorph.network.client.S2CMorphPacket;
import tfar.blockmorph.platform.Services;

@Mixin(Player.class)
public class PlayerMixin implements PlayerDuck {

    boolean morphed;


    @Override
    public boolean isMorphed() {
        return morphed;
    }

    @Override
    public void setMorphed(boolean morphed) {
        if (this.morphed != morphed && (Object)this instanceof ServerPlayer serverPlayer) {
            Services.PLATFORM.sendToTracking(serverPlayer,new S2CMorphPacket(serverPlayer.getUUID(),morphed));
        }
        this.morphed = morphed;
        ((Entity)(Object)this).refreshDimensions();
        if (morphed && (Object)this instanceof ServerPlayer serverPlayer) {
            Vec3 pos = serverPlayer.blockPosition().getCenter();
            serverPlayer.connection.teleport(pos.x,pos.y-.5,pos.z,serverPlayer.getYRot(),serverPlayer.getXRot());
        }
    }
}