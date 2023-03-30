package io.github.Iluminatiko44.ilusmod.base;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModItemBase extends net.minecraft.world.item.Item {

    private final List<TagKey<Item>> tags = new ArrayList<>();

    @SafeVarargs
    public ModItemBase(Properties properties, TagKey<Item>... tags) {
        super(properties);
        this.tags.addAll(Arrays.asList(tags));
    }

    public List<TagKey<Item>> getTags() {
        return tags;
    }
}
