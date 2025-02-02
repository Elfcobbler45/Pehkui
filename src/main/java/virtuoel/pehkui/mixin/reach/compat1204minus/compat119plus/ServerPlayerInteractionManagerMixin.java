package virtuoel.pehkui.mixin.reach.compat1204minus.compat119plus;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin
{
	@Shadow @Final @Mutable
	ServerPlayerEntity player;
	
	@Dynamic
	@WrapOperation(method = "processBlockBreakingAction", at = @At(value = "FIELD", opcode = Opcodes.GETSTATIC, target = MixinConstants.MAX_BREAK_SQUARED_DISTANCE))
	private double pehkui$processBlockBreakingAction$distance(Operation<Double> original)
	{
		final float scale = ScaleUtils.getBlockReachScale(player);
		return scale <= 1.0F ? original.call() : original.call() * scale * scale;
	}
}
