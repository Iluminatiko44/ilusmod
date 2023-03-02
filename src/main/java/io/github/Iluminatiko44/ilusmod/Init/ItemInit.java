package io.github.Iluminatiko44.ilusmod.Init;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ilusmod.MODID);

    // Values for normal items to happy item: brightness+20, saturation = 0
    public static final RegistryObject<Item> HAPPY_BALL = ITEMS.register("happy_ball", () -> new Item(getProps()));

    public static final RegistryObject<Item> HAPPY_INGOT = ITEMS.register("happy_ingot", () -> new Item(getProps()));

    public static final RegistryObject<Item> GRAPES = ITEMS.register("grapes", () -> new Item(getProps().food(Foods.GRAPES)));

    public static final RegistryObject<Item> POMMES = ITEMS.register("pommes", () -> new Item(getProps().food(Foods.POMMES)));

    public static class Foods {
        public static final FoodProperties GRAPES = new FoodProperties.Builder().nutrition(1).saturationMod(2).alwaysEat().fast().build();
        public static final FoodProperties POMMES = new FoodProperties.Builder().nutrition(8).saturationMod(0)
                                                                                .effect(() -> new MobEffectInstance(MobEffects.POISON, 20*10, 4), 0.1f)             // giving the player a poison effect (0.1%) for 10 seconds (20 ticks per second)
                                                                                .effect(() -> new MobEffectInstance(MobEffects.WITHER, 20*10, 5), 0.01f).build();   // giving the player a wither effect (0.01%) for 10 seconds (20 ticks per second)
    }

    // Normal tools from happy items
    public static final RegistryObject<SwordItem> HAPPY_SWORD = ITEMS.register("happy_sword", () -> new SwordItem(Tiers.NETHERITE, 3, 2.4f, getProps()));

    public static final RegistryObject<PickaxeItem> HAPPY_PICKAXE = ITEMS.register("happy_pickaxe", () -> new PickaxeItem(Tiers.NETHERITE, 3, 2.4f, getProps()));

    public static final RegistryObject<AxeItem> HAPPY_AXE = ITEMS.register("happy_axe", () -> new AxeItem(Tiers.NETHERITE, 3, 2.4f, getProps()));

    public static final RegistryObject<ShovelItem> HAPPY_SHOVEL = ITEMS.register("happy_shovel", () -> new ShovelItem(Tiers.NETHERITE, 3, 2.4f, getProps()));

    public static final RegistryObject<HoeItem> HAPPY_HOE = ITEMS.register("happy_hoe", () -> new HoeItem(Tiers.NETHERITE, 3, 2.4f, getProps()));

    @SuppressWarnings("rawtypes") // Yes, I know this is bad practice, but I don't know how to fix it
    public static final RegistryObject[] ILUSITEMS = {
            HAPPY_BALL,
            HAPPY_INGOT,
            GRAPES,
            POMMES,
            HAPPY_SWORD,
            HAPPY_PICKAXE,
            HAPPY_AXE,
            HAPPY_SHOVEL,
            HAPPY_HOE
    };

    public static Item.Properties getProps() {
        return new Item.Properties();
    }
}
