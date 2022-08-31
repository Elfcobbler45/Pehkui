package virtuoel.pehkui.mixin.compat116plus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.entity.LivingEntity;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends EntityMixin
{
	@ModifyConstant(method = "updateLimbs", constant = @Constant(floatValue = 4.0F))
	private float pehkui$updateLimbs$limbDistance(float value, LivingEntity livingEntity, boolean bl)
	{
		return ScaleUtils.modifyLimbDistance(value, livingEntity);
	}
}
