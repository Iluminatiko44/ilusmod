package io.github.Iluminatiko44.ilusmod.base;

import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.ArrayList;
import java.util.List;

public interface ModBlockBase {
    List<TagKey<Block>> tags = new ArrayList<>();

    default List<TagKey<Block>> getTags() {return this.tags;}
}
