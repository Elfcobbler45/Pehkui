package virtuoel.pehkui.mixin.client.compat1202plus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.entity.LivingEntity;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(InventoryScreen.class)
public abstract class InventoryScreenMixin
{
	@WrapOperation(method = "drawEntity(Lnet/minecraft/client/gui/DrawContext;IIIIIFFFLnet/minecraft/entity/LivingEntity;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getHeight()F"))
	private static float pehkui$drawEntity$getHeight(LivingEntity obj, Operation<Float> original)
	{
		final float value = original.call(obj);
		final float scale = ScaleUtils.getBoundingBoxHeightScale(obj);
		
		return scale != 1.0F ? ScaleUtils.divideClamped(value, scale) : value;
	}
}
