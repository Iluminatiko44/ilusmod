package com.iluminatiko.ilusmod.Init;

import com.iluminatiko.ilusmod.Ilusmod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ilusmod.MODID);

    public static final RegistryObject<Item> CUM_BALL = ITEMS.register("cum_ball", () -> new Item(new Item.Properties()));
}
