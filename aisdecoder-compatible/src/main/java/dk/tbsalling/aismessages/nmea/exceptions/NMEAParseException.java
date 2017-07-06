

package dk.tbsalling.aismessages.nmea.exceptions;

@SuppressWarnings("serial")
public class NMEAParseException extends RuntimeException {

	public NMEAParseException(String rawMessage, String errorDescription) {
		this.rawMessage = rawMessage;
		this.errorDescription = errorDescription;
	}
	
	@Override
	public String getMessage() {
		return this.errorDescription + ": " + rawMessage;
	}

	public final String getRawMessage() {
		return rawMessage;
	}

	public final String getErrorDescription() {
		return errorDescription;
	}

	final String rawMessage;
	final String errorDescription;
	
}
