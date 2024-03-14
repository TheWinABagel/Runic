package dev.bagel.runic.registry.entity;

import dev.bagel.runic.registry.RunicRegistry;
import dev.bagel.runic.spell.Spell;
import dev.bagel.runic.spell.modifiers.SpellModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.*;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class SpellProjectileEntity extends Projectile {
    private static final EntityDataAccessor<Spell> DATA_SPELL = SynchedEntityData.defineId(SpellProjectileEntity.class, RunicRegistry.EntityDataSerializers.SPELL_SERIALIZER_HOLDER.get());
    private static final EntityDataAccessor<List<SpellModifier>> DATA_SPELL_MODIFIERS = SynchedEntityData.defineId(SpellProjectileEntity.class, RunicRegistry.EntityDataSerializers.SPELL_MODIFIER_SERIALIZER_HOLDER.get());
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(SpellProjectileEntity.class, EntityDataSerializers.ITEM_STACK);

    public SpellProjectileEntity(Level pLevel, LivingEntity caster) {
        this(RunicRegistry.Entities.SPELL_PROJECTILE.get(), pLevel);
        this.setOwner(caster);
        this.setPos(caster.getX(), caster.getEyeY() - 0.1F, caster.getZ());
    }

    public SpellProjectileEntity(EntityType<? extends SpellProjectileEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData() {
        this.getEntityData().define(DATA_SPELL, RunicRegistry.Spells.EMPTY.get());
        this.getEntityData().define(DATA_SPELL_MODIFIERS, new ArrayList<>());
        this.getEntityData().define(DATA_ITEM_STACK, ItemStack.EMPTY);

    }

    @Override
    public void lerpTo(double pX, double pY, double pZ, float pYRot, float pXRot, int pSteps) {
        this.setPos(pX, pY, pZ);
        this.setRot(pYRot, pXRot);
    }

    @Override
    public void tick() {
        super.tick();
        boolean flag = false; //this.isNoPhysics();
        Vec3 vec3 = this.getDeltaMovement();
        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            double d0 = vec3.horizontalDistance();
            this.setYRot((float)(Mth.atan2(vec3.x, vec3.z) * 180.0F / (float)Math.PI));
            this.setXRot((float)(Mth.atan2(vec3.y, d0) * 180.0F / (float)Math.PI));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }

        BlockPos blockpos = this.blockPosition();
        BlockState blockstate = this.level().getBlockState(blockpos);
        if (!blockstate.isAir() && !flag) {
            VoxelShape voxelshape = blockstate.getCollisionShape(this.level(), blockpos);
            if (!voxelshape.isEmpty()) {
                Vec3 vec31 = this.position();

                for(AABB aabb : voxelshape.toAabbs()) {
                    if (aabb.move(blockpos).contains(vec31)) {
                        break;
                    }
                }
            }
        }

        if (this.isInWaterOrRain() || blockstate.is(Blocks.POWDER_SNOW) || this.isInFluidType((fluidType, height) -> this.canFluidExtinguish(fluidType))) {
            this.clearFire();
        }

            Vec3 vec32 = this.position();
            Vec3 vec33 = vec32.add(vec3);
            HitResult hitresult = this.level().clip(new ClipContext(vec32, vec33, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
            if (hitresult.getType() != HitResult.Type.MISS) {
                vec33 = hitresult.getLocation();
            }

            while(!this.isRemoved()) {
                EntityHitResult entityhitresult = this.findHitEntity(vec32, vec33);
                if (entityhitresult != null) {
                    hitresult = entityhitresult;
                }

                if (hitresult != null && hitresult.getType() == HitResult.Type.ENTITY) {
                    Entity entity = ((EntityHitResult)hitresult).getEntity();
                    Entity entity1 = this.getOwner();
                    if (entity instanceof Player && entity1 instanceof Player && !((Player)entity1).canHarmPlayer((Player)entity)) {
                        hitresult = null;
                        entityhitresult = null;
                    }
                }

                if (hitresult != null && hitresult.getType() != HitResult.Type.MISS && !flag) {
                    if (net.neoforged.neoforge.event.EventHooks.onProjectileImpact(this, hitresult))
                        break;
                    this.onHit(hitresult);
                    this.hasImpulse = true;
                }

                if (entityhitresult == null) {
                    break;
                }

                hitresult = null;
            }

            vec3 = this.getDeltaMovement();
            double d5 = vec3.x;
            double d6 = vec3.y;
            double d1 = vec3.z;

            double d7 = this.getX() + d5;
            double d2 = this.getY() + d6;
            double d3 = this.getZ() + d1;
            double d4 = vec3.horizontalDistance();
            if (flag) {
                this.setYRot((float)(Mth.atan2(-d5, -d1) * 180.0F / (float)Math.PI));
            } else {
                this.setYRot((float)(Mth.atan2(d5, d1) * 180.0F / (float)Math.PI));
            }

            this.setXRot((float)(Mth.atan2(d6, d4) * 180.0F / (float)Math.PI));
            this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
            this.setYRot(lerpRotation(this.yRotO, this.getYRot()));
            float f = 0.99F;

            this.setDeltaMovement(vec3.scale((double)f));
            if (!this.isNoGravity() && !flag) {
                Vec3 vec34 = this.getDeltaMovement();
                this.setDeltaMovement(vec34.x, vec34.y - 0.05F, vec34.z);
            }

            this.setPos(d7, d2, d3);
            this.checkInsideBlocks();


    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (getOwner() instanceof Player player) {
//            getSpell().onHitEntity(this.level(), player, result.getEntity(), getStack(), getSpell());
        }

    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        super.onHitBlock(result);
        if (getOwner() instanceof Player player) {
//            getSpell().onHitBlock(this.level(), player, result, getStack(), getSpell());
        }
    }

    @Nullable
    protected EntityHitResult findHitEntity(Vec3 pStartVec, Vec3 pEndVec) {
        return ProjectileUtil.getEntityHitResult(
                this.level(), this, pStartVec, pEndVec, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0), this::canHitEntity
        );
    }


    public void setStack(ItemStack stack) {
        this.getEntityData().set(DATA_ITEM_STACK, stack.copyWithCount(1));
    }

    public void setSpell(Spell spell) {
        this.getEntityData().set(DATA_SPELL, spell);
    }

    public void setModifiers(List<SpellModifier> modifiers) {
        this.getEntityData().set(DATA_SPELL_MODIFIERS, List.copyOf(modifiers));
    }

    public Spell getSpell() {
        return this.getEntityData().get(DATA_SPELL);
    }

    public ItemStack getStack() {
        return this.getEntityData().get(DATA_ITEM_STACK);
    }

    public List<SpellModifier> getModifiers() {
        return new ArrayList<>(this.getEntityData().get(DATA_SPELL_MODIFIERS));
    }
}
