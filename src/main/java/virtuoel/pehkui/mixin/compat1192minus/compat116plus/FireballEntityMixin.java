package virtuoel.pehkui.mixin.compat1192minus.compat116plus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FireballEntity;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(FireballEntity.class)
public abstract class FireballEntityMixin
{
	@Dynamic
	@ModifyArg(method = "onCollision(Lnet/minecraft/util/hit/HitResult;)V", at = @At(value = "INVOKE", target = MixinConstants.CREATE_EXPLOSION_OPTIONAL_FIRE))
	private float pehkui$onCollision$createExplosion(float power)
	{
		final float scale = ScaleUtils.getExplosionScale((Entity) (Object) this);
		
		if (scale != 1.0F)
		{
			return power * scale;
		}
		
		return power;
	}
}
