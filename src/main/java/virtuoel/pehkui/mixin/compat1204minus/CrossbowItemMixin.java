package virtuoel.pehkui.mixin.compat1204minus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin
{
	@Dynamic
	@ModifyExpressionValue(method = MixinConstants.SHOOT, at = @At(value = "CONSTANT", args = "doubleValue=0.15000000596046448D"))
	private static double pehkui$shoot$yOffset(double value, World world, LivingEntity shooter, Hand hand, ItemStack crossbow, ItemStack projectile, float soundPitch, boolean creative, float speed, float divergence, float simulated)
	{
		final float scale = ScaleUtils.getEyeHeightScale(shooter);
		
		return scale != 1.0F ? value * scale : value;
	}
}
