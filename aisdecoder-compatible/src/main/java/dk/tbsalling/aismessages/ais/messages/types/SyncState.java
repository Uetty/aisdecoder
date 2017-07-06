

package dk.tbsalling.aismessages.ais.messages.types;

public enum SyncState {
	UTCDirect(0),
	UTCIndirect(1),
	BaseDirect(2),
	BaseIndirect(3);

	SyncState(Integer code) {
		this.code = code;
	}
	
	public Integer getCode() {
		return code;
	}

	public String getValue() {
	    return toString();
	}

	private final Integer code;

	public static SyncState fromInteger(Integer integer) {
		if (integer != null) {
			for (SyncState b : SyncState.values()) {
				if (integer.equals(b.code)) {
					return b;
				}
			}
		}
		return null;
	}
}
