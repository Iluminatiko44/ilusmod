package io.github.Iluminatiko44.ilusmod.base;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.List;

public interface ModItemBase {

    List<TagKey<Item>> tags = new ArrayList<>();

    List<TagKey<Item>> getTags();
}
