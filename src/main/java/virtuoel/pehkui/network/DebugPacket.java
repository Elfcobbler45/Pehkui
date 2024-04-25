package virtuoel.pehkui.network;

import net.minecraft.network.PacketByteBuf;
import virtuoel.pehkui.server.command.DebugCommand.DebugPacketType;

public class DebugPacket
{
	public final DebugPacketType type;
	
	public DebugPacket(final DebugPacketType type)
	{
		this.type = type;
	}
	
	public DebugPacket(final PacketByteBuf buf)
	{
		this.type = buf.readEnumConstant(DebugPacketType.class);
	}
	
	public void write(final PacketByteBuf buf)
	{
		buf.writeEnumConstant(this.type);
	}
}
