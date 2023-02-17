package io.github.Iluminatiko44.ilusmod.Init;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ilusmod.MODID);

    public static final RegistryObject<Item> HAPPY_BALL = ITEMS.register("happy_ball", () -> new Item(new Item.Properties()));

}
