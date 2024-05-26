package virtuoel.pehkui.mixin.client.compat118minus.compat115plus;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.client.particle.ItemPickupParticle;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.entity.Entity;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(ItemPickupParticle.class)
public class ItemPickupParticleMixin
{
	@Shadow @Final @Mutable Entity interactingEntity;
	
	@ModifyExpressionValue(method = "buildGeometry", at = @At(value = "CONSTANT", args = "doubleValue=0.5D"))
	private double pehkui$buildGeometry$offset(double value, VertexConsumer vertexConsumer, Camera camera, float tickDelta)
	{
		final float scale = ScaleUtils.getEyeHeightScale(interactingEntity, tickDelta);
		
		if (scale != 1.0F)
		{
			return value * scale;
		}
		
		return value;
	}
}
