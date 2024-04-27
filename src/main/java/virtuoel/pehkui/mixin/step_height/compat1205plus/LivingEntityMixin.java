package virtuoel.pehkui.mixin.step_height.compat1205plus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin
{
	@ModifyReturnValue(method = "getStepHeight()F", at = @At("RETURN"))
	private float pehkui$getStepHeight(float original)
	{
		final float scale = ScaleUtils.getStepHeightScale((Entity) (Object) this);
		
		return scale != 1.0F ? original * scale : original;
	}
}
