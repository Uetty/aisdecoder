

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.ais.messages.types.MMSI;
import dk.tbsalling.aismessages.ais.messages.types.TxRxMode;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import static dk.tbsalling.aismessages.ais.Decoders.BOOLEAN_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.FLOAT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

@SuppressWarnings("serial")
public class ChannelManagement extends AISMessage {

    public ChannelManagement(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected ChannelManagement(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.ChannelManagement;
    }

    @SuppressWarnings("unused")
	public Integer getChannelA() {
        return getDecodedValue(() -> channelA, value -> channelA = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(40, 52)));
	}

    @SuppressWarnings("unused")
	public Integer getChannelB() {
        return getDecodedValue(() -> channelB, value -> channelB = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(52, 64)));
	}

    @SuppressWarnings("unused")
	public TxRxMode getTransmitReceiveMode() {
        return getDecodedValue(() -> transmitReceiveMode, value -> transmitReceiveMode = value, () -> Boolean.TRUE, () -> TxRxMode.fromInteger(UNSIGNED_INTEGER_DECODER.apply(getBits(64, 68))));
	}

    @SuppressWarnings("unused")
	public Boolean getPower() {
        return getDecodedValue(() -> power, value -> power = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(68, 69)));
	}

    @SuppressWarnings("unused")
	public Float getNorthEastLongitude() {
        return getDecodedValue(() -> northEastLongitude, value -> northEastLongitude = value, () -> !getAddressed(), () -> FLOAT_DECODER.apply(getBits(69, 87)) / 10f);
	}

    @SuppressWarnings("unused")
	public Float getNorthEastLatitude() {
        return getDecodedValue(() -> northEastLatitude, value -> northEastLatitude = value, () -> !getAddressed(), () -> FLOAT_DECODER.apply(getBits(87, 104)) / 10f);
	}

    @SuppressWarnings("unused")
	public Float getSouthWestLongitude() {
        return getDecodedValue(() -> southWestLongitude, value -> southWestLongitude = value, () -> !getAddressed(), () -> FLOAT_DECODER.apply(getBits(104, 122)) / 10f);
	}

    @SuppressWarnings("unused")
	public Float getSouthWestLatitude() {
        return getDecodedValue(() -> southWestLatitude, value -> southWestLatitude = value, () -> !getAddressed(), () -> FLOAT_DECODER.apply(getBits(122, 138)) / 10f);
	}

    @SuppressWarnings("unused")
	public MMSI getDestinationMmsi1() {
        return getDecodedValue(() -> destinationMmsi1, value -> destinationMmsi1 = value, () -> getAddressed(), () -> MMSI.valueOf(UNSIGNED_INTEGER_DECODER.apply(getBits(69, 99))));
	}

    @SuppressWarnings("unused")
	public MMSI getDestinationMmsi2() {
        return getDecodedValue(() -> destinationMmsi2, value -> destinationMmsi2 = value, () -> getAddressed(), () -> MMSI.valueOf(UNSIGNED_INTEGER_DECODER.apply(getBits(104, 134))));
	}

    @SuppressWarnings("unused")
	public Boolean getAddressed() {
        return getDecodedValue(() -> addressed, value -> addressed = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(139, 140)));
	}

    @SuppressWarnings("unused")
	public Boolean getBandA() {
        return getDecodedValue(() -> bandA, value -> bandA = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(140, 141)));
	}

    @SuppressWarnings("unused")
	public Boolean getBandB() {
        return getDecodedValue(() -> bandB, value -> bandB = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(141, 142)));
	}

    @SuppressWarnings("unused")
	public Integer getZoneSize() {
        return getDecodedValue(() -> zoneSize, value -> zoneSize = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(142, 145)));
	}

    @Override
    public String toString() {
        return "ChannelManagement{" +
                "messageType=" + getMessageType() +
                ", channelA=" + getChannelA() +
                ", channelB=" + getChannelB() +
                ", transmitReceiveMode=" + getTransmitReceiveMode() +
                ", power=" + getPower() +
                ", northEastLongitude=" + getNorthEastLongitude() +
                ", northEastLatitude=" + getNorthEastLatitude() +
                ", southWestLongitude=" + getSouthWestLongitude() +
                ", southWestLatitude=" + getSouthWestLatitude() +
                ", destinationMmsi1=" + getDestinationMmsi1() +
                ", destinationMmsi2=" + getDestinationMmsi2() +
                ", addressed=" + getAddressed() +
                ", bandA=" + getBandA() +
                ", bandB=" + getBandB() +
                ", zoneSize=" + getZoneSize() +
                "} " + super.toString();
    }

    private transient Integer channelA;
    private transient Integer channelB;
    private transient TxRxMode transmitReceiveMode;
    private transient Boolean power;
    private transient Float northEastLongitude;
    private transient Float northEastLatitude;
    private transient Float southWestLongitude;
    private transient Float southWestLatitude;
    private transient MMSI destinationMmsi1;
    private transient MMSI destinationMmsi2;
    private transient Boolean addressed;
    private transient Boolean bandA;
    private transient Boolean bandB;
    private transient Integer zoneSize;
}
