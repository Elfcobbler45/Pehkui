package virtuoel.pehkui.mixin.compat1206minus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.decoration.AbstractDecorationEntity;
import virtuoel.pehkui.api.ScaleRegistries;
import virtuoel.pehkui.api.ScaleType;
import virtuoel.pehkui.mixin.EntityMixin;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin({
	AbstractDecorationEntity.class,
})
public abstract class PreEntityTickMixin extends EntityMixin
{
	@Dynamic
	@Inject(at = @At("HEAD"), method = MixinConstants.TICK)
	private void pehkui$tick(CallbackInfo info)
	{
		for (final ScaleType scaleType : ScaleRegistries.SCALE_TYPES.values())
		{
			ScaleUtils.tickScale(pehkui_getScaleData(scaleType));
		}
	}
}
