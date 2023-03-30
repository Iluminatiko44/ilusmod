package io.github.Iluminatiko44.ilusmod.custom;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import org.jetbrains.annotations.NotNull;

public class BulletEntity extends Arrow {
    private final float expoRad;
    public BulletEntity(Level p_36858_, LivingEntity p_36859_, float explosionRadius) {
        super(p_36858_, p_36859_);
        this.expoRad = explosionRadius;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult p_36861_) {
        explode(this);
        super.onHitBlock(p_36861_);
        this.remove(RemovalReason.DISCARDED);
    }
    @Override
    public void onHitEntity(@NotNull EntityHitResult p_36863_) {
        explode(this);
        super.onHitEntity(p_36863_);
        this.remove(RemovalReason.DISCARDED);
    }

    private static void explode(BulletEntity projectile) {

        projectile.getLevel().explode(
                projectile, // the projectile itself, because the explosion shouldn't damage its host. It also gets the owner from this.
                projectile.getX(), projectile.getY(), projectile.getZ(), // x-, y- and z- Coordinates;
                projectile.getExplosionRadius(), // Explosion radius;
                Level.ExplosionInteraction.TNT // ExplosionType. In this case TNT, because it's easy
        );
    }

    public float getExplosionRadius() {
        return expoRad;
    }
}
