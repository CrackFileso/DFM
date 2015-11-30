package co.ryred.dfmserver;

import co.ryred.dfmserver.customprotocol.ChatColor;
import co.ryred.dfmserver.customprotocol.CustomSessionFactory;
import org.spacehq.mc.auth.data.GameProfile;
import org.spacehq.mc.protocol.MinecraftConstants;
import org.spacehq.mc.protocol.MinecraftProtocol;
import org.spacehq.mc.protocol.ServerLoginHandler;
import org.spacehq.mc.protocol.data.message.TextMessage;
import org.spacehq.mc.protocol.data.status.PlayerInfo;
import org.spacehq.mc.protocol.data.status.ServerStatusInfo;
import org.spacehq.mc.protocol.data.status.VersionInfo;
import org.spacehq.mc.protocol.data.status.handler.ServerInfoBuilder;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.packet.Packet;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.net.Proxy;
import java.util.Random;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 30/11/2015.
 */
public class DFMServerMain
{

	static Random random = new Random();

	static String[] strings = new String[] {

			"&9ZIPPING THE ZIPPER.",
			"&eDeleting all da banz.",
			"&cAdding salt and pepper.",
			"&2Cleaning up the mayo.",
			"&dGently throwing files.",
			"&aCrap.. erm we lost your most prized items.",
			"&6We're defo doing this as fast as we can!",
			"&bI've gone fishing..",
			"&fI love you.. But I'm tired.",
			"&3Isn't it past your bedtime?",
			"&1I'm tired..",
			"&9We need more MOTD ideas!",
			"&7Any ideas for the MOTD?",
			"&3Man I'm breaking a sweat.",
			"&2I'm getting ICECREAM!",
			"&cAre you bored?",
			"&8I'm well aware I can't spel! Thanks.",
			"&4404 Server not found",
			"&7This server requires additional pylons.... shutting down...",
			"&9Me love you long time!",
			"&fHmmmm, to be or not to be..",
			"&8Ghoti_Mayo came up with some of these.",
			"&3Server self destruct initiated",
			"&1You are banned becaused you're bad.. :(",
			"&8server going on killing spree",
			"&eHaxored by Corie!",
			"&4Do these amoose u!?",
			"&8server going on killing spree",
			"&1Ghoti says hi. also server is going byebye",
			"&fThis is an urgent message.",
			"&9those responsible for the previous message have been sacked",
			"&1L&2E&3E&4D&5L&6E&7L&8E&9E&1D&2L&3E&4L&5E&6E&7D&8L&9E",
			"&5...nwod gnittuhs revreS"

	};

	public static void main( String... args ) {

		new Thread( new Runnable() {
			
			public void run() {
						
				while( true ) {
						try {
							Thread.sleep(500);
						} catch( Exception e ) {}
				}
				
			}
			
		} ).start();
		
		if( args.length != 2 ) {
			System.err.println( "INCORRECT USAGE!" );
			System.err.println( " USAGE:" );
			System.err.println( " java -jar DFM.jar 0.0.0.0 25565" );
			return;
		}

		String host = args[0];
		int port = Integer.parseInt( args[1] );

		Server server = new Server(host, port, MinecraftProtocol.class, new CustomSessionFactory());
		server.setGlobalFlag( MinecraftConstants.AUTH_PROXY_KEY, Proxy.NO_PROXY);
		server.setGlobalFlag(MinecraftConstants.VERIFY_USERS_KEY, false);
		server.setGlobalFlag(MinecraftConstants.SERVER_INFO_BUILDER_KEY, (ServerInfoBuilder) session -> new ServerStatusInfo(new VersionInfo( ChatColor.translateAlternateColorCodes( '&', "&4&lMaintenance" ), MinecraftConstants.PROTOCOL_VERSION + 10), new PlayerInfo(1000, 100, new GameProfile[0]), new TextMessage( getRandomMotd() ), getFavicon()) );
		server.setGlobalFlag(MinecraftConstants.SERVER_LOGIN_HANDLER_KEY, (ServerLoginHandler) session -> session.disconnect( getRandomMotd() ) );
		server.setGlobalFlag(MinecraftConstants.SERVER_COMPRESSION_THRESHOLD, 100);

		server.bind();

	}

	public static Packet handlePacket( Packet packet )
	{
		return packet;
	}

	public static String getRandomMotd() {
		return ChatColor.translateAlternateColorCodes( '&', "&c&lHC Server is under maintenance!!\n" + strings[random.nextInt( strings.length )] );
	}

	public static BufferedImage getFavicon() {

		try {
			BufferedImage image = ImageIO.read( DFMServerMain.class.getResourceAsStream( "reg-server-icon.png" ) );

			// check size
			if ( image.getWidth() != 64 || image.getHeight() != 64 )
			{
				throw new IllegalArgumentException( "Server icon must be exactly 64x64 pixels" );
			}

			return image;

		} catch ( Exception e ) {
			return null;
		}

	}

}
