

package dk.tbsalling.aismessages.ais.messages.types;

public enum StationType {
	AllTypesOfMobileStations(0),
	ClassAMobileStation(1),
	AllTypesOfClassBMobileStations(2),
	SARAirborneMobileStation(3),
	ClassBSOMobileStation(4),
	ClassBCSShipborneMobileStation(5),
	InlandWaterways(6),
	RegionalUse1(7),
	RegionalUse2(8),
	RegionalUse3(9),
	BaseStationCoverageArea(10),
	FutureUse1(11),
	FutureUse2(12),
	FutureUse3(13),
	FutureUse4(14),
	FutureUse5(15);
	
	StationType(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}

	public String getValue() {
	    return toString();
	}

	private final Integer code;

	public static StationType fromInteger(Integer integer) {
		if (integer != null) {
			for (StationType b : StationType.values()) {
				if (integer.equals(b.code)) {
					return b;
				}
			}
		}
		return null;
	}
}
