

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.ais.messages.types.CommunicationState;
import dk.tbsalling.aismessages.ais.messages.types.ITDMACommunicationState;
import dk.tbsalling.aismessages.ais.messages.types.SOTDMACommunicationState;
import dk.tbsalling.aismessages.ais.messages.types.TransponderClass;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import java.lang.ref.WeakReference;

import static dk.tbsalling.aismessages.ais.Decoders.BIT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.BOOLEAN_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.FLOAT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_FLOAT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

/**
 * A less detailed report than types 1-3 for vessels using Class B transmitters.
 * Omits navigational status and rate of turn.
 * 
 * @author tbsalling
 * 
 */
@SuppressWarnings("serial")
public class StandardClassBCSPositionReport extends AISMessage implements ExtendedDynamicDataReport {

    public StandardClassBCSPositionReport(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected StandardClassBCSPositionReport(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.StandardClassBCSPositionReport;
    }

    @Override
    public TransponderClass getTransponderClass() {
        return TransponderClass.B;
    }

    @SuppressWarnings("unused")
	public String getRegionalReserved1() {
        return getDecodedValue(() -> regionalReserved1, value -> regionalReserved1 = value, () -> Boolean.TRUE, () -> BIT_DECODER.apply(getBits(38, 46)));
	}

    @SuppressWarnings("unused")
	public Float getSpeedOverGround() {
        return getDecodedValue(() -> speedOverGround, value -> speedOverGround = value, () -> Boolean.TRUE, () -> UNSIGNED_FLOAT_DECODER.apply(getBits(46, 56)) / 10f);
	}

    @SuppressWarnings("unused")
	public Boolean getPositionAccurate() {
        return getDecodedValue(() -> positionAccurate, value -> positionAccurate = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(56, 57)));
	}

    @SuppressWarnings("unused")
	public Float getLatitude() {
        return getDecodedValue(() -> latitude, value -> latitude = value, () -> Boolean.TRUE, () -> FLOAT_DECODER.apply(getBits(85, 112)) / 600000f);
	}

    @SuppressWarnings("unused")
	public Float getLongitude() {
        return getDecodedValue(() -> longitude, value -> longitude = value, () -> Boolean.TRUE, () -> FLOAT_DECODER.apply(getBits(57, 85)) / 600000f);
	}

    @SuppressWarnings("unused")
	public Float getCourseOverGround() {
        return getDecodedValue(() -> courseOverGround, value -> courseOverGround = value, () -> Boolean.TRUE, () -> UNSIGNED_FLOAT_DECODER.apply(getBits(112, 124)) / 10f);
	}

    @SuppressWarnings("unused")
	public Integer getTrueHeading() {
        return getDecodedValue(() -> trueHeading, value -> trueHeading = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(124, 133)));
	}

    @SuppressWarnings("unused")
	public Integer getSecond() {
        return getDecodedValue(() -> second, value -> second = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(133, 139)));
	}

    @SuppressWarnings("unused")
	public String getRegionalReserved2() {
        return getDecodedValue(() -> regionalReserved2, value -> regionalReserved2 = value, () -> Boolean.TRUE, () -> BIT_DECODER.apply(getBits(139, 141)));
	}

    @SuppressWarnings("unused")
	public Boolean getCsUnit() {
        return getDecodedValue(() -> csUnit, value -> csUnit = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(141, 142)));
	}

    @SuppressWarnings("unused")
	public Boolean getDisplay() {
        return getDecodedValue(() -> display, value -> display = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(142, 143)));
	}

    @SuppressWarnings("unused")
	public Boolean getDsc() {
        return getDecodedValue(() -> dsc, value -> dsc = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(143, 144)));
	}

    @SuppressWarnings("unused")
	public Boolean getBand() {
        return getDecodedValue(() -> band, value -> band = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(144, 145)));
	}

    @SuppressWarnings("unused")
	public Boolean getMessage22() {
        return getDecodedValue(() -> message22, value -> message22 = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(145, 146)));
	}

    @SuppressWarnings("unused")
	public Boolean getAssigned() {
        return getDecodedValue(() -> assigned, value -> assigned = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(146, 147)));
	}

    @SuppressWarnings("unused")
	public Boolean getRaimFlag() {
        return getDecodedValue(() -> raimFlag, value -> raimFlag = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(147, 148)));
	}

    public Boolean getCommunicationStateSelectorFlag() {
        return getDecodedValue(() -> commStateSelectorFlag, value -> commStateSelectorFlag = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(148, 149)));
    }

    @SuppressWarnings("unused")
    public CommunicationState getCommunicationState() {
        if (getCommunicationStateSelectorFlag() == Boolean.FALSE)
            return getDecodedValueByWeakReference(() -> communicationState, value -> communicationState = value, () -> Boolean.TRUE, () -> SOTDMACommunicationState.fromBitString(getBits(149, 168)));
        else
            return getDecodedValueByWeakReference(() -> communicationState, value -> communicationState = value, () -> Boolean.TRUE, () -> ITDMACommunicationState.fromBitString(getBits(149, 168)));
    }

    @Override
    public String toString() {
        return "StandardClassBCSPositionReport{" +
                "messageType=" + getMessageType() +
                ", regionalReserved1='" + getRegionalReserved1() + '\'' +
                ", speedOverGround=" + getSpeedOverGround() +
                ", positionAccurate=" + getPositionAccurate() +
                ", latitude=" + getLatitude() +
                ", longitude=" + getLongitude() +
                ", courseOverGround=" + getCourseOverGround() +
                ", trueHeading=" + getTrueHeading() +
                ", second=" + getSecond() +
                ", regionalReserved2='" + getRegionalReserved2() + '\'' +
                ", csUnit=" + getCsUnit() +
                ", display=" + getDisplay() +
                ", dsc=" + getDsc() +
                ", band=" + getBand() +
                ", message22=" + getMessage22() +
                ", assigned=" + getAssigned() +
                ", raimFlag=" + getRaimFlag() +
                ", commStateSelectorFlag=" + getCommunicationStateSelectorFlag() +
                ", commState=" + getCommunicationState() +
                "} " + super.toString();
    }

    /**
     * 备用
     */
    private transient String regionalReserved1;
	private transient Float speedOverGround;
	private transient Boolean positionAccurate;
	private transient Float latitude;
	private transient Float longitude;
	private transient Float courseOverGround;
	private transient Integer trueHeading;
	private transient Integer second;
	/**
	 * 备用
	 */
	private transient String regionalReserved2;
	private transient Boolean csUnit;
	private transient Boolean display;
	private transient Boolean dsc;
	private transient Boolean band;
	private transient Boolean message22;
	private transient Boolean assigned;
	private transient Boolean raimFlag;
	private transient Boolean commStateSelectorFlag;
	private transient WeakReference<CommunicationState> communicationState;

}
