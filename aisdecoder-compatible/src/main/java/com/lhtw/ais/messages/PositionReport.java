

/**
 * 
 */
package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.CommunicationState;
import dk.tbsalling.aismessages.ais.messages.types.ITDMACommunicationState;
import dk.tbsalling.aismessages.ais.messages.types.ManeuverIndicator;
import dk.tbsalling.aismessages.ais.messages.types.NavigationStatus;
import dk.tbsalling.aismessages.ais.messages.types.SOTDMACommunicationState;
import dk.tbsalling.aismessages.ais.messages.types.TransponderClass;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import java.lang.ref.WeakReference;

import static dk.tbsalling.aismessages.ais.Decoders.BOOLEAN_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.FLOAT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.INTEGER_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_FLOAT_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

/**
 * @author tbsalling
 *
 */
@SuppressWarnings("serial")
public abstract class PositionReport extends AISMessage implements ExtendedDynamicDataReport {

    public PositionReport(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected PositionReport(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    @Override
    public TransponderClass getTransponderClass() {
        return TransponderClass.A;
    }

    @SuppressWarnings("unused")
	public NavigationStatus getNavigationStatus() {
        return getDecodedValue(() -> navigationStatus, value -> navigationStatus = value, () -> Boolean.TRUE, () -> NavigationStatus.fromInteger(UNSIGNED_INTEGER_DECODER.apply(getBits(38, 42))));
	}

    @SuppressWarnings("unused")
	public Integer getRateOfTurn() {
        // TODO Square-root
        return getDecodedValue(() -> rateOfTurn, value -> rateOfTurn = value, () -> Boolean.TRUE, () -> INTEGER_DECODER.apply(getBits(42, 50)));
	}

    @SuppressWarnings("unused")
	public Float getSpeedOverGround() {
        return getDecodedValue(() -> speedOverGround, value -> speedOverGround = value, () -> Boolean.TRUE, () -> UNSIGNED_FLOAT_DECODER.apply(getBits(50, 60)) / 10f);
	}

    @SuppressWarnings("unused")
	public Boolean getPositionAccuracy() {
        return getDecodedValue(() -> positionAccuracy, value -> positionAccuracy = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(60, 61)));
	}

    @SuppressWarnings("unused")
	public Float getLatitude() {
        return getDecodedValue(() -> latitude, value -> latitude = value, () -> Boolean.TRUE, () -> FLOAT_DECODER.apply(getBits(89, 116)) / 600000f);
	}

    @SuppressWarnings("unused")
	public Float getLongitude() {
        return getDecodedValue(() -> longitude, value -> longitude = value, () -> Boolean.TRUE, () -> FLOAT_DECODER.apply(getBits(61, 89)) / 600000f);
	}

    @SuppressWarnings("unused")
	public Float getCourseOverGround() {
        return getDecodedValue(() -> courseOverGround, value -> courseOverGround = value, () -> Boolean.TRUE, () -> UNSIGNED_FLOAT_DECODER.apply(getBits(116, 128)) / 10f);
	}

    @SuppressWarnings("unused")
	public Integer getTrueHeading() {
        return getDecodedValue(() -> trueHeading, value -> trueHeading = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(128, 137)));
	}

    @SuppressWarnings("unused")
	public Integer getSecond() {
        return getDecodedValue(() -> second, value -> second = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(137, 143)));
	}

    @SuppressWarnings("unused")
	public ManeuverIndicator getSpecialManeuverIndicator() {
        return getDecodedValue(() -> specialManeuverIndicator, value -> specialManeuverIndicator = value, () -> Boolean.TRUE, () -> ManeuverIndicator.fromInteger(UNSIGNED_INTEGER_DECODER.apply(getBits(143, 145))));
	}

    @SuppressWarnings("unused")
	public Boolean getRaimFlag() {
        return getDecodedValue(() -> raimFlag, value -> raimFlag = value, () -> Boolean.TRUE, () -> BOOLEAN_DECODER.apply(getBits(148, 149)));
	}

    @SuppressWarnings("unused")
    public CommunicationState getCommunicationState() {
        if (this instanceof PositionReportClassAScheduled || this instanceof PositionReportClassAAssignedSchedule)
            return getDecodedValueByWeakReference(() -> communicationState, value -> communicationState = value, () -> Boolean.TRUE, () -> SOTDMACommunicationState.fromBitString(getBits(149, 168)));
        else if (this instanceof PositionReportClassAResponseToInterrogation)
            return getDecodedValueByWeakReference(() -> communicationState, value -> communicationState = value, () -> Boolean.TRUE, () -> ITDMACommunicationState.fromBitString(getBits(149, 168)));
        else
            return null;
    }

    @Override
    public String toString() {
        return "PositionReport{" +
                "navigationStatus=" + getNavigationStatus() +
                ", rateOfTurn=" + getRateOfTurn() +
                ", speedOverGround=" + getSpeedOverGround() +
                ", positionAccuracy=" + getPositionAccuracy() +
                ", latitude=" + getLatitude() +
                ", longitude=" + getLongitude() +
                ", courseOverGround=" + getCourseOverGround() +
                ", trueHeading=" + getTrueHeading() +
                ", second=" + getSecond() +
                ", specialManeuverIndicator=" + getSpecialManeuverIndicator() +
                ", raimFlag=" + getRaimFlag() +
                "} " + super.toString();
    }

    private transient NavigationStatus navigationStatus;
	private transient Integer rateOfTurn;
	private transient Float speedOverGround;
	private transient Boolean positionAccuracy;
	private transient Float latitude;
	private transient Float longitude;
	private transient Float courseOverGround;
	private transient Integer trueHeading;
	private transient Integer second;
	private transient ManeuverIndicator specialManeuverIndicator;
	private transient Boolean raimFlag;
	private transient WeakReference<CommunicationState> communicationState;
}
