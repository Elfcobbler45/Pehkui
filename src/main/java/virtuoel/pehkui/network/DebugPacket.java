package virtuoel.pehkui.network;

import net.minecraft.network.PacketByteBuf;
import virtuoel.pehkui.server.command.DebugCommand;

public class DebugPacket
{
	public final DebugCommand.PacketType type;
	
	public DebugPacket(final DebugCommand.PacketType type)
	{
		this.type = type;
	}
	
	public DebugPacket(final PacketByteBuf buf)
	{
		this.type = buf.readEnumConstant(DebugCommand.PacketType.class);
	}
	
	public void write(final PacketByteBuf buf)
	{
		buf.writeEnumConstant(this.type);
	}
}
