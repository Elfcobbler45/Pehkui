package virtuoel.pehkui.mixin.compat1204minus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.LivingEntity;
import virtuoel.pehkui.api.PehkuiConfig;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(LivingEntity.class)
public class LivingEntityMixin
{
	@Dynamic
	@ModifyArg(method = MixinConstants.GET_EYE_HEIGHT, index = 1, at = @At(value = "INVOKE", target = MixinConstants.GET_ACTIVE_EYE_HEIGHT_TARGET))
	private EntityDimensions pehkui$getEyeHeight$dimensions(EntityDimensions dimensions)
	{
		return dimensions.scaled(1.0F / ScaleUtils.getEyeHeightScale((Entity) (Object) this));
	}
	
	@ModifyExpressionValue(method = "travel", at = @At(value = "CONSTANT", args = "floatValue=1.0F", ordinal = 0))
	private float pehkui$travel$fallDistance(float value)
	{
		final float scale = ScaleUtils.getFallingScale((Entity) (Object) this);
		
		if (scale != 1.0F)
		{
			if (PehkuiConfig.COMMON.scaledFallDamage.get())
			{
				return value / scale;
			}
		}
		
		return value;
	}
	
	@Dynamic
	@ModifyReturnValue(method = MixinConstants.GET_EYE_HEIGHT, at = @At("RETURN"))
	private float pehkui$getEyeHeight(float original, EntityPose pose, EntityDimensions dimensions)
	{
		if (pose != EntityPose.SLEEPING)
		{
			final float scale = ScaleUtils.getEyeHeightScale((Entity) (Object) this);
			
			if (scale != 1.0F)
			{
				return original * scale;
			}
		}
		
		return original;
	}
	
	@ModifyReturnValue(method = "getJumpVelocity()F", at = @At("RETURN"))
	private float pehkui$getJumpVelocity(float original)
	{
		final float scale = ScaleUtils.getJumpHeightScale((Entity) (Object) this);
		
		return scale != 1.0F ? original * scale : original;
	}
}
