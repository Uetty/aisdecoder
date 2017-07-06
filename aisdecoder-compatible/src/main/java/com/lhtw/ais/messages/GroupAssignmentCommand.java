

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.ais.messages.types.ReportingInterval;
import dk.tbsalling.aismessages.ais.messages.types.ShipType;
import dk.tbsalling.aismessages.ais.messages.types.StationType;
import dk.tbsalling.aismessages.ais.messages.types.TxRxMode;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import static dk.tbsalling.aismessages.ais.Decoders.FLOAT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.STRING_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

/**
 * intended to be broadcast by a competent authority (an AIS network-control
 * base station) to set to set operational parameters for all mobile stations in
 * an AIS coverage region
 * 
 * @author tbsalling
 */
@SuppressWarnings("serial")
public class GroupAssignmentCommand extends AISMessage {

    public GroupAssignmentCommand(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected GroupAssignmentCommand(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.GroupAssignmentCommand;
    }

    @SuppressWarnings("unused")
    public String getSpare1() {
        return getDecodedValue(() -> spare1, value -> spare1 = value, () -> Boolean.TRUE, () -> STRING_DECODER.apply(getBits(38, 40)));
    }

    @SuppressWarnings("unused")
    public Float getNorthEastLongitude() {
        return getDecodedValue(() -> northEastLongitude, value -> northEastLongitude = value, () -> Boolean.TRUE, () -> FLOAT_DECODER.apply(getBits(40, 58)) / 10f);
    }

    @SuppressWarnings("unused")
	public Float getNorthEastLatitude() {
        return getDecodedValue(() -> northEastLatitude, value -> northEastLatitude = value, () -> Boolean.TRUE, () -> FLOAT_DECODER.apply(getBits(58, 75)) / 10f);
	}

    @SuppressWarnings("unused")
	public Float getSouthWestLongitude() {
        return getDecodedValue(() -> southWestLongitude, value -> southWestLongitude = value, () -> Boolean.TRUE, () -> FLOAT_DECODER.apply(getBits(75, 93)) / 10f);
	}

    @SuppressWarnings("unused")
    public Float getSouthWestLatitude() {
        return getDecodedValue(() -> southWestLatitude, value -> southWestLatitude = value, () -> Boolean.TRUE, () -> FLOAT_DECODER.apply(getBits(93, 110)) / 10f);
    }

    @SuppressWarnings("unused")
	public StationType getStationType() {
        return getDecodedValue(() -> stationType, value -> stationType = value, () -> Boolean.TRUE, () -> StationType.fromInteger(UNSIGNED_INTEGER_DECODER.apply(getBits(110, 114))));
	}

    @SuppressWarnings("unused")
	public ShipType getShipType() {
        return getDecodedValue(() -> shipType, value -> shipType = value, () -> Boolean.TRUE, () -> ShipType.fromInteger(UNSIGNED_INTEGER_DECODER.apply(getBits(114, 122))));
	}

    @SuppressWarnings("unused")
    public String getSpare2() {
        return getDecodedValue(() -> spare2, value -> spare2 = value, () -> Boolean.TRUE, () -> STRING_DECODER.apply(getBits(122, 166)));
    }

    @SuppressWarnings("unused")
	public TxRxMode getTransmitReceiveMode() {
        return getDecodedValue(() -> transmitReceiveMode, value -> transmitReceiveMode = value, () -> Boolean.TRUE, () -> TxRxMode.fromInteger(UNSIGNED_INTEGER_DECODER.apply(getBits(166, 168))));
	}

    @SuppressWarnings("unused")
	public ReportingInterval getReportingInterval() {
        return getDecodedValue(() -> reportingInterval, value -> reportingInterval = value, () -> Boolean.TRUE, () -> ReportingInterval.fromInteger(UNSIGNED_INTEGER_DECODER.apply(getBits(168, 172))));
	}

    @SuppressWarnings("unused")
	public Integer getQuietTime() {
        return getDecodedValue(() -> quietTime, value -> quietTime = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(172, 176)));
	}

    @Override
    public String toString() {
        return "GroupAssignmentCommand{" +
                "messageType=" + getMessageType() +
                ", spare1='" + getSpare1() + '\'' +
                ", northEastLatitude=" + getNorthEastLatitude() +
                ", northEastLongitude=" + getNorthEastLongitude() +
                ", southWestLatitude=" + getSouthWestLatitude() +
                ", southWestLongitude=" + getSouthWestLongitude() +
                ", stationType=" + getStationType() +
                ", shipType=" + getShipType() +
                ", transmitReceiveMode=" + getTransmitReceiveMode() +
                ", reportingInterval=" + getReportingInterval() +
                ", quietTime=" + getQuietTime() +
                ", spare2='" + getSpare2() + '\'' +
                "} " + super.toString();
    }

    private transient String spare1;
    private transient Float northEastLatitude;
    private transient Float northEastLongitude;
    private transient Float southWestLatitude;
    private transient Float southWestLongitude;
    private transient StationType stationType;
    private transient ShipType shipType;
    private transient TxRxMode transmitReceiveMode;
    private transient ReportingInterval reportingInterval;
    private transient Integer quietTime;
    private transient String spare2;
}
