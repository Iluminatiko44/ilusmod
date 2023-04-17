package io.github.Iluminatiko44.ilusmod.base;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.List;

public class ModItem extends Item implements ModTagsBase{
    @SafeVarargs
    public ModItem(Properties p_41383_, TagKey<? extends Item>... tags) {
        super(p_41383_);
        this.setTagKeys(List.of(tags));
    }
}
