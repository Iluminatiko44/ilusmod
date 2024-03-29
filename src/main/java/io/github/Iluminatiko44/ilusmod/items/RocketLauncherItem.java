package io.github.Iluminatiko44.ilusmod.items;

import com.mojang.logging.LogUtils;
import io.github.Iluminatiko44.ilusmod.base.ModTagsBase;
import io.github.Iluminatiko44.ilusmod.datagen.ModTagsProvider;
import io.github.Iluminatiko44.ilusmod.entities.AbstractRocket;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileWeaponItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.List;
import java.util.function.Predicate;

public class RocketLauncherItem extends ProjectileWeaponItem implements ModTagsBase {
    private static final Logger LOGGER = LogUtils.getLogger();
    @SafeVarargs
    public RocketLauncherItem(Properties properties, TagKey<Item>... tags) {
        super(properties);
        this.setTagKeys(List.of(tags));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand); // Get the item which is fired from this hand
        boolean hasNoAmmo = player.getProjectile(itemstack).isEmpty();

        if (!level.isClientSide()) {
            //LOGGER.debug("RocketLauncherItem.use() on Client called");
            AbstractRocket rocket = new AbstractRocket.Nuke(level, player);
            //LOGGER.debug("New Nuke declared");
            rocket.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 1.0F);
            //LOGGER.debug("Nuke shot");
            level.addFreshEntity(rocket);
            //LOGGER.debug("Nuke added to level");
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean canAttackBlock(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player) {
        return false;
    }

    @Override
    public @NotNull Predicate<ItemStack> getAllSupportedProjectiles() {
        return itemStack -> itemStack.is(ModTagsProvider.ItemTagsProvider.ROCKETS);
    }

    @Override
    public int getDefaultProjectileRange() {
        return 1;
    }

}
