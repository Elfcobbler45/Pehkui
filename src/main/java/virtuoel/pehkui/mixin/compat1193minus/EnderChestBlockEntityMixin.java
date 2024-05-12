package virtuoel.pehkui.mixin.compat1193minus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EnderChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(EnderChestBlockEntity.class)
public abstract class EnderChestBlockEntityMixin
{
	@Dynamic
	@ModifyExpressionValue(method = MixinConstants.ENDER_CHEST_CAN_PLAYER_USE, at = @At(value = "CONSTANT", args = "doubleValue=0.5D", ordinal = 0))
	private double pehkui$canPlayerUse$xOffset(double value, PlayerEntity player)
	{
		return ScaleUtils.getBlockXOffset(((BlockEntity) (Object) this).getPos(), player);
	}
	
	@Dynamic
	@ModifyExpressionValue(method = MixinConstants.ENDER_CHEST_CAN_PLAYER_USE, at = @At(value = "CONSTANT", args = "doubleValue=0.5D", ordinal = 1))
	private double pehkui$canPlayerUse$yOffset(double value, PlayerEntity player)
	{
		return ScaleUtils.getBlockYOffset(((BlockEntity) (Object) this).getPos(), player);
	}
	
	@Dynamic
	@ModifyExpressionValue(method = MixinConstants.ENDER_CHEST_CAN_PLAYER_USE, at = @At(value = "CONSTANT", args = "doubleValue=0.5D", ordinal = 2))
	private double pehkui$canPlayerUse$zOffset(double value, PlayerEntity player)
	{
		return ScaleUtils.getBlockZOffset(((BlockEntity) (Object) this).getPos(), player);
	}
}
