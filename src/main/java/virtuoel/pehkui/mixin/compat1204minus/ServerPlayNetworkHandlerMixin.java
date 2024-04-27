package virtuoel.pehkui.mixin.compat1204minus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.network.packet.c2s.play.PlayerInteractBlockC2SPacket;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin
{
	@Shadow ServerPlayerEntity player;
	
	@ModifyExpressionValue(method = "onPlayerInteractBlock", at = @At(value = "CONSTANT", args = "doubleValue=0.5D", ordinal = 0))
	private double pehkui$onPlayerInteractBlock$xOffset(double value, PlayerInteractBlockC2SPacket packet)
	{
		return ScaleUtils.getBlockXOffset(packet.getBlockHitResult().getBlockPos(), player);
	}
	
	@ModifyExpressionValue(method = "onPlayerInteractBlock", at = @At(value = "CONSTANT", args = "doubleValue=0.5D", ordinal = 1))
	private double pehkui$onPlayerInteractBlock$yOffset(double value, PlayerInteractBlockC2SPacket packet)
	{
		return ScaleUtils.getBlockYOffset(packet.getBlockHitResult().getBlockPos(), player);
	}
	
	@ModifyExpressionValue(method = "onPlayerInteractBlock", at = @At(value = "CONSTANT", args = "doubleValue=0.5D", ordinal = 2))
	private double pehkui$onPlayerInteractBlock$zOffset(double value, PlayerInteractBlockC2SPacket packet)
	{
		return ScaleUtils.getBlockZOffset(packet.getBlockHitResult().getBlockPos(), player);
	}
}
