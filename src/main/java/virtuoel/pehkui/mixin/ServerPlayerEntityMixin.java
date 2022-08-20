package virtuoel.pehkui.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.network.ServerPlayerEntity;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(ServerPlayerEntity.class)
public abstract class ServerPlayerEntityMixin
{
	@Inject(at = @At("HEAD"), method = "copyFrom")
	private void pehkui$copyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info)
	{
		ScaleUtils.loadScaleOnRespawn((ServerPlayerEntity) (Object) this, oldPlayer, alive);
	}
}
