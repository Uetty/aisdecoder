

package dk.tbsalling.aismessages.nmea;


import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.util.function.Consumer;
import java.util.logging.Logger;

public class NMEAMessageSocketClient {

	private static final Logger log = Logger.getLogger(NMEAMessageSocketClient.class.getName());

	@SuppressWarnings("unused")
	private NMEAMessageSocketClient() {
		this.socketAddress = null;
		this.nmeaMessageConsumer = null;
	}

	public NMEAMessageSocketClient(String host, Integer port, Consumer<? super NMEAMessage> nmeaMessageConsumer) throws UnknownHostException {
		InetAddress inetAddress = InetAddress.getByName(host);
		this.socketAddress = new InetSocketAddress(inetAddress, port);
		this.nmeaMessageConsumer = nmeaMessageConsumer;
	}

    @SuppressWarnings("unused")
	public void requestStop() {
		if (streamReader != null)
			streamReader.requestStop();
	}

	public void run() throws IOException {
	    log.info("NMEAMessageSocketClient running.");
	    Socket socket = new Socket();
	    socket.connect(socketAddress);
	    log.info("Connected to AIS server on " + socketAddress.toString());
	    InputStream inputStream = socket.getInputStream();
	    streamReader = new NMEAMessageInputStreamReader(inputStream, nmeaMessageConsumer);
	    streamReader.run();
	    // TODO: Close socket
	    log.info("NMEAMessageSocketClient stopping.");
	}

	private NMEAMessageInputStreamReader streamReader;
	private final SocketAddress socketAddress;
	private final Consumer<? super NMEAMessage> nmeaMessageConsumer;
}
