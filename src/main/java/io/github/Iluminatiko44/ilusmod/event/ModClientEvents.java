package io.github.Iluminatiko44.ilusmod.event;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.EntityInit;
import io.github.Iluminatiko44.ilusmod.client.models.RocketModel;
import io.github.Iluminatiko44.ilusmod.client.renderer.AbstractRocketRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid= Ilusmod.MODID, bus= Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
    @SubscribeEvent
    public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityInit.ROCKET.get(), AbstractRocketRenderer.RocketRenderer::new);
        event.registerEntityRenderer(EntityInit.NUKE.get(), AbstractRocketRenderer.NukeRenderer::new);
        event.registerEntityRenderer(EntityInit.EXPLOSIVE_ROCKET.get(), AbstractRocketRenderer.ExplosiveRocketRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(RocketModel.LAYER_LOCATION, RocketModel::createBodyLayer);
    }
}
