package io.github.Iluminatiko44.ilusmod.worldGen;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.ForkingTrunkPlacer;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> HAPPY_KEY = registerKey("happy");

    // TODO: Understand UpwardBranchingTrunkPlacer (IntProvider (Codec?) --> ConstantInt!) for cooler trees.
    public static void bootstrap(BootstapContext< ConfiguredFeature<?, ?> > context) {
        register(
                context, HAPPY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockInit.HAPPY_LOG.get()),
                    new ForkingTrunkPlacer(7, 4, 4),
                    BlockStateProvider.simple(BlockInit.HAPPY_LEAVES.get()),
                    new AcaciaFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0)),
                    new TwoLayersFeatureSize(3, 1, 5)).build()

        );
    }

    // I don't know what this does, but it's in the example
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Ilusmod.MODID, name));
    }

    private static < FC extends FeatureConfiguration, F extends Feature<FC> > void register(BootstapContext< ConfiguredFeature<?, ?> > context,
                                                                                            ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }


}
