

/**
 * 
 */
package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;


/**
 * @author tbsalling
 *
 */
@SuppressWarnings("serial")
public class PositionReportClassAScheduled extends PositionReport {
    public PositionReportClassAScheduled(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected PositionReportClassAScheduled(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    @Override
    public String toString() {
        return "PositionReportClassAScheduled{" +
                "messageType=" + getMessageType() +
                "} " + super.toString();
    }

    @Override
    public AISMessageType getMessageType() {
        return AISMessageType.PositionReportClassAScheduled;
    }
}
