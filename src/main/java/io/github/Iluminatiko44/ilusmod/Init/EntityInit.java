package io.github.Iluminatiko44.ilusmod.Init;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.custom.rockets.RocketEntity;
import io.github.Iluminatiko44.ilusmod.datagen.ModTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Ilusmod.MODID);
    private static final TagKey<EntityType<?>> ROCKETS = ModTagsProvider.EntityTypeTagsProvider.ROCKETS;
    public static final RegistryObject<EntityType<RocketEntity>> ROCKET =
            ENTITY_TYPES.register("rocket", () -> EntityType.Builder.
                    of((EntityType<RocketEntity> e, Level l) -> new RocketEntity.Rocket(e, l, ROCKETS),
                            MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(new ResourceLocation(Ilusmod.MODID, "rocket").toString()));
    public static final RegistryObject<EntityType<RocketEntity>> NUKE_ROCKET =
            ENTITY_TYPES.register("nuclear_rocket", () -> EntityType.Builder.
                            of((EntityType<RocketEntity> e, Level l) -> new RocketEntity.Nuke(e, l, ROCKETS),
                            MobCategory.MISC)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(new ResourceLocation(Ilusmod.MODID, "nuclear_rocket").toString()));
    public static final RegistryObject<EntityType<RocketEntity>> EXPLOSIVE_ROCKET =
                                    // The name of the entity in the registry
            ENTITY_TYPES.register("explosive_rocket", () -> EntityType.Builder.
                            of((EntityType<RocketEntity> e, Level l) -> new RocketEntity.ExplosiveRocket(e, l, ROCKETS),
                            MobCategory.MISC)
                    .sized(0.25F, 0.25F)
                    .clientTrackingRange(4)
                    .updateInterval(20)
                    .build(new ResourceLocation(Ilusmod.MODID, "explosive_rocket").toString()));
}
