package virtuoel.pehkui.mixin.compat1206minus;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ExplosiveProjectileEntity;
import net.minecraft.world.World;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(ExplosiveProjectileEntity.class)
public abstract class ExplosiveProjectileEntityMixin
{
	@Dynamic
	@Shadow
	public float field_7601; // UNMAPPED_FIELD
	@Dynamic
	@Shadow
	public float field_7600; // UNMAPPED_FIELD
	@Dynamic
	@Shadow
	public float field_7599; // UNMAPPED_FIELD
	
	@Inject(at = @At("RETURN"), method = MixinConstants.EXPLOSIVE_PROJECTILE_ENTITY_INIT)
	private void pehkui$construct(EntityType<? extends ExplosiveProjectileEntity> type, LivingEntity owner, double directionX, double directionY, double directionZ, World world, CallbackInfo info)
	{
		final ExplosiveProjectileEntity self = (ExplosiveProjectileEntity) (Object) this;
		final float scale = ScaleUtils.setScaleOfProjectile(self, owner);
		
		if (scale != 1.0F)
		{
			field_7601 *= scale;
			field_7600 *= scale;
			field_7599 *= scale;
		}
	}
}
