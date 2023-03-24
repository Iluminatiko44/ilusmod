package io.github.Iluminatiko44.ilusmod.datagen;

import com.mojang.logging.LogUtils;
import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import io.github.Iluminatiko44.ilusmod.Init.ItemInit;
import io.github.Iluminatiko44.ilusmod.Init.custom.ModFlammableRotatedPillarBlock;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.concurrent.CompletableFuture;

public class ModTagsProvider{

    public static class BlockTagsProvider extends TagsProvider<Block> {

        protected BlockTagsProvider(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, ExistingFileHelper fileHelper) {
            super(p_256596_, Registries.BLOCK, p_256513_, Ilusmod.MODID, fileHelper);
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        protected void addTags(HolderLookup.@NotNull Provider p_256380_) {

            for(RegistryObject<Block> log : BlockInit.BLOCKS.getEntries().stream() .filter(block -> block.get() instanceof ModFlammableRotatedPillarBlock) .toList()) {
                ResourceKey<Block> key = log.getKey();
                this.tag(BlockTags.LOGS).add(key);
                this.tag(BlockTags.LOGS_THAT_BURN).add(key);
                this.tag(BlockTags.MINEABLE_WITH_AXE).add(key);
                this.tag(BlockTags.OVERWORLD_NATURAL_LOGS).add(key);
            }
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(BlockInit.PSEUDO_ICE.getKey());

            for(RegistryObject<Block> ore : BlockInit.BLOCKS.getEntries().stream() .filter(block -> block.getKey().location().toString().contains("ore")) .toList()) {
                ResourceKey<Block> key = ore.getKey();
                this.tag(Tags.Blocks.ORES).add(key);
                this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(key);
                this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(key);
            }
            this.tag(Tags.Blocks.ORE_RATES_DENSE).add(BlockInit.HAPPY_ORE.getKey(), BlockInit.DEEPSLATE_HAPPY_ORE.getKey(), BlockInit.NETHERRACK_HAPPY_ORE.getKey());

            this.tag(Tags.Blocks.ORE_BEARING_GROUND_STONE).add(BlockInit.HAPPY_ORE.getKey());
            this.tag(Tags.Blocks.ORE_BEARING_GROUND_DEEPSLATE).add(BlockInit.DEEPSLATE_HAPPY_ORE.getKey());
            this.tag(Tags.Blocks.ORE_BEARING_GROUND_NETHERRACK).add(BlockInit.NETHERRACK_HAPPY_ORE.getKey());

            this.tag(BlockTags.PLANKS).add(BlockInit.HAPPY_PLANKS.getKey());
            this.tag(BlockTags.SAPLINGS).add(BlockInit.HAPPY_SAPLING.getKey());
            this.tag(BlockTags.LEAVES).add(BlockInit.HAPPY_LEAVES.getKey());

            this.tag(BlockTags.ICE).add(BlockInit.PSEUDO_ICE.getKey());
            this.tag(BlockTags.FROG_PREFER_JUMP_TO).add(BlockInit.PSEUDO_ICE.getKey());
            this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(BlockInit.HAPPY_BLOCK.getKey());
            // Needs ... Tool Tags
            this.tag(BlockTags.NEEDS_DIAMOND_TOOL).add(BlockInit.PSEUDO_ICE.getKey());
            this.tag(Tags.Blocks.NEEDS_WOOD_TOOL).add(BlockInit.HAPPY_BLOCK.getKey());

        }


    }

    public static class ItemTagsProvider extends TagsProvider<Item> {

        @SuppressWarnings("unused")
        private static final Logger LOGGER = LogUtils.getLogger();
        public static final TagKey<Item> HAPPY_LOGS = ItemTags.create(new ResourceLocation(Ilusmod.MODID, "happy_logs"));
        protected ItemTagsProvider(PackOutput p_256596_, CompletableFuture<HolderLookup.Provider> p_256513_, ExistingFileHelper fileHelper) {
            super(p_256596_, Registries.ITEM, p_256513_, Ilusmod.MODID, fileHelper);
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        protected void addTags(HolderLookup.@NotNull Provider p_256380_) {
            // Trys to add a tag to every item
            for (RegistryObject<Item> ItemResource : ItemInit.ITEMS.getEntries()) {

                ResourceKey<Item> key = ItemResource.getKey();
                Item item = ItemResource.get();

                // adds appropriate tags to every tool
                if(item instanceof TieredItem) {
                    this.tag(Tags.Items.TOOLS).add(key);
                    if(item instanceof SwordItem) this.tag(Tags.Items.TOOLS_SWORDS).add(key);
                    if(item instanceof PickaxeItem) this.tag(Tags.Items.TOOLS_PICKAXES).add(key);
                    if(item instanceof AxeItem) this.tag(Tags.Items.TOOLS_AXES).add(key);
                    if(item instanceof ShovelItem) this.tag(Tags.Items.TOOLS_SHOVELS).add(key);
                    if(item instanceof HoeItem) this.tag(Tags.Items.TOOLS_HOES).add(key);
                }

                // adds appropriate tags to every log or wood
                if (item instanceof BlockItem && ((BlockItem) item).getBlock() instanceof ModFlammableRotatedPillarBlock) {
                    this.tag(HAPPY_LOGS).add(key);
                    this.tag(ItemTags.LOGS).add(key);
                    this.tag(ItemTags.LOGS_THAT_BURN).add(key);
                }

                // Adds the appropriate armor tags
                if(item instanceof ArmorItem) {
                    this.tag(Tags.Items.ARMORS).add(key);
                    String Slot = ((ArmorItem) item) .getType().getSlot().getName();
                    switch (Slot) {
                        case "head" -> this.tag(Tags.Items.ARMORS_HELMETS).add(key);
                        case "chest" -> this.tag(Tags.Items.ARMORS_CHESTPLATES).add(key);
                        case "legs" -> this.tag(Tags.Items.ARMORS_LEGGINGS).add(key);
                        case "feet" -> this.tag(Tags.Items.ARMORS_BOOTS).add(key);
                        default -> throw new IllegalStateException("Unexpected value: " + Slot);
                    }
                }

            }

            this.tag(ItemTags.COALS).add(ItemInit.HAPPY_COAL.getKey());
        }
    }

}
