package virtuoel.pehkui.mixin.reach.compat.client;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.hit.HitResult;
import virtuoel.pehkui.util.ReachEntityAttributesCompatibility;
import virtuoel.pehkui.util.ScaleRenderUtils;

@Mixin(value = GameRenderer.class, priority = 990)
public class GameRendererMixin
{
	@Shadow @Final @Mutable
	MinecraftClient client;
	
	@ModifyVariable(method = "updateCrosshairTarget", ordinal = 0, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getCameraPosVec(F)Lnet/minecraft/util/math/Vec3d;"))
	private double pehkui$updateCrosshairTarget$setDistance(double value, float tickDelta)
	{
		final Entity entity = client.getCameraEntity();
		
		if (entity != null)
		{
			if (!ScaleRenderUtils.hasExtendedReach(client.interactionManager))
			{
				final double baseEntityReach = client.interactionManager.getCurrentGameMode().isCreative() ? 5.0F : 4.5F;
				
				return ReachEntityAttributesCompatibility.INSTANCE.getAttackRange(client.player, baseEntityReach);
			}
		}
		
		return value;
	}
	
	@ModifyVariable(method = "updateCrosshairTarget", ordinal = 0, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getRotationVec(F)Lnet/minecraft/util/math/Vec3d;"))
	private double pehkui$updateCrosshairTarget$fixDistance(double value, float tickDelta)
	{
		final Entity entity = client.getCameraEntity();
		
		if (entity != null)
		{
			if (ScaleRenderUtils.hasExtendedReach(client.interactionManager))
			{
				return ReachEntityAttributesCompatibility.INSTANCE.getAttackRange(client.player, 6.0D);
			}
		}
		
		return value;
	}
	
	@ModifyVariable(method = "updateCrosshairTarget", ordinal = 1, at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getRotationVec(F)Lnet/minecraft/util/math/Vec3d;"))
	private double pehkui$updateCrosshairTarget$fixSquaredDistance(double value, float tickDelta)
	{
		final Entity entity = client.getCameraEntity();
		
		if (entity != null)
		{
			if (this.client.crosshairTarget == null || this.client.crosshairTarget.getType() == HitResult.Type.MISS)
			{
				final double baseEntityReach = ScaleRenderUtils.hasExtendedReach(client.interactionManager) ? 6.0D : client.interactionManager.getCurrentGameMode().isCreative() ? 5.0F : 4.5F;
				final double entityReach = ReachEntityAttributesCompatibility.INSTANCE.getAttackRange(client.player, baseEntityReach);
				final double entityReachSquared = entityReach * entityReach;
				
				return entityReachSquared;
			}
		}
		
		return value;
	}
}
