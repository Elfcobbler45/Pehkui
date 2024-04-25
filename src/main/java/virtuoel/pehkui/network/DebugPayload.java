package virtuoel.pehkui.network;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import virtuoel.pehkui.Pehkui;
import virtuoel.pehkui.server.command.DebugCommand.DebugPacketType;

public class DebugPayload extends DebugPacket implements CustomPayload
{
	public static final CustomPayload.Id<DebugPayload> ID = new CustomPayload.Id<>(Pehkui.DEBUG_PACKET);
	public static final PacketCodec<PacketByteBuf, DebugPayload> CODEC = codec(ID);
	
	public DebugPayload(final DebugPacketType type)
	{
		super(type);
	}
	
	public DebugPayload(final PacketByteBuf buf)
	{
		super(buf);
	}
	
	@Override
	public Id<? extends CustomPayload> getId()
	{
		return ID;
	}
	
	private static PacketCodec<PacketByteBuf, DebugPayload> codec(final Id<DebugPayload> id)
	{
		return CustomPayload.codecOf(DebugPayload::write, DebugPayload::new);
	}
}
