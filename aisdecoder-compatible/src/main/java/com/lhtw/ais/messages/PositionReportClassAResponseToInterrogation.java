

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

@SuppressWarnings("serial")
public class PositionReportClassAResponseToInterrogation extends PositionReport {
    public PositionReportClassAResponseToInterrogation(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected PositionReportClassAResponseToInterrogation(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    @Override
    public String toString() {
        return "PositionReportClassAResponseToInterrogation{" +
                "messageType=" + getMessageType() +
                "} " + super.toString();
    }

    @Override
    public AISMessageType getMessageType() {
        return AISMessageType.PositionReportClassAResponseToInterrogation;
    }
}
