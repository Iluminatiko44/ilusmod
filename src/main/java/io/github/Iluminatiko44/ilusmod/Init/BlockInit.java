package io.github.Iluminatiko44.ilusmod.Init;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.custom.ModFlammableRotatedPillarBlock;
import io.github.Iluminatiko44.ilusmod.worldGen.tree.HappyTreeGrower;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ilusmod.MODID);

    public static List<RegistryObject<Block>> ILUSBLOCKS = new ArrayList<>();/*List.of(
            HAPPY_BLOCK, // 0
            HAPPY_ORE, // 1
            DEEPSLATE_HAPPY_ORE, // 2
            ENDSTONE_HAPPY_ORE, // 3
            NETHERRACK_HAPPY_ORE, // 4
            PSEUDO_ICE, // 5
            HAPPY_LOG, // 6
            HAPPY_WOOD, // 7
            STRIPPED_HAPPY_LOG, // 8
            STRIPPED_HAPPY_WOOD, // 9
            HAPPY_PLANKS, // 10
            HAPPY_LEAVES, // 11
            HAPPY_SAPLING // 12
    );*/
    public static List<RegistryObject<Item>> BlockItems = new ArrayList<>();

    public static final RegistryObject<Block> HAPPY_BLOCK = register("happy_block",
            () -> new Block(Block.Properties.of(Material.CLAY).strength(1.5f, 10.0f).requiresCorrectToolForDrops()),
                  new Item.Properties());

    public static final RegistryObject<Block> HAPPY_ORE = register("happy_ore",
            () -> new Block(Block.Properties.of(Material.METAL).strength(3.0f, 10.0f).requiresCorrectToolForDrops()),
            new Item.Properties());
    public static final RegistryObject<Block> DEEPSLATE_HAPPY_ORE = register("happy_ore_deepslate",
            () -> new Block(Block.Properties.of(Material.METAL).strength(5.0f, 12.0f).requiresCorrectToolForDrops()),
            new Item.Properties());
    public static final RegistryObject<Block> ENDSTONE_HAPPY_ORE = register("happy_ore_endstone",
            () -> new Block(Block.Properties.of(Material.METAL).strength(3.0f, 10.0f).requiresCorrectToolForDrops()),
            new Item.Properties());
    public static final RegistryObject<Block> NETHERRACK_HAPPY_ORE = register("happy_ore_netherrack",
            () -> new Block(Block.Properties.of(Material.METAL).strength(2.0f, 10.0f).requiresCorrectToolForDrops()),
            new Item.Properties());
    public static final RegistryObject<Block> PSEUDO_ICE = register("pseudo_ice",
            () -> new Block(Block.Properties.of(Material.CLAY).friction(1.2f).strength(3.0f, 1.0f).requiresCorrectToolForDrops()),
            new Item.Properties().stacksTo(128));

    // The difference between a log and wood is that, logs are the "cut" type of wood
    public static final RegistryObject<Block> HAPPY_LOG = register("happy_log",
            () -> new ModFlammableRotatedPillarBlock(Block.Properties.copy(Blocks.OAK_LOG).strength(3.0f, 10.0f)),
            new Item.Properties());
    public static final RegistryObject<Block> HAPPY_WOOD = register("happy_wood",
            () -> new ModFlammableRotatedPillarBlock(Block.Properties.copy(Blocks.OAK_WOOD).strength(3.0f, 10.0f)),
            new Item.Properties());
    public static final RegistryObject<Block> STRIPPED_HAPPY_LOG = register("stripped_happy_log",
            () -> new ModFlammableRotatedPillarBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(3.0f, 10.0f)),
            new Item.Properties());
    public static final RegistryObject<Block> STRIPPED_HAPPY_WOOD = register("stripped_happy_wood",
            () -> new ModFlammableRotatedPillarBlock(Block.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(3.0f, 10.0f)),
            new Item.Properties());

    public static final RegistryObject<Block> HAPPY_PLANKS = register("happy_planks",
            () -> new Block(Block.Properties.copy(Blocks.OAK_PLANKS).strength(3.0f, 10.0f)) {
                @Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 5;}
            },
            new Item.Properties());
    public static final RegistryObject<Block> HAPPY_LEAVES = register("happy_leaves",
            () -> new LeavesBlock(Block.Properties.copy(Blocks.OAK_LEAVES).strength(3.0f)){
                @Override public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return true;}
                @Override public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 30;}
                @Override public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {return 60;}
            },
            new Item.Properties());

    public static final RegistryObject<Block> HAPPY_SAPLING = register("happy_sapling",
            () -> new SaplingBlock(
                    new HappyTreeGrower(),
                    Block.Properties.copy(Blocks.OAK_SAPLING)),
            new Item.Properties());


    public static final List<RegistryObject<Block> > ORES = ILUSBLOCKS.subList(1, 5);
    @SuppressWarnings("unused")
    public static final List<RegistryObject<Block> > WOODEN = ILUSBLOCKS.subList(6, 11);
    public static final List<RegistryObject<Block> > LOGS = ILUSBLOCKS.subList(6, 10);
    public static final List<RegistryObject<Item> > LOG_ITEMS = BlockItems.subList(6, 10);

    // A supplier for making the item block easier
    private static RegistryObject<Block> register(String name, Supplier<Block> supplier, Item.Properties properties) {
        // Register the block
        RegistryObject<Block> block = BLOCKS.register(name, supplier);
        ILUSBLOCKS.add(block);
        // Register the item block in own class ItemInit
        RegistryObject<Item> blockItem = ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), properties));
        BlockItems.add(blockItem);
        return block;
    }

}
