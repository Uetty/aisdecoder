

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import static dk.tbsalling.aismessages.ais.Decoders.BIT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

/**
 * broadcast message with unspecified binary payload. The St. Lawrence Seaway
 * AIS system and the USG PAWSS system use this payload for local extension
 * messages. It is variable in length up to a maximum of 1008 bits (up to 5
 * AIVDM sentence payloads).
 * 
 * @author tbsalling
 * 
 */
@SuppressWarnings("serial")
public class BinaryBroadcastMessage extends AISMessage {

    public BinaryBroadcastMessage(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected BinaryBroadcastMessage(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.BinaryBroadcastMessage;
    }

    @SuppressWarnings("unused")
	public Integer getDesignatedAreaCode() {
        return getDecodedValue(() -> designatedAreaCode, value -> designatedAreaCode = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(40, 50)));
	}

    @SuppressWarnings("unused")
	public Integer getFunctionalId() {
        return getDecodedValue(() -> functionalId, value -> functionalId = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(50, 56)));
	}

    @SuppressWarnings("unused")
	public String getBinaryData() {
        return getDecodedValue(() -> binaryData, value -> binaryData = value, () -> Boolean.TRUE, () -> BIT_DECODER.apply(getBits(52, 56)));
	}

    @Override
    public String toString() {
        return "BinaryBroadcastMessage{" +
                "messageType=" + getMessageType() +
                ", designatedAreaCode=" + getDesignatedAreaCode() +
                ", functionalId=" + getFunctionalId() +
                ", binaryData='" + getBinaryData() + '\'' +
                "} " + super.toString();
    }

    private transient Integer designatedAreaCode;
	private transient Integer functionalId;
	private transient String binaryData;
}
