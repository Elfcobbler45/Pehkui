package virtuoel.pehkui.network;

import java.util.Collection;

import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import virtuoel.pehkui.Pehkui;
import virtuoel.pehkui.api.ScaleData;

public class ScalePayload extends ScalePacket implements CustomPayload
{
	public static final CustomPayload.Id<ScalePayload> ID = new CustomPayload.Id<>(Pehkui.SCALE_PACKET);
	public static final PacketCodec<PacketByteBuf, ScalePayload> CODEC = codec(ID);
	
	public ScalePayload(final Entity entity, final Collection<ScaleData> scales)
	{
		super(entity, scales);
	}
	
	public ScalePayload(final PacketByteBuf buf)
	{
		super(buf);
	}
	
	@Override
	public Id<? extends CustomPayload> getId()
	{
		return ID;
	}
	
	private static PacketCodec<PacketByteBuf, ScalePayload> codec(final Id<ScalePayload> id)
	{
		return CustomPayload.codecOf(ScalePayload::write, ScalePayload::new);
	}
}
