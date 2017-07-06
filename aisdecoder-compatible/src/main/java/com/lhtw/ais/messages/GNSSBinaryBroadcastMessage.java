

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import static dk.tbsalling.aismessages.ais.Decoders.BIT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.FLOAT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

/**
 * used to broadcast differential corrections for GPS. The data in the payload
 * is intended to be passed directly to GPS receivers capable of accepting such
 * corrections.
 * 
 * @author tbsalling
 */
@SuppressWarnings("serial")
public class GNSSBinaryBroadcastMessage extends AISMessage {

    public GNSSBinaryBroadcastMessage(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected GNSSBinaryBroadcastMessage(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.GNSSBinaryBroadcastMessage;
    }

    @SuppressWarnings("unused")
	public Integer getSpare1() {
        return getDecodedValue(() -> spare1, value -> spare1 = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(38, 40)));
	}

    @SuppressWarnings("unused")
	public Float getLatitude() {
        return getDecodedValue(() -> latitude, value -> latitude = value, () -> Boolean.TRUE, () -> FLOAT_DECODER.apply(getBits(58, 75)) / 10f);
	}

    @SuppressWarnings("unused")
	public Float getLongitude() {
        return getDecodedValue(() -> longitude, value -> longitude = value, () -> Boolean.TRUE, () -> FLOAT_DECODER.apply(getBits(40, 58)) / 10f);
	}

    @SuppressWarnings("unused")
	public Integer getSpare2() {
        return getDecodedValue(() -> spare2, value -> spare2 = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(75, 80)));
	}

    @SuppressWarnings("unused")
	public Integer getMType() {
        return getDecodedValue(() -> mType, value -> mType = value, () -> getNumberOfBits() > 80, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(80, 86)));
	}

    @SuppressWarnings("unused")
	public Integer getStationId() {
        return getDecodedValue(() -> stationId, value -> stationId = value, () -> getNumberOfBits() > 80, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(86, 96)));
	}

    @SuppressWarnings("unused")
	public Integer getZCount() {
        return getDecodedValue(() -> zCount, value -> zCount = value, () -> getNumberOfBits() > 80, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(96, 109)));
	}

    @SuppressWarnings("unused")
	public Integer getSequenceNumber() {
        return getDecodedValue(() -> sequenceNumber, value -> sequenceNumber = value, () -> getNumberOfBits() > 80, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(109, 112)));
	}

    @SuppressWarnings("unused")
	public Integer getNumOfWords() {
        return getDecodedValue(() -> numOfWords, value -> numOfWords = value, () -> getNumberOfBits() > 80, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(112, 117)));
	}

    @SuppressWarnings("unused")
	public Integer getHealth() {
        return getDecodedValue(() -> health, value -> health = value, () -> getNumberOfBits() > 80, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(117, 120)));
	}

    @SuppressWarnings("unused")
	public String getBinaryData() {
        return getDecodedValue(() -> binaryData, value -> binaryData = value, () -> getNumberOfBits() > 80, () -> BIT_DECODER.apply(getBits(80, getNumberOfBits())));
	}

    @Override
    public String toString() {
        return "GNSSBinaryBroadcastMessage{" +
                "messageType=" + getMessageType() +
                ", spare1=" + getSpare1() +
                ", latitude=" + getLatitude() +
                ", longitude=" + getLongitude() +
                ", spare2=" + getSpare2() +
                ", mType=" + getMType() +
                ", stationId=" + getStationId() +
                ", zCount=" + getZCount() +
                ", sequenceNumber=" + getSequenceNumber() +
                ", numOfWords=" + getNumOfWords() +
                ", health=" + getHealth() +
                ", binaryData='" + getBinaryData() + '\'' +
                "} " + super.toString();
    }

    private transient Integer spare1;
    private transient Float latitude;
    private transient Float longitude;
    private transient Integer spare2;
    private transient Integer mType;
    private transient Integer stationId;
    private transient Integer zCount;
    private transient Integer sequenceNumber;
    private transient Integer numOfWords;
    private transient Integer health;
    private transient String binaryData;
}
