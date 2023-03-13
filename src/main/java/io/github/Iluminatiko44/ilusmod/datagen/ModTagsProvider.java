package io.github.Iluminatiko44.ilusmod.datagen;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class ModTagsProvider{

    public static class BlockTagsProvider extends TagsProvider<Block> {

        protected BlockTagsProvider(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, ExistingFileHelper fileHelper) {
            super(p_256596_, Registries.BLOCK, p_256513_, Ilusmod.MODID, fileHelper);
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        protected void addTags(HolderLookup.@NotNull Provider p_256380_) {

            for(RegistryObject<Block> log : BlockInit.LOGS) {
                ResourceKey<Block> key = log.getKey();
                this.tag(BlockTags.LOGS).add(key);
                this.tag(BlockTags.LOGS_THAT_BURN).add(key);
                this.tag(BlockTags.MINEABLE_WITH_AXE).add(key);
                this.tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(key);
            }
            for(RegistryObject<Block> ore : BlockInit.ORES) {
                ResourceKey<Block> key = ore.getKey();
                this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(key);
                this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(key);
            }
            // Plank Tag
            this.tag(BlockTags.PLANKS).add(
                    BlockInit.HAPPY_PLANKS.getKey()
            );
            // Sapling Tag
            this.tag(BlockTags.SAPLINGS).add(
                    BlockInit.HAPPY_SAPLING.getKey()
            );
            // Leaves Tag
            this.tag(BlockTags.LEAVES).add(
                    BlockInit.HAPPY_LEAVES.getKey()
            );
            // Mineable Tags
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                    BlockInit.PSEUDO_ICE.getKey()
            );
            this.tag(BlockTags.ICE).add(
                    BlockInit.PSEUDO_ICE.getKey()
            );
            this.tag(BlockTags.FROG_PREFER_JUMP_TO).add(
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

    public static class ItemTagsProvider extends TagsProvider<Item> {

        public static final TagKey<Item> HAPPY_LOGS = ItemTags.create(new ResourceLocation(Ilusmod.MODID, "happy_logs"));

        protected ItemTagsProvider(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, ExistingFileHelper fileHelper) {
            super(p_256596_, Registries.ITEM, p_256513_, Ilusmod.MODID, fileHelper);
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        protected void addTags(HolderLookup.@NotNull Provider p_256380_) {
            for (RegistryObject<Item> bItem : BlockInit.LOG_ITEMS) {
                ResourceKey<Item> key = bItem.getKey();
                this.tag(HAPPY_LOGS).add(key);
                this.tag(ItemTags.LOGS).add(key);
                this.tag(ItemTags.LOGS_THAT_BURN).add(key);
            }
        }
    }

}
