package virtuoel.pehkui.mixin.client.compat1205plus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.entity.ShulkerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.util.math.Direction;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(ShulkerEntityRenderer.class)
public class ShulkerEntityRendererMixin
{
	@Inject(at = @At("RETURN"), method = "setupTransforms")
	private void pehkui$setupTransforms(ShulkerEntity entity, MatrixStack matrices, float animationProgress, float bodyYaw, float tickDelta, float scale, CallbackInfo info)
	{
		final Direction face = entity.getAttachedFace();
		if (face != Direction.DOWN)
		{
			final float h = ScaleUtils.getModelHeightScale(entity, tickDelta);
			if (face != Direction.UP)
			{
				final float w = ScaleUtils.getModelWidthScale(entity, tickDelta);
				if (w != 1.0F || h != 1.0F)
				{
					matrices.translate(0.0, -((1.0F - w) * 0.5F) / w, -((1.0F - h) * 0.5F) / h);
				}
			}
			else if (h != 1.0F)
			{
				matrices.translate(0.0, -(1.0F - h) / h, 0.0);
			}
		}
	}
}
