

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

@SuppressWarnings("serial")
public class PositionReportClassAAssignedSchedule extends PositionReport {
    public PositionReportClassAAssignedSchedule(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected PositionReportClassAAssignedSchedule(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    @Override
    public String toString() {
        return "PositionReportClassAAssignedSchedule{" +
                "messageType=" + getMessageType() +
                "} " + super.toString();
    }

    @Override
    public AISMessageType getMessageType() {
        return AISMessageType.PositionReportClassAAssignedSchedule;
    }
}
