package virtuoel.pehkui.mixin.compat116plus.compat1202minus;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Desc;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(Explosion.class)
public abstract class ExplosionMixin
{
	@Shadow @Final @Mutable float power;
	
	@Inject(at = @At("RETURN"), target = @Desc(owner = Explosion.class, value = "<init>", args = { World.class, Entity.class, DamageSource.class, ExplosionBehavior.class, double.class, double.class, double.class, float.class, boolean.class, Explosion.DestructionType.class }), remap = false)
	private void pehkui$construct(World world, @Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior explosionBehavior, double x, double y, double z, float power, boolean createFire, Explosion.DestructionType blockDestructionType, CallbackInfo info)
	{
		if (entity != null)
		{
			final float scale = ScaleUtils.getExplosionScale(entity);
			
			if (scale != 1.0F)
			{
				this.power *= scale;
			}
		}
	}
}
