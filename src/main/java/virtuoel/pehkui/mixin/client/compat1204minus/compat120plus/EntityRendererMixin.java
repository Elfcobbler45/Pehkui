package virtuoel.pehkui.mixin.client.compat1204minus.compat120plus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.entity.Entity;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin
{
	@Dynamic
	@WrapOperation(method = MixinConstants.RENDER_LABEL_IF_PRESENT, at = @At(value = "INVOKE", target = MixinConstants.GET_NAME_LABEL_HEIGHT))
	private float pehkui$renderLabelIfPresent$getNameLabelHeight(Entity entity, Operation<Float> original)
	{
		final float delta = MinecraftClient.getInstance().getTickDelta();
		return (original.call(entity) - entity.getHeight()) + (entity.getHeight() / ScaleUtils.getBoundingBoxHeightScale(entity, delta));
	}
}
