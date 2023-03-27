package io.github.Iluminatiko44.ilusmod.custom;

import io.github.Iluminatiko44.ilusmod.base.IlusItem;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class MachineGunItem extends IlusItem {

    @SafeVarargs
    public MachineGunItem(Properties properties, TagKey<Item>... tags) {
        super(properties, tags);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand) {
        if (!level.isClientSide()) {
            BulletEntity arrow = new BulletEntity(level, player);
            arrow.setBaseDamage(arrow.getBaseDamage() + 2.0D);
            arrow.setKnockback(0);
            arrow.pickup = AbstractArrow.Pickup.DISALLOWED;
            arrow.setNoGravity(true);                                           // Offset, Power, Spread
            arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 2.5F, 1.0F);
            level.addFreshEntity(arrow);
        }
        return super.use(level, player, hand);
    }
}
