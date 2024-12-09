package tfar.blockmorph.mixin;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
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
    }
}