package io.github.Iluminatiko44.ilusmod.custom.rockets;

import com.mojang.logging.LogUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class RocketEntity extends Arrow {
    private static final Logger LOGGER = LogUtils.getLogger();
    private final double acceleration;
    private final float expoRad;
    public RocketEntity(EntityType<? extends RocketEntity> p_37411_, Level p_37412_) {
        super(p_37411_, p_37412_);
        this.setNoGravity(true);
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
        this.acceleration = 1.2D;
        this.expoRad = 15.0F;
    }
    public RocketEntity(Level level, LivingEntity owner, float explosionRadius, double acceleration) {
        super(level, owner);
        this.expoRad = explosionRadius;
        this.setNoGravity(true);
        this.pickup = AbstractArrow.Pickup.DISALLOWED;
        this.acceleration = acceleration;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        explode(this);
        super.onHitBlock(blockHitResult);
        this.remove(RemovalReason.DISCARDED);
    }
    @Override
    public void onHitEntity(@NotNull EntityHitResult entityHitResult) {
        explode(this);
        super.onHitEntity(entityHitResult);
        this.discard();
    }

    private static void explode(@NotNull RocketEntity projectile) {
        projectile.getLevel().explode(
                projectile, // the projectile itself, because the explosion shouldn't damage its host. It also gets the owner from this.
                projectile.getX(), projectile.getY(), projectile.getZ(), // x-, y- and z- Coordinates;
                projectile.getExplosionRadius(), // Explosion radius;
                Level.ExplosionInteraction.TNT // ExplosionType. In this case TNT, because it's easy
        );
    }

    public void tick() {
        if(LOGGER.isDebugEnabled() && this.firstTick && !this.level.isClientSide() ) {
            LOGGER.info("RocketEntity spawned. Current Position: " + this.position());
        }

        super.tick();

        if(!this.level.isClientSide()) {
            Vec3 delMov = this.getDeltaMovement();
            if(LOGGER.isDebugEnabled() && this.tickCount % 20 <= 4) LOGGER.debug(this.tickCount % 20 + ": RocketEntity ticked. Current Movement: " + delMov);

            // This is the important line
            this.setDeltaMovement(delMov.multiply(acceleration, acceleration, acceleration));

            if(LOGGER.isDebugEnabled() && this.tickCount % 20 <= 4) LOGGER.debug(this.tickCount % 20 + ": RocketEntity ticked. Changed Movement: " + this.getDeltaMovement());
            // This has to be set, otherwise the rocketEntity won't update the position for the client
            this.hasImpulse = true;
        }

        if (this.level.isClientSide && !this.inGround)
            this.level.addParticle(ParticleTypes.LAVA, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
    }

    // This is here to unscramble the mojang mappings
    @Override
    public void shootFromRotation(@NotNull Entity entity, float xRotation, float yRotation, float yOffset, float power, float spread) {
        super.shootFromRotation(entity, xRotation, yRotation, yOffset, power, spread);
    }

    public float getExplosionRadius() {
        return expoRad;
    }

    public static class NukeEntity extends RocketEntity {
        public NukeEntity(EntityType<? extends RocketEntity> p_37411_, Level p_37412_) {
            super(p_37411_, p_37412_);
        }
    }

}
