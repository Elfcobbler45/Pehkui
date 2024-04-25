package virtuoel.pehkui.network;

import java.util.Collection;

import net.minecraft.network.PacketByteBuf;
import virtuoel.pehkui.util.ConfigSyncUtils;
import virtuoel.pehkui.util.ConfigSyncUtils.SyncableConfigEntry;

public class ConfigSyncPacket
{
	public Collection<SyncableConfigEntry<?>> configEntries;
	public Runnable action;
	
	public ConfigSyncPacket(final Collection<SyncableConfigEntry<?>> configEntries)
	{
		this.configEntries = configEntries;
	}
	
	public ConfigSyncPacket(final PacketByteBuf buf)
	{
		this.action = ConfigSyncUtils.readConfigs(buf);
	}
	
	public void write(final PacketByteBuf buf)
	{
		ConfigSyncUtils.write(configEntries, buf);
	}
}
