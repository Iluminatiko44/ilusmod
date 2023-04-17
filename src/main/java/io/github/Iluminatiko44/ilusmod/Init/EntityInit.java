package io.github.Iluminatiko44.ilusmod.Init;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.datagen.ModTagsProvider;
import io.github.Iluminatiko44.ilusmod.entities.AbstractRocket;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Ilusmod.MODID);
    public static final RegistryObject<EntityType<AbstractRocket.Rocket>> ROCKET =
            ENTITY_TYPES.register("rocket", () -> EntityType.Builder.
                    of((EntityType<AbstractRocket.Rocket> e, Level l) -> new AbstractRocket.Rocket(e, l),
                            MobCategory.MISC)
                    .sized(0.2F, 0.85F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(new ResourceLocation(Ilusmod.MODID, "rocket").toString()));
    public static final RegistryObject<EntityType<AbstractRocket.Nuke>> NUKE =
            ENTITY_TYPES.register("nuclear_rocket", () -> EntityType.Builder.
                            of((EntityType<AbstractRocket.Nuke> e, Level l) -> new AbstractRocket.Nuke(e, l, ModTagsProvider.EntityTypeTagsProvider.ROCKETS),
                            MobCategory.MISC)
                    .sized(0.5F, 1.0F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(new ResourceLocation(Ilusmod.MODID, "nuclear_rocket").toString()));
    public static final RegistryObject<EntityType<AbstractRocket.ExplosiveRocket>> EXPLOSIVE_ROCKET =
                                    // The name of the entity in the registry
            ENTITY_TYPES.register("explosive_rocket", () -> EntityType.Builder.
                            of((EntityType<AbstractRocket.ExplosiveRocket> e, Level l) -> new AbstractRocket.ExplosiveRocket(e, l, ModTagsProvider.EntityTypeTagsProvider.ROCKETS),
                            MobCategory.MISC)
                    .sized(0.2F, 0.85F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(new ResourceLocation(Ilusmod.MODID, "explosive_rocket").toString()));
}
