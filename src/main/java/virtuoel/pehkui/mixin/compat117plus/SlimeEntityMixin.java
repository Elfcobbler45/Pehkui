package virtuoel.pehkui.mixin.compat117plus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.SlimeEntity;
import virtuoel.pehkui.util.ScaleUtils;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin
{
	@ModifyArg(method = "remove(Lnet/minecraft/entity/Entity$RemovalReason;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
	private Entity pehkui$remove$spawnEntity(Entity entity)
	{
		ScaleUtils.loadScale(entity, (Entity) (Object) this);
		
		return entity;
	}
	
	@ModifyExpressionValue(method = "remove(Lnet/minecraft/entity/Entity$RemovalReason;)V", at = @At(value = "CONSTANT", args = "doubleValue=0.5D"))
	private double pehkui$remove$verticalOffset(double value)
	{
		final float scale = ScaleUtils.getBoundingBoxHeightScale((Entity) (Object) this);
		
		if (scale != 1.0F)
		{
			return value * scale;
		}
		
		return value;
	}
}
