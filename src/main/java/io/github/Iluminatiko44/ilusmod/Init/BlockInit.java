package io.github.Iluminatiko44.ilusmod.Init;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Ilusmod.MODID);

    public static final RegistryObject<Block> HAPPY_BLOCK = register("happy_block",
            () -> new Block(Block.Properties.of(Material.CLAY).strength(1.5f, 6.0f).requiresCorrectToolForDrops()),
                  new Item.Properties());

    public static final RegistryObject<Block> PSEUDO_ICE = register("pseudo_ice",
            () -> new Block(Block.Properties.of(Material.CLAY).friction(1.2f).strength(3.0f, 1.0f).requiresCorrectToolForDrops()),
            new Item.Properties().stacksTo(128));

    @SuppressWarnings("rawtypes") // Yes, I know this is bad practice, but I don't know how to fix it
    public static final RegistryObject[] ILUSBLOCKS = {
            HAPPY_BLOCK,
            PSEUDO_ICE
    };
    // A supplier for making the item block easier
    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> supplier, Item.Properties properties) {
        RegistryObject<T> block = BLOCKS.register(name, supplier);

        ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), properties));

        return block;
    }
}