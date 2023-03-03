package io.github.Iluminatiko44.ilusmod.datagen;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.BlockInit;
import io.github.Iluminatiko44.ilusmod.Init.ItemInit;
import net.minecraft.client.tutorial.Tutorial;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {


    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        // Happy Block to Happy Ingot
        oreSmelting(consumer, List.of(BlockInit.HAPPY_BLOCK.get()),
                RecipeCategory.MISC, ItemInit.HAPPY_INGOT.get(), 0.7f, 200, "happy_ball");
        // Happy Ore Blocks to Happy Ingot
        // 1: consumer, 2: List of items to input for one output 3: recipe category, 4: output, 5: xp, 6: time, 7: group (?)
        oreSmelting(consumer, List.of(BlockInit.HAPPY_ORE.get(), BlockInit.DEEPSLATE_HAPPY_ORE.get(), BlockInit.ENDSTONE_HAPPY_ORE.get(), BlockInit.NETHERRACK_HAPPY_ORE.get()),
                RecipeCategory.MISC, ItemInit.HAPPY_INGOT.get(), 0.7f, 200, "happy_ball");
        // Happy Balls to Happy Block, 1: consumer, 2: recipe category, 3: output, 4: input
        twoByTwoPacker(consumer, RecipeCategory.MISC, BlockInit.HAPPY_BLOCK.get(), ItemInit.HAPPY_BALL.get());
        // Happy Block to Happy Balls
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ItemInit.HAPPY_BALL.get(), 4)
                .requires(BlockInit.HAPPY_BLOCK.get())
                .unlockedBy(getHasName(BlockInit.HAPPY_BLOCK.get())
                        , has(BlockInit.HAPPY_BLOCK.get()))
                .save(consumer);
        // Happy Ingot to Happy Sword 1: output, 2: material, 3: consumer
        swordWStickBuilding(ItemInit.HAPPY_SWORD.get(), ItemInit.HAPPY_INGOT.get(), consumer);
        pickaxeWStickBuilding(ItemInit.HAPPY_PICKAXE.get(), ItemInit.HAPPY_INGOT.get(), consumer);
        axeWStickBuilding(ItemInit.HAPPY_AXE.get(), ItemInit.HAPPY_INGOT.get(), consumer);
        shovelWStickBuilding(ItemInit.HAPPY_SHOVEL.get(), ItemInit.HAPPY_INGOT.get(), consumer);
        hoeWStickBuilding(ItemInit.HAPPY_HOE.get(), ItemInit.HAPPY_INGOT.get(), consumer);
    }


    // This has to be here, so the data generates in the right place (It would be under the "minecraft" modid otherwise)
    // Yes, this is just copied
    @SuppressWarnings({"SameParameterValue", "NullableProblems"})
    protected static void twoByTwoPacker(Consumer<FinishedRecipe> p_248860_, RecipeCategory p_250881_, ItemLike p_252184_, ItemLike p_249710_) {
        ShapedRecipeBuilder.shaped(p_250881_, p_252184_, 1).define('#', p_249710_).pattern("##").pattern("##").unlockedBy(getHasName(p_249710_), has(p_249710_)).save(p_248860_);
    }
    @SuppressWarnings("NullableProblems")
    protected static void oreSmelting(Consumer<FinishedRecipe> p_250654_, List<ItemLike> p_250172_, RecipeCategory p_250588_, ItemLike p_251868_, float p_250789_, int p_252144_, String p_251687_) {
        oreCooking(p_250654_, RecipeSerializer.SMELTING_RECIPE, p_250172_, p_250588_, p_251868_, p_250789_, p_252144_, p_251687_, "_from_smelting");
    }
    @SuppressWarnings("NullableProblems")
    protected static void oreCooking(Consumer<FinishedRecipe> p_250791_, RecipeSerializer<? extends AbstractCookingRecipe> p_251817_, List<ItemLike> p_249619_, RecipeCategory p_251154_, ItemLike p_250066_, float p_251871_, int p_251316_, String p_251450_, String p_249236_) {
        for(ItemLike itemlike : p_249619_) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), p_251154_, p_250066_, p_251871_, p_251316_, p_251817_).group(p_251450_).unlockedBy(getHasName(itemlike), has(itemlike)).save(p_250791_, new ResourceLocation(Ilusmod.MODID, getItemName(p_250066_)) + p_249236_ + "_" + getItemName(itemlike));
        }

    }

    // Own helper methods
    @SuppressWarnings("SameParameterValue")
    private static void swordBuilding(ItemLike output, Item material, ItemLike handle, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output)
                .define('M', material)
                .define('H', handle)
                .pattern("M")
                .pattern("M")
                .pattern("H")
                .unlockedBy("has_item",has(material))
                .save(consumer);
    }
    private static void swordWStickBuilding(ItemLike output, Item material, Consumer<FinishedRecipe> consumer) {
        swordBuilding(output, material, Items.STICK, consumer);
    }
    @SuppressWarnings("SameParameterValue")
    private static void pickaxeBuilding(ItemLike output, Item material, ItemLike handle, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output)
                .define('M', material)
                .define('H', handle)
                .pattern("MMM")
                .pattern(" H ")
                .pattern(" H ")
                .unlockedBy("has_item",has(material))
                .save(consumer);
    }
    private static void pickaxeWStickBuilding(ItemLike output, Item material, Consumer<FinishedRecipe> consumer) {
        pickaxeBuilding(output, material, Items.STICK, consumer);
    }
    @SuppressWarnings("SameParameterValue")
    private static void axeBuilding(ItemLike output, Item material, ItemLike handle, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output)
                .define('M', material)
                .define('H', handle)
                .pattern("MM")
                .pattern("MH")
                .pattern(" H")
                .unlockedBy("has_item",has(material))
                .save(consumer);
    }
    private static void axeWStickBuilding(ItemLike output, Item material, Consumer<FinishedRecipe> consumer) {
        axeBuilding(output, material, Items.STICK, consumer);
    }
    @SuppressWarnings("SameParameterValue")
    private static void shovelBuilding(ItemLike output, Item material, ItemLike handle, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output)
                .define('M', material)
                .define('H', handle)
                .pattern("M")
                .pattern("H")
                .pattern("H")
                .unlockedBy("has_item",has(material))
                .save(consumer);
    }
    private static void shovelWStickBuilding(ItemLike output, Item material, Consumer<FinishedRecipe> consumer) {
        shovelBuilding(output, material, Items.STICK, consumer);
    }
    @SuppressWarnings("SameParameterValue")
    private static void hoeBuilding(ItemLike output, Item material, ItemLike handle, Consumer<FinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, output)
                .define('M', material)
                .define('H', handle)
                .pattern("MM")
                .pattern(" H")
                .pattern(" H")
                .unlockedBy("has_item",has(material))
                .save(consumer);
    }
    private static void hoeWStickBuilding(ItemLike output, Item material, Consumer<FinishedRecipe> consumer) {
        hoeBuilding(output, material, Items.STICK, consumer);
    }
}
