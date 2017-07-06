

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.ais.messages.types.MMSI;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

@SuppressWarnings("serial")
public class UTCAndDateInquiry extends AISMessage {

    public UTCAndDateInquiry(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected UTCAndDateInquiry(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.UTCAndDateInquiry;
    }

    @SuppressWarnings("unused")
	public MMSI getDestinationMmsi() {
        return getDecodedValue(() -> destinationMmsi, value -> destinationMmsi = value, () -> Boolean.TRUE, () -> MMSI.valueOf(UNSIGNED_INTEGER_DECODER.apply(getBits(40, 70))));
	}

    @Override
    public String toString() {
        return "UTCAndDateInquiry{" +
                "messageType=" + getMessageType() +
                ", destinationMmsi=" + getDestinationMmsi() +
                "} " + super.toString();
    }

    private transient MMSI destinationMmsi;
}
