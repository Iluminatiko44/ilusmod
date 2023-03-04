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
        handheldItem(ItemInit.HAPPY_SWORD);
        handheldItem(ItemInit.HAPPY_AXE);
        handheldItem(ItemInit.HAPPY_PICKAXE);
        handheldItem(ItemInit.HAPPY_SHOVEL);
        handheldItem(ItemInit.HAPPY_HOE);
    }

    @SuppressWarnings("UnusedReturnValue")
    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                        new ResourceLocation(Ilusmod.MODID, "item/" + item.getId().getPath()));
    }

    // I just learned that this is a thing! (<T extends Item>)
    @SuppressWarnings("UnusedReturnValue")
    private <T extends Item> ItemModelBuilder handheldItem(RegistryObject<T> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(Ilusmod.MODID, "item/" + item.getId().getPath()));
    }
}
