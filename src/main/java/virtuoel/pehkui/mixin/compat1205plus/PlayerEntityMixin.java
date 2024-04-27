package virtuoel.pehkui.mixin.compat1205plus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin
{
	@ModifyReturnValue(method = "getBlockInteractionRange", at = @At("RETURN"))
	private double pehkui$getBlockInteractionRange(double original)
	{
		final float scale = ScaleUtils.getBlockReachScale((Entity) (Object) this);
		return scale != 1.0F ? scale * original : original;
	}
	
	@ModifyReturnValue(method = "getEntityInteractionRange", at = @At("RETURN"))
	private double pehkui$getEntityInteractionRange(double original)
	{
		final float scale = ScaleUtils.getEntityReachScale((Entity) (Object) this);
		return scale != 1.0F ? scale * original : original;
	}
}
