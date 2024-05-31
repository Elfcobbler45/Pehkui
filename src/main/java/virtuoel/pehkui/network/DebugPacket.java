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
		DebugCommand.PacketType read;
		
		try
		{
			read = buf.readEnumConstant(DebugCommand.PacketType.class);
		}
		catch (Exception e)
		{
			read = null;
		}
		
		this.type = read;
	}
	
	public void write(final PacketByteBuf buf)
	{
		buf.writeEnumConstant(this.type);
	}
}
