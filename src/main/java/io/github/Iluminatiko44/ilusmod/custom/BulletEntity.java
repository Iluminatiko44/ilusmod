package io.github.Iluminatiko44.ilusmod.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class BulletEntity extends Arrow {
    public BulletEntity(Level p_36858_, LivingEntity p_36859_) {
        super(p_36858_, p_36859_);
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult p_36861_) {
        explode(this.level, this.getX(), this.getY(), this.getZ(), (LivingEntity) this.getOwner());
        super.onHitBlock(p_36861_);
        this.remove(RemovalReason.DISCARDED);
    }
    @Override
    public void onHitEntity(@NotNull EntityHitResult p_36863_) {
        explode(this.level, this.getX(), this.getY(), this.getZ(), (LivingEntity) this.getOwner());
        super.onHitEntity(p_36863_);
        this.remove(RemovalReason.DISCARDED);
    }

    private static void explode(Level level, double x, double y, double z, LivingEntity owner) {
                        // smt with mob explosion; x-, y- and z- Coordinates; explosion radius; ExplosionType. In this case TNT, because it's easy
        level.explode(null, x, y, z, 15.0F, Level.ExplosionInteraction.TNT);

    }
}
