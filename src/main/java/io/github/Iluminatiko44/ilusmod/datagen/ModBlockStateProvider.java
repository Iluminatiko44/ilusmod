package io.github.Iluminatiko44.ilusmod.datagen;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Objects;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Ilusmod.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(BlockInit.HAPPY_BLOCK);
        blockWithItem(BlockInit.HAPPY_ORE);
        blockWithItem(BlockInit.DEEPSLATE_HAPPY_ORE);
        blockWithItem(BlockInit.ENDSTONE_HAPPY_ORE);
        blockWithItem(BlockInit.NETHERRACK_HAPPY_ORE);
        blockWithItem(BlockInit.PSEUDO_ICE);

        // Sets the state of the blocks
        logBlock(((RotatedPillarBlock) BlockInit.HAPPY_LOG.get()));
            // Copies the textures of the wood from the texture of the log
            // It needs the same texture for the log and the top/bottom of the log
        axisBlock((RotatedPillarBlock) BlockInit.HAPPY_WOOD.get(), blockTexture(BlockInit.HAPPY_LOG.get()), blockTexture(BlockInit.HAPPY_LOG.get()));

            // Sets own textures for the stripped wood
            // The first texture ist the texture of the log, the second is the texture of the top of the log
        axisBlock((RotatedPillarBlock) BlockInit.STRIPPED_HAPPY_LOG.get(),
                new ResourceLocation(Ilusmod.MODID, "block/stripped_happy_log"),
                new ResourceLocation(Ilusmod.MODID, "block/stripped_happy_log_top"));
        axisBlock((RotatedPillarBlock) BlockInit.STRIPPED_HAPPY_WOOD.get(),
                new ResourceLocation(Ilusmod.MODID, "block/stripped_happy_log"),
                new ResourceLocation(Ilusmod.MODID, "block/stripped_happy_log"));

        // Now the states for the items for the blocks >:)
        blockWithItem(BlockInit.HAPPY_PLANKS);
        blockWithItem(BlockInit.HAPPY_LEAVES);
        saplingBlock(BlockInit.HAPPY_SAPLING);

        simpleBlockItem(BlockInit.HAPPY_LOG.get(), models().withExistingParent("ilusmod:happy_log", "minecraft:block/cube_column"));
        simpleBlockItem(BlockInit.HAPPY_WOOD.get(), models().withExistingParent("ilusmod:happy_wood", "minecraft:block/cube_column"));
        simpleBlockItem(BlockInit.STRIPPED_HAPPY_LOG.get(), models().withExistingParent("ilusmod:stripped_happy_log", "minecraft:block/cube_column"));
        simpleBlockItem(BlockInit.STRIPPED_HAPPY_WOOD.get(), models().withExistingParent("ilusmod:stripped_happy_wood", "minecraft:block/cube_column"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    @SuppressWarnings("SameParameterValue")
    private void saplingBlock(RegistryObject<Block> block) {
        simpleBlockWithItem(
                block.get(),
                models()
                        // The texture of the sapling
                        .cross(
                                Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey( block.get() )).getPath(),
                                blockTexture( block.get() )
                        )
                        // How it should be rendered
                        .renderType("cutout"));
    }
}
