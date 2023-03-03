package io.github.Iluminatiko44.ilusmod.datagen;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import io.github.Iluminatiko44.ilusmod.Init.ItemInit;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Ilusmod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ItemInit.HAPPY_BALL);
        simpleItem(ItemInit.GRAPES);
        simpleItem(ItemInit.POMMES);
        simpleItem(ItemInit.HAPPY_INGOT);
        swordHandheldItem(ItemInit.HAPPY_SWORD);
        axeHandheldItem(ItemInit.HAPPY_AXE);
        pickaxeHandheldItem(ItemInit.HAPPY_PICKAXE);
        shovelHandheldItem(ItemInit.HAPPY_SHOVEL);
        hoeHandheldItem(ItemInit.HAPPY_HOE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                        new ResourceLocation(Ilusmod.MODID, "item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Ilusmod.MODID, "item/" + item.getId().getPath()));
    }
    // I don't know why, but it doesn't work with the Item class
    //
    private ItemModelBuilder swordHandheldItem(RegistryObject<SwordItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Ilusmod.MODID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder axeHandheldItem(RegistryObject<AxeItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Ilusmod.MODID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder pickaxeHandheldItem(RegistryObject<PickaxeItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Ilusmod.MODID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder shovelHandheldItem(RegistryObject<ShovelItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Ilusmod.MODID, "item/" + item.getId().getPath()));
    }
    private ItemModelBuilder hoeHandheldItem(RegistryObject<HoeItem> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Ilusmod.MODID, "item/" + item.getId().getPath()));
    }
}
