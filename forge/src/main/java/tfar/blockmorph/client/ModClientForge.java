package tfar.blockmorph.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import tfar.blockmorph.PlayerDuck;
import tfar.blockmorph.network.server.C2SKeyPacket;
import tfar.blockmorph.platform.Services;

public class ModClientForge {

    public static void init(IEventBus bus) {
        bus.addListener(ModClientForge::keybind);
        MinecraftForge.EVENT_BUS.addListener(ModClientForge::renderBlock);
        MinecraftForge.EVENT_BUS.addListener(ModClientForge::clientTick);
    }

    static void clientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            while (ClientPacketHandler.TOGGLE.consumeClick()) {
                Services.PLATFORM.sendToServer(new C2SKeyPacket());
            }
        }
    }

    public static void keybind(RegisterKeyMappingsEvent event) {
        event.register(ClientPacketHandler.TOGGLE);
    }

    public static void renderBlock(RenderPlayerEvent.Pre event) {
        Player player = event.getEntity();
        PlayerDuck playerDuck = PlayerDuck.of(player);
        if (playerDuck.isMorphed()) {
            ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
            ItemStack stack = player.getItemBySlot(EquipmentSlot.HEAD);
            PoseStack pPoseStack = event.getPoseStack();
            MultiBufferSource pBuffer = event.getMultiBufferSource();
            itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, event.getPackedLight(), OverlayTexture.NO_OVERLAY, pPoseStack, pBuffer, player.level(), player.getId());

            event.setCanceled(true);
        }
    }

}
