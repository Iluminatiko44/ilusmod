package io.github.Iluminatiko44.ilusmod.custom;

import io.github.Iluminatiko44.ilusmod.base.ModItemBase;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

public class RocketLauncherItem extends Item implements ModItemBase {

    @SafeVarargs
    public RocketLauncherItem(Properties properties, TagKey<Item>... tags) {
        super(properties);
        this.tags.addAll(Arrays.asList(tags));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide()) {
            RocketEntity arrow = new RocketEntity(level, player, 15.0F);
            arrow.setBaseDamage(arrow.getBaseDamage() + 2.0D);
            arrow.setKnockback(0);
            arrow.pickup = AbstractArrow.Pickup.DISALLOWED;
            arrow.setNoGravity(true);                                           // Offset, Power, Spread
            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 10.0F, 1.0F);
            level.addFreshEntity(arrow);
        }
        return super.use(level, player, hand);
    }

    @Override
    public List<TagKey<Item>> getTags() {
        return this.tags;
    }
}
