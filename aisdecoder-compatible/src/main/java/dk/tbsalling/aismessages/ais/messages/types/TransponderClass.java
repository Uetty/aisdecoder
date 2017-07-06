

package dk.tbsalling.aismessages.ais.messages.types;

public enum TransponderClass {
	BS(0),  // Base station
	A(1),   // Class A equipment
	B(2),   // Class B equipment
	SAR(3); // Search and rescue airborne equipment

	TransponderClass(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}
	public String getValue() {
	    return toString();
	}

	private final int code;
}
