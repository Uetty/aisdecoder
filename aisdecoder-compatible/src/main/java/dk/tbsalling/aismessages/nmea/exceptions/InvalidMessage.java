

package dk.tbsalling.aismessages.nmea.exceptions;

@SuppressWarnings("serial")
public class InvalidMessage extends RuntimeException {

	public InvalidMessage(String message) {
		super(message);
	}
	
}
