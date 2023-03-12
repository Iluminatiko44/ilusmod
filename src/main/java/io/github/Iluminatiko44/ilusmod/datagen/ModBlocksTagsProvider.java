package io.github.Iluminatiko44.ilusmod.datagen;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModBlocksTagsProvider extends TagsProvider<Block> {

    protected ModBlocksTagsProvider(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, ExistingFileHelper fileHelper) {
        super(p_256596_, Registries.BLOCK, p_256513_, Ilusmod.MODID, fileHelper);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void addTags(HolderLookup.@NotNull Provider p_256380_) {

        for(RegistryObject<Block> log : BlockInit.WOODEN) {
            this.tag(BlockTags.LOGS).add(log.getKey());
            this.tag(BlockTags.LOGS_THAT_BURN).add(log.getKey());
            this.tag(BlockTags.MINEABLE_WITH_AXE).add(log.getKey());
        }
        for(RegistryObject<Block> ore : BlockInit.ORES) {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ore.getKey());
            this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(ore.getKey());
        }
        // Mineable Tags
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                BlockInit.PSEUDO_ICE.getKey()
        );
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                BlockInit.HAPPY_BLOCK.getKey()
        );
        // Needs ... Tool Tags
        this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(
                BlockInit.PSEUDO_ICE.getKey()
        );
        this.tag(Tags.Blocks.NEEDS_WOOD_TOOL).add(
                BlockInit.HAPPY_BLOCK.getKey()
        );
    }
}
