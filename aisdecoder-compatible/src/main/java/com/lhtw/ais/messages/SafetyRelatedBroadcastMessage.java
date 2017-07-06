

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

import static dk.tbsalling.aismessages.ais.Decoders.STRING_DECODER;
import static dk.tbsalling.aismessages.ais.Decoders.UNSIGNED_INTEGER_DECODER;

@SuppressWarnings("serial")
public class SafetyRelatedBroadcastMessage extends AISMessage {

    public SafetyRelatedBroadcastMessage(NMEAMessage[] nmeaMessages) {
        super(nmeaMessages);
    }

    protected SafetyRelatedBroadcastMessage(NMEAMessage[] nmeaMessages, String bitString) {
        super(nmeaMessages, bitString);
    }

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.SafetyRelatedBroadcastMessage;
    }

    @SuppressWarnings("unused")
	public Integer getSpare() {
        return getDecodedValue(() -> spare, value -> spare = value, () -> Boolean.TRUE, () -> UNSIGNED_INTEGER_DECODER.apply(getBits(38, 40)));
	}

    @SuppressWarnings("unused")
	public final String getText() {
        return getDecodedValue(() -> text, value -> text = value, () -> Boolean.TRUE, () -> {
            int extraBitsOfChars = ((getNumberOfBits() - 40) / 6) * 6;
            return STRING_DECODER.apply(getBits(40, 40 + extraBitsOfChars));
        });
	}

    @Override
    public String toString() {
        return "SafetyRelatedBroadcastMessage{" +
                "messageType=" + getMessageType() +
                ", spare=" + getSpare() +
                ", text='" + getText() + '\'' +
                "} " + super.toString();
    }

    private transient Integer spare;
	private transient String text;
}
