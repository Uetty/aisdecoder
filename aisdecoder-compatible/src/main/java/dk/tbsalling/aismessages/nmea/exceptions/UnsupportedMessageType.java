

package dk.tbsalling.aismessages.nmea.exceptions;

@SuppressWarnings("serial")
public class UnsupportedMessageType extends RuntimeException {

	public UnsupportedMessageType(String message) {
		super(message);
	}
	
}
