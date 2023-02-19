package io.github.Iluminatiko44.ilusmod.Init;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ilusmod.MODID);

    public static final RegistryObject<Item> HAPPY_BALL = ITEMS.register("happy_ball", () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GRAPES = ITEMS.register("grapes", () -> new Item(new Item.Properties().food(Foods.GRAPES)));

    public static final RegistryObject<Item> POMMES = ITEMS.register("pommes", () -> new Item(new Item.Properties().food(Foods.POMMES)));

    public static class Foods {
        public static final FoodProperties GRAPES = new FoodProperties.Builder().nutrition(1).saturationMod(2).alwaysEat().fast().build();
        public static final FoodProperties POMMES = new FoodProperties.Builder().nutrition(8).saturationMod(0)
                                                                                .effect(() -> new MobEffectInstance(MobEffects.POISON, 20*10, 4), 0.1f)             // giving the player a poison effect (0.1%) for 10 seconds (20 ticks per second)
                                                                                .effect(() -> new MobEffectInstance(MobEffects.WITHER, 20*10, 5), 0.01f).build();   // giving the player a wither effect (0.01%) for 10 seconds (20 ticks per second)

    }
}
