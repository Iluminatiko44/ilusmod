package io.github.Iluminatiko44.ilusmod.base;

import net.minecraft.tags.TagKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * This interface is used to add tags to blocks and items when they are declared.
 * @author Iluminatiko
 * @since 1.0.0
 */
public interface ModTagsBase {
    // Had to use a HashMap, because fields are always static in interfaces
    // First String is the class name, second is the list of tags
    HashMap<String, List<TagKey<?>>> tags = new HashMap<>();
    default void setTagKeys(List<TagKey<?>> tagKeys) {this.tags.put(this.getClass().getSimpleName(), tagKeys);}
    default List<TagKey<?>> getTagKeys() {return this.tags.get(this.getClass().getSimpleName());}

    default void addTags(List<TagKey<?>> tag) {this.tags.put(this.getClass().getSimpleName(), tag);}
    default void addTag(TagKey<?> tag) {this.tags.put(this.getClass().getSimpleName(), List.of(tag));}

}
