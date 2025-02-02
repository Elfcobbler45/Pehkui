package virtuoel.pehkui.mixin.compat1201minus.compat117plus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.AxolotlEntity;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(AxolotlEntity.class)
public class AxolotlEntityMixin
{
	@Dynamic
	@ModifyExpressionValue(method = MixinConstants.SQUARED_ATTACK_RANGE, at = @At(value = "CONSTANT", args = "doubleValue=1.5D"))
	private double pehkui$squaredAttackRange$range(double value)
	{
		final float scale = ScaleUtils.getBoundingBoxWidthScale((Entity) (Object) this);
		
		if (scale != 1.0F)
		{
			return scale * scale * value;
		}
		
		return value;
	}
}
