

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.AISMessageType;

/**
 * Identification and location message to be emitted by aids to navigation such as buoys and lighthouses.
 * @author tbsalling
 *
 */
@SuppressWarnings("serial")
public class Error extends AISMessage {

    protected void checkAISMessage() {
    }

    public final AISMessageType getMessageType() {
        return AISMessageType.Error;
    }

    public Error(String rawMessage, String errorDescription) {
        super();
        this.rawMessage = rawMessage;
		this.errorDescription = errorDescription;
	}

    @Override
    public String toString() {
        return "Error{" +
                "messageType=" + getMessageType() +
                ", rawMessage='" + rawMessage + '\'' +
                ", errorDescription='" + errorDescription + '\'' +
                "} " + super.toString();
    }

    @SuppressWarnings("unused")
    public String getRawMessage() {
        return rawMessage;
    }

    @SuppressWarnings("unused")
	public String getErrorDescription() {
		return errorDescription;
	}

    private final String rawMessage;
	private final String errorDescription;
}
