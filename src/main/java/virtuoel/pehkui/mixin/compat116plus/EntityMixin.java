package virtuoel.pehkui.mixin.compat116plus;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

@Mixin(Entity.class)
public abstract class EntityMixin
{
	@Shadow
	private BlockPos blockPos;
	
	@Unique
	protected void setPosDirectly(final BlockPos pos)
	{
		blockPos = pos;
	}
}
