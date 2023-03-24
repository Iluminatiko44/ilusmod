package io.github.Iluminatiko44.ilusmod.Init;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.base.ModArmorMaterial;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class ItemInit {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Ilusmod.MODID);

    // Values for normal items to happy item: brightness+20, saturation = 0
    public static final RegistryObject<Item> HAPPY_BALL = register("happy_ball");

    public static final RegistryObject<Item> HAPPY_INGOT = register("happy_ingot");

    public static final RegistryObject<Item> GRAPES = register("grapes", () -> new Item(getProps().food(Foods.GRAPES)));

    public static final RegistryObject<Item> POMMES = register("pommes", () -> new Item(getProps().food(Foods.POMMES)));

    public static final RegistryObject<Item> HAPPY_COAL = register("happy_coal", () -> new Item(getProps().fireResistant()) {
        @Override
        public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
            return 16000;
        }
    });

    public static class Foods {
        public static final FoodProperties GRAPES = new FoodProperties.Builder().nutrition(1).saturationMod(2).alwaysEat().fast().build();
        public static final FoodProperties POMMES = new FoodProperties.Builder().nutrition(8).saturationMod(0)
                                                                                .effect(() -> new MobEffectInstance(MobEffects.POISON, 20*10, 4), 0.1f)             // giving the player a poison effect (0.1%) for 10 seconds (20 ticks per second)
                                                                                .effect(() -> new MobEffectInstance(MobEffects.WITHER, 20*10, 5), 0.01f).build();   // giving the player a wither effect (0.01%) for 10 seconds (20 ticks per second)
    }

    // Normal tools from happy items
    public static final RegistryObject<SwordItem> HAPPY_SWORD = ITEMS.register("happy_sword", () -> new SwordItem(Tiers.HAPPY, 3, 2.4f, getProps()));

    public static final RegistryObject<PickaxeItem> HAPPY_PICKAXE = ITEMS.register("happy_pickaxe", () -> new PickaxeItem(Tiers.HAPPY, 3, 2.4f, getProps()));

    public static final RegistryObject<AxeItem> HAPPY_AXE = ITEMS.register("happy_axe", () -> new AxeItem(Tiers.HAPPY, 3, 2.4f, getProps()));

    public static final RegistryObject<ShovelItem> HAPPY_SHOVEL = ITEMS.register("happy_shovel", () -> new ShovelItem(Tiers.HAPPY, 3, 2.4f, getProps()));

    public static final RegistryObject<HoeItem> HAPPY_HOE = ITEMS.register("happy_hoe", () -> new HoeItem(Tiers.HAPPY, 3, 2.4f, getProps()));

    // Armor
    public static final RegistryObject<ArmorItem> HAPPY_HELMET = register("happy_helmet", () -> new ArmorItem(ArmorTiers.HAPPY, ArmorItem.Type.HELMET, getProps()));
    public static final RegistryObject<ArmorItem> HAPPY_CHESTPLATE = register("happy_chestplate", () -> new ArmorItem(ArmorTiers.HAPPY, ArmorItem.Type.CHESTPLATE, getProps()));
    public static final RegistryObject<ArmorItem> HAPPY_LEGGINGS = register("happy_leggings", () -> new ArmorItem(ArmorTiers.HAPPY, ArmorItem.Type.LEGGINGS, getProps()));
    public static final RegistryObject<ArmorItem> HAPPY_BOOTS = register("happy_boots", () -> new ArmorItem(ArmorTiers.HAPPY, ArmorItem.Type.BOOTS, getProps()));

    private static <T extends Item> RegistryObject<T> register(String name, Supplier<T> supplier) {
        return ITEMS.register(name, supplier);
    }
    private static RegistryObject<Item> register(String name) {
        return register(name, () -> new Item(getProps()));
    }

    public static Item.Properties getProps() {
        return new Item.Properties();
    }

    public static final class ArmorTiers {
        public static final ArmorMaterial HAPPY = new ModArmorMaterial(
                "happy",
                1000,
                new int[]{10, 16, 20, 10},
                50,
                SoundEvents.ARMOR_EQUIP_NETHERITE,
                10.0f,
                10.0f,
                () -> Ingredient.of(ItemInit.HAPPY_INGOT.get())
        );
    }

    public static class Tiers {
        public static final Tier HAPPY = new ForgeTier(
                5,
                2569,
                10.0f,
                10.0f,
                50,
                Tags.Blocks.NEEDS_NETHERITE_TOOL,
                () -> Ingredient.of(ItemInit.HAPPY_INGOT.get())
        );
    }
}
