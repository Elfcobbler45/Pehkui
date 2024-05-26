package virtuoel.pehkui.mixin.reach.compat1204minus.compat119plus;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(ServerPlayNetworkHandler.class)
public abstract class ServerPlayNetworkHandlerMixin
{
	@Shadow
	ServerPlayerEntity player;
	
	@Dynamic
	@WrapOperation(method = "onPlayerInteractBlock", at = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = MixinConstants.MAX_BREAK_SQUARED_DISTANCE))
	private double pehkui$onPlayerInteractBlock$distance(Operation<Double> original)
	{
		final float scale = ScaleUtils.getBlockReachScale(player);
		return scale <= 1.0F ? original.call() : original.call() * scale * scale;
	}
	
	@Dynamic
	@WrapOperation(method = "onPlayerInteractEntity", at = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = MixinConstants.MAX_BREAK_SQUARED_DISTANCE))
	private double pehkui$onPlayerInteractEntity$distance(Operation<Double> original)
	{
		final float scale = ScaleUtils.getEntityReachScale(player);
		return scale <= 1.0F ? original.call() : original.call() * scale * scale;
	}
}
