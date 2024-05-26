package virtuoel.pehkui.mixin.compat1204minus.compat117plus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.ViewerCountManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(ViewerCountManager.class)
public class ViewerCountManagerMixin
{
	@Shadow
	int viewerCount;
	
	@Unique
	float viewerSearchRange = 5.0F;
	
	@Inject(at = @At("HEAD"), method = "openContainer(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V")
	private void pehkui$openContainer(PlayerEntity player, World world, BlockPos pos, BlockState state, CallbackInfo info)
	{
		if (viewerCount < 0)
		{
			viewerCount = 0;
			
			viewerSearchRange = 5.0F;
		}
		
		final float scale = ScaleUtils.getBlockReachScale(player);
		
		if (scale != 1.0F)
		{
			final float nextRange = 5.0F * scale;
			
			if (nextRange > viewerSearchRange)
			{
				viewerSearchRange = nextRange;
			}
		}
	}
	
	@Inject(at = @At("HEAD"), method = "closeContainer(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)V")
	private void pehkui$closeContainer(PlayerEntity player, World world, BlockPos pos, BlockState state, CallbackInfo info)
	{
		if (viewerCount <= 1)
		{
			viewerCount = 1;
			
			viewerSearchRange = 5.0F;
		}
	}
	
	@Dynamic
	@ModifyExpressionValue(method = MixinConstants.GET_IN_RANGE_VIEWER_COUNT, at = @At(value = "CONSTANT", args = "floatValue=5.0F"))
	private float pehkui$getInRangeViewerCount$range(float value)
	{
		return viewerSearchRange;
	}
}
