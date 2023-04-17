package io.github.Iluminatiko44.ilusmod.Init;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelLayerInit {
    public static final ModelLayerLocation ROCKET = new ModelLayerLocation(new ResourceLocation(Ilusmod.MODID, "rocket"), "main");
    public static final ModelLayerLocation NUCLEAR_ROCKET = new ModelLayerLocation(new ResourceLocation(Ilusmod.MODID, "nuclear_rocket"), "main");
    public static final ModelLayerLocation EXPLOSIVE_ROCKET = new ModelLayerLocation(new ResourceLocation(Ilusmod.MODID, "explosive_rocket"), "main");
}
