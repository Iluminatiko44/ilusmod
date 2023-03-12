package io.github.Iluminatiko44.ilusmod.worldGen;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.ThreeLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.AcaciaFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.UpwardsBranchingTrunkPlacer;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.RuleTest;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.Tags;

import java.util.List;
import java.util.OptionalInt;

public class ModConfiguredFeatures {

    public static final ResourceKey<ConfiguredFeature<?, ?>> HAPPY_KEY = registerKey("happy");

    public static final ResourceKey<ConfiguredFeature<?, ?>> OVERWORLD_HAPPY_ORE_KEY = registerKey("happy_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> END_HAPPY_ORE_KEY = registerKey("end_happy_ore");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_HAPPY_ORE_KEY = registerKey("nether_happy_ore");

    // TODO: Understand Trees...
    // TODO: Understand the holder set parameter for the happy_leaves (currently Tags.Block.ORES)
    public static void bootstrap(BootstapContext< ConfiguredFeature<?, ?> > context) {
        HolderGetter<Block> holdergetter = context.lookup(Registries.BLOCK);
        RuleTest stoneReplaceable = new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES);
        RuleTest deepslateReplaceable = new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);
        RuleTest netherrackReplaceable = new BlockMatchTest(Blocks.NETHERRACK);
        RuleTest endstoneReplaceable = new BlockMatchTest(Blocks.END_STONE);

        List<OreConfiguration.TargetBlockState> overworldHappyOres =
                List.of(
                        OreConfiguration.target(stoneReplaceable, BlockInit.HAPPY_ORE.get().defaultBlockState()),
                        OreConfiguration.target(deepslateReplaceable, BlockInit.DEEPSLATE_HAPPY_ORE.get().defaultBlockState())
                );
                                                                                                                  // this is the oreSize in Blocks
        register(context, OVERWORLD_HAPPY_ORE_KEY, Feature.ORE, new OreConfiguration(overworldHappyOres, 16));
        register(context, END_HAPPY_ORE_KEY, Feature.ORE, new OreConfiguration(endstoneReplaceable, BlockInit.ENDSTONE_HAPPY_ORE.get().defaultBlockState(), 8));
        register(context, NETHER_HAPPY_ORE_KEY, Feature.ORE, new OreConfiguration(netherrackReplaceable, BlockInit.NETHERRACK_HAPPY_ORE.get().defaultBlockState(), 12));

        // Tree
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
