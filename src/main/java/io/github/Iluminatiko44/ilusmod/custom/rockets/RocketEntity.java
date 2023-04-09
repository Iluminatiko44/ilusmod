package io.github.Iluminatiko44.ilusmod.custom.rockets;

import com.mojang.logging.LogUtils;
import io.github.Iluminatiko44.ilusmod.Init.EntityInit;
import io.github.Iluminatiko44.ilusmod.base.ModTagsBase;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.List;

public abstract class RocketEntity extends Projectile implements ModTagsBase {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(RocketEntity.class, EntityDataSerializers.BYTE);
    private final double acceleration;
    private final float expoRad;
    private boolean inGround;
    @SafeVarargs
    public RocketEntity(EntityType<? extends RocketEntity> p_37411_, Level p_37412_, TagKey<? extends EntityType<?>>... tags) {
        this(p_37411_, p_37412_, null, 0.0F, 1.0D);

        this.setTagKeys(List.of(tags));
    }
    public RocketEntity(EntityType<? extends RocketEntity>entityType, Level level, LivingEntity owner, float explosionRadius, double acceleration) {
        super(entityType,level);
        this.setOwner(owner);
        this.setNoGravity(true);
        this.expoRad = explosionRadius;
        this.acceleration = acceleration;
    }

    @Override
    protected void onHitBlock(@NotNull BlockHitResult blockHitResult) {
        explode(this);
        super.onHitBlock(blockHitResult);
        inGround = true;
        this.discard();
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
    @Override
    protected void defineSynchedData() {
        this.entityData.define(ID_FLAGS, (byte)0);
    }

    public static class Rocket extends RocketEntity {
        @SafeVarargs
        public Rocket(EntityType<? extends RocketEntity> entityType, Level level, TagKey<EntityType<?>>... tags) {
            super(entityType, level, tags);
        }
        public Rocket(Level level, LivingEntity owner) {
            super(EntityInit.ROCKET.get(), level, owner, 7.0F, 1.2D);
        }
    }
    public static class ExplosiveRocket extends RocketEntity {
        public ExplosiveRocket(Level level, LivingEntity owner) {
            super(EntityInit.EXPLOSIVE_ROCKET.get(), level, owner, 10.0F, 1.1D);
        }
        @SafeVarargs
        public ExplosiveRocket(EntityType<? extends RocketEntity> entityType, Level level, TagKey<EntityType<?>>... tags) {
            super(entityType, level, tags);
        }

    }
    public static class Nuke extends RocketEntity {
        @SafeVarargs
        public Nuke(EntityType<? extends RocketEntity> entityType, Level level, TagKey<EntityType<?>>... tags) {
            super(entityType, level, tags);
        }
        public Nuke(Level level, LivingEntity owner) {
            super(EntityInit.NUKE_ROCKET.get(), level, owner, 25.0F, 1.01D);
        }
    }

}
