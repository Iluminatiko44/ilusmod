package io.github.Iluminatiko44.ilusmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.client.models.RocketModel;
import io.github.Iluminatiko44.ilusmod.entities.AbstractRocket;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractRocketRenderer<T extends AbstractRocket> extends EntityRenderer<AbstractRocket> {
    private final RocketModel<T> model;
    private final ResourceLocation ROCKET_LOCATION;
    protected AbstractRocketRenderer(EntityRendererProvider.Context context, ResourceLocation location) {
        super(context);
        ROCKET_LOCATION = location;
        this.model = new RocketModel<T>(context.bakeLayer(RocketModel.LAYER_LOCATION));
    }


    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull AbstractRocket abstractRocket) {
        return ROCKET_LOCATION;
    }

    @Override
    public void render(@NotNull AbstractRocket rocket, float p_114486_, float p_114487_, @NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose(); // Make this frame the new frame
        // I stole this from AbstractArrowRenderer. It rotates the rocket.
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(p_114487_, rocket.yRotO, rocket.getYRot()) - 90.0F));
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(p_114487_, rocket.xRotO, rocket.getXRot()) - 90.0F));

        poseStack.scale(1.0F, 1.0F, 1.0F);

        VertexConsumer vertexConsumer = bufferSource.getBuffer(this.model.renderType(ROCKET_LOCATION));
        // This is the magic line
        this.model.renderToBuffer(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        poseStack.popPose(); // Make this frame the last frame
        super.render(rocket, p_114486_, p_114487_, poseStack, bufferSource, packedLight);
    }

    public static class RocketRenderer extends AbstractRocketRenderer<AbstractRocket.Rocket> {
        public RocketRenderer(EntityRendererProvider.Context context) {
            super(context, new ResourceLocation(Ilusmod.MODID,"textures/entity/rocket/rocket.png"));
        }
    }
    public static class NukeRenderer extends AbstractRocketRenderer<AbstractRocket.Rocket> {
        public NukeRenderer(EntityRendererProvider.Context context) {
            super(context, new ResourceLocation(Ilusmod.MODID,"textures/entity/rocket/nuclear_rocket.png"));
        }
    }
    public static class ExplosiveRocketRenderer extends AbstractRocketRenderer<AbstractRocket.Rocket> {
        public ExplosiveRocketRenderer(EntityRendererProvider.Context context) {
            super(context, new ResourceLocation(Ilusmod.MODID,"textures/entity/rocket/explosive_rocket.png"));
        }
    }

}

