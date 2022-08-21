package virtuoel.pehkui.mixin.compat114;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import virtuoel.pehkui.util.MixinConstants;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin
{
	@ModifyArg(method = MixinConstants.DEATH, at = @At(value = "INVOKE", target = MixinConstants.SPAWN_ENTITY, remap = false), remap = false)
	private Entity pehkui$onDeath$entity(Entity entity)
	{
		ScaleUtils.setScaleOfDrop(entity, (Entity) (Object) this);
		
		return entity;
	}
	
	@ModifyArg(method = MixinConstants.POST_DEATH, at = @At(value = "INVOKE", target = MixinConstants.SPAWN_ENTITY, remap = false), remap = false)
	private Entity pehkui$updatePostDeath$entity(Entity entity)
	{
		ScaleUtils.setScaleOfDrop(entity, (Entity) (Object) this);
		
		return entity;
	}
}
