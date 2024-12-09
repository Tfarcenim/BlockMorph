package tfar.blockmorph;

import net.minecraft.world.entity.player.Player;

public interface PlayerDuck {
    boolean isMorphed();
    void setMorphed(boolean morphed);
    default void toggleMorph(){
        setMorphed(!isMorphed());
    }
    static PlayerDuck of(Player player) {
        return (PlayerDuck) player;
    }
}
