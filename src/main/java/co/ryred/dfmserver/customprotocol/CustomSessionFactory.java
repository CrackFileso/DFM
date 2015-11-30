package co.ryred.dfmserver.customprotocol;

import org.spacehq.packetlib.*;
import org.spacehq.packetlib.tcp.TcpConnectionListener;

import java.net.Proxy;

/**
 * @author Cory Redmond
 *         Created by acech_000 on 23/11/2015.
 */
public class CustomSessionFactory implements SessionFactory
{


	public CustomSessionFactory() {
	}

	public Session createClientSession(Client client) {
		//return new ThreadlessTcpClientSession(client.getHost(), client.getPort(), client.getPacketProtocol(), client, Proxy.NO_PROXY, plugin);
		return new CustomTCPSession(client.getHost(), client.getPort(), client.getPacketProtocol(), client, Proxy.NO_PROXY);
	}

	public ConnectionListener createServerListener(Server server) {
		return new TcpConnectionListener(server.getHost(), server.getPort(), server);
	}

}
