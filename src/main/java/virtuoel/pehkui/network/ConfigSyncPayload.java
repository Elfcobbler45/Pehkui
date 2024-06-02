package virtuoel.pehkui.network;

import java.util.Collection;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import virtuoel.pehkui.Pehkui;
import virtuoel.pehkui.util.ConfigSyncUtils.SyncableConfigEntry;

public class ConfigSyncPayload extends ConfigSyncPacket implements CustomPayload
{
	public static final CustomPayload.Id<ConfigSyncPayload> ID = new CustomPayload.Id<>(Pehkui.CONFIG_SYNC_PACKET);
	public static final PacketCodec<PacketByteBuf, ConfigSyncPayload> CODEC = codec(ID);
	
	public ConfigSyncPayload(final Collection<SyncableConfigEntry<?>> configEntries)
	{
		super(configEntries);
	}
	
	public ConfigSyncPayload(final PacketByteBuf buf)
	{
		super(buf);
	}
	
	@Override
	public CustomPayload.Id<? extends CustomPayload> getId()
	{
		return ID;
	}
	
	private static PacketCodec<PacketByteBuf, ConfigSyncPayload> codec(final CustomPayload.Id<ConfigSyncPayload> id)
	{
		return CustomPayload.codecOf(ConfigSyncPayload::write, ConfigSyncPayload::new);
	}
}
