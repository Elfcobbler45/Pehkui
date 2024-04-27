package virtuoel.pehkui.mixin.compat1205plus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.WitherEntity;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(WitherEntity.class)
public class WitherEntityMixin
{
	@ModifyExpressionValue(method = "getHeadY", at = { @At(value = "CONSTANT", args = "floatValue=3.0F"), @At(value = "CONSTANT", args = "floatValue=2.2F") })
	private float pehkui$getHeadY$offset(float value)
	{
		final float scale = ScaleUtils.getBoundingBoxHeightScale((Entity) (Object) this);
		
		return scale != 1.0F ? scale * value : value;
	}
}
