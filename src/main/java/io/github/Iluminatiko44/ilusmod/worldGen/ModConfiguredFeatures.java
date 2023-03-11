package io.github.Iluminatiko44.ilusmod.worldGen;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer;
import net.minecraftforge.common.Tags;

import java.util.OptionalInt;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> HAPPY_KEY = registerKey("happy");

    // TODO: Understand Trees...
    // TODO: Understand the holder set parameter for the happy_leaves (currently Tags.Block.ORES)
    public static void bootstrap(BootstapContext< ConfiguredFeature<?, ?> > context) {
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);
        register(
                context, HAPPY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                    BlockStateProvider.simple(BlockInit.HAPPY_LOG.get()),
                    new UpwardsBranchingTrunkPlacer(10, 4, 4, UniformInt.of(3,6), 0.2f, UniformInt.of(3,6), holdergetter.getOrThrow(Tags.Blocks.ORES)),
                    BlockStateProvider.simple(BlockInit.HAPPY_LEAVES.get()),
                    new AcaciaFoliagePlacer(ConstantInt.of(3), ConstantInt.of(0)),
                    new ThreeLayersFeatureSize(4, 4, 3, 4, 3,OptionalInt.of(2))).build()

        );
    }

    // I don't know what this does, but it's in the example
    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(Ilusmod.MODID, name));
    }

    @SuppressWarnings("SameParameterValue")
    private static < FC extends FeatureConfiguration, F extends Feature<FC> > void register(BootstapContext< ConfiguredFeature<?, ?> > context,
                                                                                            ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC config) {
        context.register(key, new ConfiguredFeature<>(feature, config));
    }


}
