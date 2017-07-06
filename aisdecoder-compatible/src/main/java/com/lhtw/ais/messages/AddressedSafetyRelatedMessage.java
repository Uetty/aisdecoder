

package com.lhtw.ais.messages;

import static dk.tbsalling.aismessages.ais.Decoders.BOOLEAN_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.STRING_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.ais.messages.types.MMSI;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

@SuppressWarnings("serial")
public class AddressedSafetyRelatedMessage extends AISMessage {

    public AddressedSafetyRelatedMessage(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected AddressedSafetyRelatedMessage(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public AISMessageType getMessageType() {
        return AISMessageType.AddressedSafetyRelatedMessage;
    }

    @SuppressWarnings("unused")
	public Integer getSequenceNumber() {
        return getDecodedValue(() -> sequenceNumber, value -> sequenceNumber=value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(38, 40)));
	}

    @SuppressWarnings("unused")
	public MMSI getDestinationMmsi() {
        return getDecodedValue(() -> destinationMmsi, value -> destinationMmsi = value, () -> Boolean.TRUE, () -> MMSI.valueOf(UNSIGNED_INTEGER_DECODER.apply(getBits(40, 70))));
	}

    @SuppressWarnings("unused")
	public Boolean getRetransmit() {
        return getDecodedValue(() -> retransmit, value -> retransmit = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(70, 71)));
	}

    @SuppressWarnings("unused")
	public Integer getSpare() {
        return getDecodedValue(() -> spare, value -> spare = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(71, 72)));
	}

    @SuppressWarnings("unused")
	public String getText() {
        return getDecodedValue(() -> text, value -> text = value, () -> Boolean.TRUE, () -> {
            int extraBitsOfChars = ((getNumberOfBits() - 72)/6)*6;
            return STRING_DECODER.apply(getBits(72, 72 + extraBitsOfChars));
        });
	}

    @Override
    public String toString() {
        return "AddressedSafetyRelatedMessage{" +
                "messageType=" + getMessageType() +
                ", sequenceNumber=" + getSequenceNumber() +
                ", destinationMmsi=" + getDestinationMmsi() +
                ", retransmit=" + getRetransmit() +
                ", spare=" + getSpare() +
                ", text='" + getText() + '\'' +
                "} " + super.toString();
    }

    private transient Integer sequenceNumber;
    private transient MMSI destinationMmsi;
    private transient Boolean retransmit;
    private transient Integer spare;
    private transient String text;
}
