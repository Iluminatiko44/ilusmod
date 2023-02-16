package io.github.Iluminatiko44.ilusmod.Init;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ilusmod.MODID);

    public static final RegistryObject<Item> CUM_BALL = ITEMS.register("cum_ball", () -> new Item(new Item.Properties()));

    public static final RegistryObject<BlockItem> CUM_BLOCK = ITEMS.register("cum_block", () -> new BlockItem(BlockInit.CUM_BLOCK.get(), new Item.Properties()));
}
