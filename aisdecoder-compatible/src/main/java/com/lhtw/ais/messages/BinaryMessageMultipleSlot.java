

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.ais.messages.types.MMSI;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import static dk.tbsalling.aismessages.ais.Decoders.BIT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.BOOLEAN_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

@SuppressWarnings("serial")
public class BinaryMessageMultipleSlot extends AISMessage {

    public BinaryMessageMultipleSlot(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected BinaryMessageMultipleSlot(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.BinaryMessageMultipleSlot;
    }

    @SuppressWarnings("unused")
    public Boolean getAddressed() {
        return getDecodedValue(() -> addressed, value -> addressed = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(38, 39)));
    }

    @SuppressWarnings("unused")
    public Boolean getStructured() {
        return getDecodedValue(() -> structured, value -> structured = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(39, 40)));
    }

    @SuppressWarnings("unused")
    public MMSI getDestinationMmsi() {
        return getDecodedValue(() -> destinationMmsi, value -> destinationMmsi = value, () -> Boolean.TRUE, () -> MMSI.valueOf(UNSIGNED_INTEGER_DECODER.apply(getBits(40, 70))));
    }

    @SuppressWarnings("unused")
    public Integer getApplicationId() {
        return getDecodedValue(() -> applicationId, value -> applicationId = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(70, 86)));
    }

    @SuppressWarnings("unused")
    public String getData() {
        return getDecodedValue(() -> data, value -> data = value, () -> Boolean.TRUE, () -> BIT_DECODER.apply(getBits(86, 86 + 1004 + 1)));
    }

    @SuppressWarnings("unused")
    public String getRadioStatus() {
        return null; // BIT_DECODER.apply(getBits(6, 8));
    }

    @Override
    public String toString() {
        return "BinaryMessageMultipleSlot{" +
                "messageType=" + getMessageType() +
                ", addressed=" + getAddressed() +
                ", structured=" + getStructured() +
                ", destinationMmsi=" + getDestinationMmsi() +
                ", applicationId=" + getApplicationId() +
                ", data='" + getData() + '\'' +
                ", radioStatus='" + getRadioStatus() + '\'' +
                "} " + super.toString();
    }

    private transient Boolean addressed;
    private transient Boolean structured;
    private transient MMSI destinationMmsi;
    private transient Integer applicationId;
    private transient String data;
    // private transient String radioStatus;
}
