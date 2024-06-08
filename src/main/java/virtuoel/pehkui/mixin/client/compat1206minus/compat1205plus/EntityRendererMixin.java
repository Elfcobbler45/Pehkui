package virtuoel.pehkui.mixin.client.compat1206minus.compat1205plus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;

import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity>
{
	@Dynamic
	@ModifyExpressionValue(method = MixinConstants.RENDER_LABEL_IF_PRESENT_WITH_DELTA, at = @At(value = "CONSTANT", args = "doubleValue=0.5D"))
	private double pehkui$renderLabelIfPresent$offset(double value, @Local(argsOnly = true) T entity)
	{
		final float scale = ScaleUtils.getBoundingBoxHeightScale(entity);
		
		return scale != 1.0F ? value + (entity.getHeight() * ((1.0F / scale) - 1.0F)) : value;
	}
}
