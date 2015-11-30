package co.ryred.dfmserver.customprotocol;

import co.ryred.dfmserver.DFMServerMain;
import io.netty.channel.*;
import org.spacehq.packetlib.Client;
import org.spacehq.packetlib.packet.Packet;
import org.spacehq.packetlib.packet.PacketProtocol;
import org.spacehq.packetlib.tcp.*;

import java.lang.reflect.Field;
import java.net.Proxy;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 23/11/2015.
 */
public class CustomTCPSession extends TcpClientSession
{

	public static final UUID uuid = UUID.nameUUIDFromBytes( ( "SpecialPlayer:BlackGuy" ).getBytes( StandardCharsets.UTF_8 ) );
	private boolean RUNNING = true;

	public CustomTCPSession( String host, int port, PacketProtocol protocol, Client client, Proxy proxy )
	{
		super( host, port, protocol, client, proxy );
	}

	@Override
	public void send( Packet packet )
	{

		packet = DFMServerMain.handlePacket( packet );
		if( packet == null ) return;
		super.send( packet );
	}

	private Channel getChannel()
	{

		try {

			Field channelField = TcpSession.class.getDeclaredField( "channel" );
			channelField.setAccessible( true );
			return (Channel) channelField.get( this );

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;

	}

	private void setChannel( Channel ch )
	{

		try {

			Field channelField = TcpSession.class.getDeclaredField( "channel" );
			channelField.setAccessible( true );
			channelField.set( this, ch );

		} catch ( Exception e ) {
			e.printStackTrace();
		}

	}

	private BlockingQueue<Packet> getBlockingQueue()
	{

		try {

			Field channelField = TcpSession.class.getDeclaredField( "packets" );
			channelField.setAccessible( true );
			return (BlockingQueue<Packet>) channelField.get( this );

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;

	}

	private Client getClient()
	{

		try {

			Field channelField = TcpClientSession.class.getDeclaredField( "client" );
			channelField.setAccessible( true );
			return (Client) channelField.get( this );

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;

	}

	private EventLoopGroup getEventLoopGroup()
	{

		try {

			Field channelField = TcpClientSession.class.getDeclaredField( "group" );
			channelField.setAccessible( true );
			return (EventLoopGroup) channelField.get( this );

		} catch ( Exception e ) {
			e.printStackTrace();
		}

		return null;

	}

	private void setEventLoopGroup( EventLoopGroup group )
	{

		try {

			Field channelField = TcpClientSession.class.getDeclaredField( "group" );
			channelField.setAccessible( true );
			channelField.set( this, group );

		} catch ( Exception e ) {
			e.printStackTrace();
		}

	}
	
}
