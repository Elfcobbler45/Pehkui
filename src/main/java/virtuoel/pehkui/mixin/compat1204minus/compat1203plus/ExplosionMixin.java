package virtuoel.pehkui.mixin.compat1204minus.compat1203plus;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.sound.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(Explosion.class)
public abstract class ExplosionMixin
{
	@Shadow @Final @Mutable float power;
	
	@Dynamic
	@Inject(at = @At("RETURN"), method = MixinConstants.EXPLOSION_WITH_PARTICLES_AND_SOUND_INIT)
	private void pehkui$construct(World world, @Nullable Entity entity, @Nullable DamageSource damageSource, @Nullable ExplosionBehavior explosionBehavior, double x, double y, double z, float power, boolean createFire, Explosion.DestructionType blockDestructionType, ParticleEffect particle, ParticleEffect emitterParticle, SoundEvent soundEvent, CallbackInfo info)
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
