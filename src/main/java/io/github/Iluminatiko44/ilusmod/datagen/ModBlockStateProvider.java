package io.github.Iluminatiko44.ilusmod.datagen;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

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
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
