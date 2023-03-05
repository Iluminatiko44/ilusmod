package io.github.Iluminatiko44.ilusmod.datagen;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    private static final String id = Ilusmod.MODID;
    public ModLanguageProvider(PackOutput output, String locale) {
        super(output, id, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup.ilusmod", "Ilusmod");

        add("item."+id+".happy_ball", "Happy Ball");
        add("item."+id+".grapes", "Grapes");
        add("item."+id+".pommes", "Pommes");
        add("item."+id+".happy_ingot", "Happy Ingot");
        add("item."+id+".happy_sword", "Happy Sword");
        add("item."+id+".happy_axe", "Happy Axe");
        add("item."+id+".happy_pickaxe", "Happy Pickaxe");
        add("item."+id+".happy_shovel", "Happy Shovel");
        add("item."+id+".happy_hoe", "Happy Hoe");

        add("block."+id+".happy_block", "Happy Block");
        add("block."+id+".happy_ore", "Happy Ore");
        add("block."+id+".happy_ore_deepslate", "Deepslate Happy Ore");
        add("block."+id+".happy_ore_endstone", "Endstone Happy Ore");
        add("block."+id+".happy_ore_netherrack", "Nether Happy Ore");
        add("block."+id+".pseudo_ice", "Pseudo Ice");
        add("block."+id+".happy_log", "Happy Log");
        add("block."+id+".happy_wood", "Happy Wood");
        add("block."+id+".stripped_happy_log", "Stripped Happy Log");
        add("block."+id+".stripped_happy_wood", "Stripped Happy Wood");
        add("block."+id+".happy_leaves", "Happy Leaves");
        add("block."+id+".happy_sapling", "Happy Sapling");
        add("block."+id+".happy_planks", "Happy Planks");
    }
}
