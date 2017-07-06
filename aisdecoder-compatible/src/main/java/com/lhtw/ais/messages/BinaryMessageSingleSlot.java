

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.ais.messages.types.MMSI;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import static dk.tbsalling.aismessages.ais.Decoders.BIT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.BOOLEAN_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

@SuppressWarnings("serial")
public class BinaryMessageSingleSlot extends AISMessage {

    public BinaryMessageSingleSlot(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected BinaryMessageSingleSlot(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.BinaryMessageSingleSlot;
    }

    @SuppressWarnings("unused")
	public Boolean getDestinationIndicator() {
        return getDecodedValue(() -> destinationIndicator, value -> destinationIndicator = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(38, 39)));
	}

    @SuppressWarnings("unused")
	public Boolean getBinaryDataFlag() {
        return getDecodedValue(() -> binaryDataFlag, value -> binaryDataFlag = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(39, 40)));
	}

    @SuppressWarnings("unused")
	public MMSI getDestinationMMSI() {
        return getDecodedValue(() -> destinationMMSI, value -> destinationMMSI = value, () -> Boolean.TRUE, () -> MMSI.valueOf(UNSIGNED_INTEGER_DECODER.apply(getBits(40, 70))));
	}

    @SuppressWarnings("unused")
	public String getBinaryData() {
        return getDecodedValue(() -> binaryData, value -> binaryData = value, () -> Boolean.TRUE, () -> BIT_DECODER.apply(getBits(40, 168)));
	}

    @Override
    public String toString() {
        return "BinaryMessageSingleSlot{" +
                "messageType=" + getMessageType() +
                ", destinationIndicator=" + getDestinationIndicator() +
                ", destinationMMSI=" + getDestinationMMSI() +
                ", binaryDataFlag=" + getBinaryDataFlag() +
                ", binaryData='" + getBinaryData() + '\'' +
                "} " + super.toString();
    }

    private transient Boolean destinationIndicator;
    private transient Boolean binaryDataFlag;
    private transient MMSI destinationMMSI;
    private transient String binaryData;
}
