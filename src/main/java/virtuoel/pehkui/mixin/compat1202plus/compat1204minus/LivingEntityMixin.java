package virtuoel.pehkui.mixin.compat1202plus.compat1204minus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin
{
	@Dynamic
	@ModifyArg(method = "getPassengerRidingPos", at = @At(value = "INVOKE", target = MixinConstants.LIVING_ENTITY_GET_PASSENGER_ATTACHMENT_POS))
	private float pehkui$getPassengerRidingPos$getPassengerAttachmentPos(float value)
	{
		final float scale = ScaleUtils.getBoundingBoxHeightScale((Entity) (Object) this);
		return scale == 1.0F ? value : value * scale;
	}
	
	@Dynamic
	@ModifyReturnValue(method = MixinConstants.GET_RIDING_OFFSET, at = @At("RETURN"))
	private float pehkui$getRidingOffset(float original)
	{
		final float scale = ScaleUtils.getBoundingBoxHeightScale((Entity) (Object) this);
		
		return scale != 1.0F ? original * scale : original;
	}
}
