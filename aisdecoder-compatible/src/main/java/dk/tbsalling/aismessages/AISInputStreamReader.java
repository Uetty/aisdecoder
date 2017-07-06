

package dk.tbsalling.aismessages;

import dk.tbsalling.aismessages.nmea.NMEAMessageHandler;
import dk.tbsalling.aismessages.nmea.NMEAMessageInputStreamReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import com.lhtw.ais.messages.AISMessage;

/**
 * AISMessageInputStreamReader is the main entry point into the program loop
 * of an AISMessages application.
 *
 * Feed it with a stream of NMEA messages, and get your AISMessage consumer called
 * with decoded messages, whenever they are extracted from the NMEA stream.
 *
 * @see dk.tbsalling.aismessages.demo.SimpleDemoApp
 */
public class AISInputStreamReader {

	public AISInputStreamReader(InputStream inputStream, Consumer<? super AISMessage> aisMessageConsumer) {
        this.nmeaMessageHandler = new NMEAMessageHandler("SRC", aisMessageConsumer);
        this.nmeaMessageInputStreamReader = new NMEAMessageInputStreamReader(inputStream, this.nmeaMessageHandler::accept);
	}

	public final synchronized void requestStop() {
		this.stopRequested = true;
	}

    public final synchronized boolean isStopRequested() {
        return stopRequested;
    }

    public void run() throws IOException {
        this.nmeaMessageInputStreamReader.run();
	}

	private boolean stopRequested = false;
    private final NMEAMessageHandler nmeaMessageHandler;
	private final NMEAMessageInputStreamReader nmeaMessageInputStreamReader;
}
