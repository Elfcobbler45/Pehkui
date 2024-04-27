package virtuoel.pehkui.mixin.compat1204minus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(ThrownEntity.class)
public abstract class ThrownEntityMixin
{
	@Dynamic @Shadow
	protected abstract float method_7490(); // UNMAPPED_METHOD
	
	@Dynamic
	@ModifyArg(method = "tick()V", index = 1, at = @At(value = "INVOKE", target = MixinConstants.THROWN_ENTITY_SET_VELOCITY))
	private double pehkui$tick$setVelocity(double value)
	{
		final float scale = ScaleUtils.getMotionScale((Entity) (Object) this);
		
		if (scale != 1.0F)
		{
			return value + ((1.0F - scale) * method_7490());
		}
		
		return value;
	}
}
