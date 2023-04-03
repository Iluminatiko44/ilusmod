package io.github.Iluminatiko44.ilusmod.custom.rockets;

import io.github.Iluminatiko44.ilusmod.base.ModItemBase;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class RocketLauncherItem extends Item implements ModItemBase {

    @SafeVarargs
    public RocketLauncherItem(Properties properties, TagKey<Item>... tags) {
        super(properties);
        this.tags.addAll(Arrays.asList(tags));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide()) {
            RocketEntity rocket = new RocketEntity(level, player, 15.0F, 1.2D);
            rocket.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.0F, 1.0F);
            level.addFreshEntity(rocket);
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean canAttackBlock(@NotNull BlockState blockState, @NotNull Level level, @NotNull BlockPos blockPos, @NotNull Player player) {
        return false;
    }
}
