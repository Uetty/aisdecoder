

package com.lhtw.ais.messages;

import static dk.tbsalling.aismessages.ais.Decoders.BIT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.BOOLEAN_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

import java.lang.ref.WeakReference;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.ais.messages.types.MMSI;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

/**
 * an addressed point-to-point message with unspecified binary payload. The St.
 * Lawrence Seaway AIS system and the USG PAWSS system use this payload for
 * local extension messages. It is variable in length up to a maximum of 1008
 * bits (up to 5 AIVDM sentence payloads).
 * 
 * @author tbsalling
 * 
 */
@SuppressWarnings("serial")
public class AddressedBinaryMessage extends AISMessage {

    public AddressedBinaryMessage(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected AddressedBinaryMessage(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.AddressedBinaryMessage;
    }

    @SuppressWarnings("unused")
    public Integer getSequenceNumber() {
        return getDecodedValue(() -> sequenceNumber, ref -> sequenceNumber = ref, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(38, 40)));
	}

    @SuppressWarnings("unused")
	public MMSI getDestinationMmsi() {
        return getDecodedValue(() -> destinationMmsi, ref -> destinationMmsi = ref, () -> Boolean.TRUE, () -> MMSI.valueOf(UNSIGNED_INTEGER_DECODER.apply(getBits(40, 70))));
	}

    @SuppressWarnings("unused")
	public Boolean getRetransmit() {
        return getDecodedValue(() -> retransmit, ref -> retransmit = ref, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(70, 71)));
	}

    @SuppressWarnings("unused")
	public int getSpare() {
        return getDecodedValue(() -> spare, ref -> spare = ref, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(71, 72)));
	}

    @SuppressWarnings("unused")
	public Integer getDesignatedAreaCode() {
        return getDecodedValue(() -> designatedAreaCode, ref -> designatedAreaCode = ref, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(72, 82)));
	}

    @SuppressWarnings("unused")
	public Integer getFunctionalId() {
        return getDecodedValue(() -> functionalId, ref -> functionalId = ref, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(82, 88)));
	}

    @SuppressWarnings("unused")
	public String getBinaryData() {
        return getDecodedValueByWeakReference(() -> binaryData, ref -> binaryData = ref, () -> Boolean.TRUE, () -> BIT_DECODER.apply(getBits(88, getNumberOfBits())));
	}

    @Override
    public String toString() {
        return "AddressedBinaryMessage{" +
                "messageType=" + getMessageType() +
                ", sequenceNumber=" + getSequenceNumber() +
                ", destinationMmsi=" + getDestinationMmsi() +
                ", retransmit=" + getRetransmit() +
                ", spare=" + getSpare() +
                ", designatedAreaCode=" + getDesignatedAreaCode() +
                ", functionalId=" + getFunctionalId() +
                ", binaryData='" + getBinaryData() + '\'' +
                "} " + super.toString();
    }

    private transient Integer sequenceNumber;
    private transient MMSI destinationMmsi;
    private transient Boolean retransmit;
    private transient Integer spare;
    private transient Integer designatedAreaCode;
    private transient Integer functionalId;
    private transient WeakReference<String> binaryData;
}
