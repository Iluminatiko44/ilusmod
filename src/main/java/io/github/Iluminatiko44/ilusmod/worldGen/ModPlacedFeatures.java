package io.github.Iluminatiko44.ilusmod.worldGen;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {

    public static final ResourceKey<PlacedFeature> HAPPY_PLACED_KEY = createKey("happy_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?> > configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // configuredFeatures.getOrThrow(...) gets the bootstrap method from ModConfiguredFeatures aka. the tree / chance
        register(context, HAPPY_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.HAPPY_KEY),
                // PlacementUtils: 1: count, 2: chance (?) (Also the reciprocal needs to be an int, so 0.1, 0.2, 0.25 but not 0.3 for example), 3: extra;    The Block is a filter for where the sapling tree can be placed
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1f, 2), BlockInit.HAPPY_SAPLING.get())
        );
    }

    @SuppressWarnings("SameParameterValue")
    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Ilusmod.MODID, name));
    }

    @SuppressWarnings("SameParameterValue")
    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?> > config, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(config, List.copyOf(modifiers)));
    }
    @SuppressWarnings("unused")
    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?> > config, PlacementModifier... modifiers) {
        context.register(key, new PlacedFeature(config, List.of(modifiers)));
    }
}
