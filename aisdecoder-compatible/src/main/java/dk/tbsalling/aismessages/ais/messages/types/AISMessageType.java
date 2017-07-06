package dk.tbsalling.aismessages.ais.messages.types;

public enum AISMessageType {

	PositionReportClassAScheduled(1),
	PositionReportClassAAssignedSchedule(2),
	PositionReportClassAResponseToInterrogation(3), 
	BaseStationReport(4), 
	ShipAndVoyageRelatedData(5), 
	AddressedBinaryMessage(6), 
	BinaryAcknowledge(7), 
	BinaryBroadcastMessage(8), 
	StandardSARAircraftPositionReport(9),
	UTCAndDateInquiry(10),
	UTCAndDateResponse(11),
	AddressedSafetyRelatedMessage(12),
	SafetyRelatedAcknowledge(13),
	SafetyRelatedBroadcastMessage(14),
	Interrogation(15), 
	AssignedModeCommand(16), 
	GNSSBinaryBroadcastMessage(17), 
	StandardClassBCSPositionReport(18), 
	ExtendedClassBEquipmentPositionReport(19), 
	DataLinkManagement(20), 
	AidToNavigationReport(21), 
	ChannelManagement(22), 
	GroupAssignmentCommand(23), 
	ClassBCSStaticDataReport(24), 
	BinaryMessageSingleSlot(25),
	BinaryMessageMultipleSlot(26),
	LongRangeBroadcastMessage(27),
	Error(-1);

	public final static int MINIMUM_CODE = 1;
	public final static int MAXIMUM_CODE = 27;

	AISMessageType(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public String getValue() {
	    return toString();
	}

	public static AISMessageType fromInteger(Integer integer) {
		if (integer != null) {
			for (AISMessageType b : AISMessageType.values()) {
				if (integer.equals(b.code)) {
					return b;
				}
			}
		}
		return null;
	}

	private final Integer code;
}
