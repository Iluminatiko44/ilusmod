package io.github.Iluminatiko44.ilusmod.base;

import io.github.Iluminatiko44.ilusmod.Ilusmod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public record ModArmorMaterial(String name, int durability, int[] protection, int enchantability, SoundEvent sound, float toughness, float knockbackResistance, Supplier <Ingredient> repairMaterial) implements ArmorMaterial {
                                                            // From bottom to top
    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return HEALTH_PER_SLOT[type.getSlot().getIndex()] * this.durability;
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return this.protection[type.getSlot().getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public @NotNull SoundEvent getEquipSound() {
        return this.sound;
    }

    @Override
    public @NotNull Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }
    @SuppressWarnings("unused")
    public String getClearName() {
        return this.name;
    }

    @Override
    public @NotNull String getName() {
        return Ilusmod.MODID + ":" + this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}
