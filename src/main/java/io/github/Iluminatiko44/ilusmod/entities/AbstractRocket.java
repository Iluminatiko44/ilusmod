package io.github.Iluminatiko44.ilusmod.entities;

import com.mojang.logging.LogUtils;
import io.github.Iluminatiko44.ilusmod.Init.EntityInit;
import io.github.Iluminatiko44.ilusmod.base.ModTagsBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

import java.util.List;

public abstract class AbstractRocket extends Projectile implements ModTagsBase {
    private static final Logger LOGGER = LogUtils.getLogger();
    private static final EntityDataAccessor<Byte> ID_FLAGS = SynchedEntityData.defineId(AbstractRocket.class, EntityDataSerializers.BYTE);
    private double acceleration;
    private float expoRad;
    private boolean inGround;
    @SafeVarargs
    public AbstractRocket(EntityType<? extends AbstractRocket> p_37411_, Level p_37412_, TagKey<? extends EntityType<?>>... tags) {
        super(p_37411_, p_37412_);
        this.setTagKeys(List.of(tags));
    }
    public AbstractRocket(EntityType<? extends AbstractRocket>entityType, Level level, LivingEntity owner, float explosionRadius, double acceleration) {
        this(entityType, level);
        this.setPos(owner.getX(), owner.getEyeY() - 0.1D, owner.getZ());
        this.setOwner(owner);
        this.setNoGravity(true);
        this.expoRad = explosionRadius;
        this.acceleration = acceleration;
    }

    @Override
    protected void onHit(@NotNull HitResult p_37260_) {
        explodeAt(this);
        //super.onHit(p_37260_);
        this.discard();
    }
    private static void explodeAt(@NotNull AbstractRocket projectile) {
        projectile.getLevel().explode(
                projectile, // the projectile itself, because the explosion shouldn't damage its host. It also gets the owner from this.
                projectile.getX(), projectile.getY(), projectile.getZ(), // x-, y- and z- Coordinates;
                projectile.getExplosionRadius(), // Explosion radius;
                Level.ExplosionInteraction.TNT // ExplosionType. In this case TNT, because it's easy
        );
    }

    public void tick() {
        if (LOGGER.isDebugEnabled() && this.firstTick && !this.level.isClientSide()) {
            LOGGER.debug("RocketEntity spawned. Current Position: " + this.position());
        }


        if (this.level.isClientSide && !this.inGround && !this.firstTick)
            this.level.addParticle(ParticleTypes.LAVA, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);

        super.tick();

        this.checkGround();

        Vec3 delMov = this.getDeltaMovement();
        Vec3 pos = this.position();
        if (!this.level.isClientSide()) {

            //if(LOGGER.isDebugEnabled() && this.tickCount % 20 <= 4) LOGGER.debug(this.tickCount % 20 + ": RocketEntity ticked. Current Movement: " + delMov);

            // This is the important line
            this.setDeltaMovement(delMov.scale(acceleration));

            //if(LOGGER.isDebugEnabled() && this.tickCount % 20 <= 4) LOGGER.debug(this.tickCount % 20 + ": RocketEntity ticked. Changed Movement: " + this.getDeltaMovement());
            Player player = this.level.getNearestPlayer(this, 5.0D);
            /*i f(player != null) {
                this.lookAt(EntityAnchorArgument.Anchor.FEET, player.position());
                //this.setRo
                //this.moveTo(player.position().add(random.nextGaussian(), random.nextGaussian(), random.nextGaussian()));
            }*/
            //else
            //this.setRot(this.getYRot()+1.0F, this.getXRot()+1.0F);

            delMov = this.getDeltaMovement();
            Vec3 newPos = pos.add(delMov);
            this.checkHit(pos, newPos);
            this.setNormalRotation(delMov);

            newPos = pos.add(delMov);
            this.setPos(newPos);

            // This has to be set, otherwise the rocketEntity won't update the position for the client
            this.hasImpulse = true;
        }

        this.checkInsideBlocks();
    }

    private void checkGround() {
        BlockPos blockpos = this.blockPosition();
        BlockState blockstate = this.level.getBlockState(blockpos);
        if (!blockstate.isAir()) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.level, blockpos);
            if (!voxelshape.isEmpty()) {
                Vec3 vec31 = this.position();

                for(AABB aabb : voxelshape.toAabbs()) {
                    if (aabb.move(blockpos).contains(vec31)) {
                        this.inGround = true;
                        break;
                    }
                }
            }
        }
    }

    private void checkHit(Vec3 oldPos, Vec3 newPos) {
        HitResult hitresult = this.level.clip(new ClipContext(oldPos, newPos, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (hitresult.getType() != HitResult.Type.MISS) {
            newPos = hitresult.getLocation();
        }
        // IDK why we need a while-loop here
        while(!this.isRemoved()) {
            EntityHitResult entityhitresult = ProjectileUtil.getEntityHitResult(this.level, this, oldPos, newPos, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), this::canHitEntity);
            // Check if the hitResult is an entity
            if (entityhitresult != null) {
                hitresult = entityhitresult;
            }
            // Check if we can hurt the player
            if (hitresult != null && hitresult.getType() == HitResult.Type.ENTITY) {
                @SuppressWarnings("ConstantConditions")
                Entity entity = ((EntityHitResult)hitresult).getEntity();
                Entity entity1 = this.getOwner();
                if (entity instanceof Player && entity1 instanceof Player && !((Player)entity1).canHarmPlayer((Player)entity)) {
                    hitresult = null;
                    entityhitresult = null;
                }
            }

            if (hitresult != null && hitresult.getType() != HitResult.Type.MISS) {
                if (net.minecraftforge.event.ForgeEventFactory.onProjectileImpact(this, hitresult))
                    break;
                this.onHit(hitresult);
                this.hasImpulse = true;
            }

            if (entityhitresult == null) {
                break;
            }

            hitresult = null;
        }
    }

    public void setNormalRotation(Vec3 movement) {
        this.setYRot((float)(Mth.atan2(movement.x, movement.z) * (double)(180F / (float)Math.PI)));
        this.setXRot((float)(Mth.atan2(movement.y, movement.horizontalDistance()/*sqrt(x^2+z^2)*/ ) * (double)(180F / (float)Math.PI)));
        this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
        this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
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



    public static class Rocket extends AbstractRocket {
        public Rocket(EntityType<? extends AbstractRocket> entityType, Level level) {
            super(entityType, level);
        }
        public Rocket(Level level, LivingEntity owner) {
            super(EntityInit.ROCKET.get(), level, owner, 7.0F, 1.2D);
        }
    }
    public static class ExplosiveRocket extends AbstractRocket {
        public ExplosiveRocket(Level level, LivingEntity owner) {
            super(EntityInit.EXPLOSIVE_ROCKET.get(), level, owner, 10.0F, 1.1D);
        }
        @SafeVarargs
        public ExplosiveRocket(EntityType<? extends AbstractRocket> entityType, Level level, TagKey<EntityType<?>>... tags) {
            super(entityType, level, tags);
        }

    }
    public static class Nuke extends AbstractRocket {
        @SafeVarargs
        public Nuke(EntityType<? extends AbstractRocket> entityType, Level level, TagKey<EntityType<?>>... tags) {
            super(entityType, level, tags);
        }
        public Nuke(Level level, LivingEntity owner) {
            super(EntityInit.NUKE.get(), level, owner, 25.0F, 1.01D);
        }
    }

}
