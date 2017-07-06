package com.lhtw.ais.extra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.function.Consumer;
import java.util.logging.Logger;

import dk.tbsalling.aismessages.nmea.NMEAMessageInputStreamReader;
import dk.tbsalling.aismessages.nmea.exceptions.InvalidMessage;
import dk.tbsalling.aismessages.nmea.exceptions.NMEAParseException;
import dk.tbsalling.aismessages.nmea.exceptions.UnsupportedMessageType;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

public class INmeaMessageInputStreamReader {

	private static final Logger log = Logger.getLogger(NMEAMessageInputStreamReader.class.getName());

	public INmeaMessageInputStreamReader(InputStream inputStream, IConsumer nmeaMessageHandler) {
		this.nmeaMessageHandler = nmeaMessageHandler;
		this.inputStream = inputStream;
	}

	public final synchronized void requestStop() {
		this.stopRequested = true;
	}

	public void run() throws IOException {
//	    log.info("NMEAMessageInputStreamReader running.");

		InputStreamReader reader = new InputStreamReader(inputStream, Charset.defaultCharset());
		BufferedReader bufferedReader = new BufferedReader(reader);
		String string;
		while ((string = bufferedReader.readLine()) != null && !stopRequested()) {
			try {
				// by Override
				String headMessage = string.substring(0,string.indexOf("\\!") + 1);
				NMEAMessage nmea = NMEAMessage.fromString(string.substring(string.indexOf("\\!") + 1));
				nmeaMessageHandler.accept(nmea,headMessage);
				
//				log.fine("Received: " + nmea.toString());
			} catch (InvalidMessage invalidMessageException) {
				log.warning("Received invalid AIS message: \"" + string + "\"");
			} catch (UnsupportedMessageType unsupportedMessageTypeException) {
				log.warning("Received unsupported NMEA message: \"" + string + "\"");
			} catch (NMEAParseException parseException) {
				log.warning("Received non-compliant NMEA message: \"" + string + "\"");
			}
		}

//		log.info("NMEAMessageInputStreamReader stopping.");
	}

	private synchronized Boolean stopRequested() {
		return this.stopRequested;
	}

	private Boolean stopRequested = false;
	private final InputStream inputStream;
	private final IConsumer nmeaMessageHandler;

}
