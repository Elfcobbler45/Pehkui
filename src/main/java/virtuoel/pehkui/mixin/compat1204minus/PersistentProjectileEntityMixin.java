package virtuoel.pehkui.mixin.compat1204minus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.util.math.Vec3d;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(PersistentProjectileEntity.class)
public abstract class PersistentProjectileEntityMixin
{
	@Dynamic
	@ModifyArg(method = "tick", index = 1, at = @At(value = "INVOKE", target = MixinConstants.PERSISTENT_PROJECTILE_ENTITY_SET_VELOCITY))
	private double pehkui$tick$gravity(double value)
	{
		final Vec3d velocity = ((Entity) (Object) this).getVelocity();
		final float scale = ScaleUtils.getMotionScale((Entity) (Object) this);
		
		return scale != 1.0F ? velocity.y + (scale * (value - velocity.y)) : value;
	}
}
