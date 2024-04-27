package virtuoel.pehkui.mixin.compat1205plus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraft.entity.Entity;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(Entity.class)
public abstract class EntityMixin
{
	@ModifyArg(method = "getPassengerRidingPos", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;getPassengerAttachmentPos(Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/EntityDimensions;F)Lnet/minecraft/util/math/Vec3d;"))
	private float pehkui$getPassengerRidingPos$getPassengerAttachmentPos(float value)
	{
		final float scale = ScaleUtils.getBoundingBoxHeightScale((Entity) (Object) this);
		return scale == 1.0F ? value : value * scale;
	}
	
	@ModifyReturnValue(method = "getFinalGravity", at = @At("RETURN"))
	private double pehkui$getFinalGravity(double original)
	{
		if (original == 0.0D)
		{
			return 0.0D;
		}
		
		final float scale = ScaleUtils.getMotionScale((Entity) (Object) this);
		return scale != 1.0F ? original : original * scale;
	}
}
