package virtuoel.pehkui.mixin.client.compat1194minus.compat117plus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.player.PlayerEntity;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(InGameHud.class)
public abstract class InGameHudMixin
{
	@Shadow
	abstract PlayerEntity getCameraPlayer();
	
	@Shadow @Final @Mutable
	MinecraftClient client;
	
	@Dynamic
	@ModifyArg(method = MixinConstants.RENDER_STATUS_BARS, index = 0, at = @At(value = "INVOKE", target = "Ljava/lang/Math;max(FF)F"))
	private float pehkui$renderStatusBars(float value)
	{
		final float healthScale = ScaleUtils.getHealthScale(getCameraPlayer(), client.getTickDelta());
		
		return healthScale != 1.0F ? value * healthScale : value;
	}
}
