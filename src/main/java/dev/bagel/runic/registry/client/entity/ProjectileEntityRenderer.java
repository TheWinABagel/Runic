package dev.bagel.runic.registry.client.entity;

import dev.bagel.runic.registry.entity.SpellProjectileEntity;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ProjectileEntityRenderer extends EntityRenderer<SpellProjectileEntity> {
    public ProjectileEntityRenderer(EntityRendererProvider.Context pContext) {
        super(pContext);
    }

    @Override
    public ResourceLocation getTextureLocation(SpellProjectileEntity pEntity) {
        return new ResourceLocation("textures/entity/projectiles/spectral_arrow.png");
    }
}
