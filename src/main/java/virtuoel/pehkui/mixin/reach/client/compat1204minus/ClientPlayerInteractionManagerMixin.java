package virtuoel.pehkui.mixin.reach.client.compat1204minus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(ClientPlayerInteractionManager.class)
public class ClientPlayerInteractionManagerMixin
{
	@Shadow @Final MinecraftClient client;
	
	@Dynamic
	@ModifyReturnValue(method = MixinConstants.GET_REACH_DISTANCE, at = @At("RETURN"))
	private float pehkui$getReachDistance(float original)
	{
		if (client.player != null)
		{
			final float scale = ScaleUtils.getBlockReachScale(client.player);
			
			if (scale != 1.0F)
			{
				return original * scale;
			}
		}
		
		return original;
	}
}
