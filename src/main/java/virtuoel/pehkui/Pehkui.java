package virtuoel.pehkui;

import java.util.UUID;

import org.spongepowered.asm.logging.ILogger;
import org.spongepowered.asm.service.MixinService;

import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import virtuoel.pehkui.api.PehkuiConfig;
import virtuoel.pehkui.api.ScaleTypes;
import virtuoel.pehkui.command.PehkuiEntitySelectorOptions;
import virtuoel.pehkui.network.PehkuiPacketHandler;
import virtuoel.pehkui.util.CommandUtils;
import virtuoel.pehkui.util.GravityChangerCompatibility;
import virtuoel.pehkui.util.IdentityCompatibility;
import virtuoel.pehkui.util.ImmersivePortalsCompatibility;
import virtuoel.pehkui.util.MulticonnectCompatibility;
import virtuoel.pehkui.util.ReachEntityAttributesCompatibility;
import virtuoel.pehkui.util.ScaleUtils;
import virtuoel.pehkui.util.VersionUtils;

@Mod(Pehkui.MOD_ID)
public class Pehkui
{
	public static final String MOD_ID = "pehkui";
	
	public static final ILogger LOGGER = MixinService.getService().getLogger(MOD_ID);
	
	public static final UUID REACH_MODIFIER = UUID.fromString("d82ebc57-0753-43e5-95b5-895dc1071e12");
	
	public Pehkui()
	{
		ScaleTypes.INVALID.getClass();
		
		ScaleTypes.REACH.getPostTickEvent().add(s ->
		{
			final Entity e = s.getEntity();
			
			if (e instanceof PlayerEntity && !e.world.isClient)
			{
				final EntityAttributeInstance attribute = ((PlayerEntity) e).getAttributeInstance(ForgeMod.REACH_DISTANCE.get());
				
				if (attribute != null)
				{
					final double scale = ScaleUtils.getBlockReachScale(e) - 1.0D;
					final EntityAttributeModifier modifier = attribute.getModifier(REACH_MODIFIER);
					
					if (modifier == null || Double.compare(scale, modifier.getValue()) != 0)
					{
						if (modifier != null)
						{
							attribute.removeModifier(REACH_MODIFIER);
						}
						
						if (Double.compare(scale, 0.0D) != 0)
						{
							attribute.addPersistentModifier(new EntityAttributeModifier(REACH_MODIFIER, "Reach Scale", scale, EntityAttributeModifier.Operation.MULTIPLY_BASE));
						}
					}
				}
			}
		});
		
		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(PehkuiConfig.class);
		
		ModLoadingContext ctx = ModLoadingContext.get();
		ctx.registerConfig(ModConfig.Type.CLIENT, PehkuiConfig.clientSpec);
		ctx.registerConfig(ModConfig.Type.SERVER, PehkuiConfig.serverSpec);
		ctx.registerConfig(ModConfig.Type.COMMON, PehkuiConfig.commonSpec);
		
		if (VersionUtils.MINOR <= 18)
		{
			CommandUtils.registerArgumentTypes(CommandUtils::registerConstantArgumentType);
		}
		
		PehkuiEntitySelectorOptions.register();
		
		PehkuiPacketHandler.init();
		
		GravityChangerCompatibility.INSTANCE.getClass();
		IdentityCompatibility.INSTANCE.getClass();
		ImmersivePortalsCompatibility.INSTANCE.getClass();
		MulticonnectCompatibility.INSTANCE.getClass();
		ReachEntityAttributesCompatibility.INSTANCE.getClass();
	}
	
	@SubscribeEvent
	public void onRegisterCommands(RegisterCommandsEvent event)
	{
		CommandUtils.registerCommands(event.getDispatcher());
	}
	
	public static Identifier id(String path)
	{
		return new Identifier(MOD_ID, path);
	}
	
	public static Identifier id(String path, String... paths)
	{
		return id(paths.length == 0 ? path : path + "/" + String.join("/", paths));
	}
	
	public static final Identifier SCALE_PACKET = id("scale");
	public static final Identifier CONFIG_SYNC_PACKET = id("config_sync");
	public static final Identifier DEBUG_PACKET = id("debug");
}
