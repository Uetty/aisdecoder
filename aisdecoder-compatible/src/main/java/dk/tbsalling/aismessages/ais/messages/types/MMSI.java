

package dk.tbsalling.aismessages.ais.messages.types;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MMSI implements Serializable {

	public MMSI(int mmsi) {
		this.mmsi = mmsi;
	}
	
	public static MMSI valueOf(int mmsi) {
		return new MMSI(mmsi);
	}
	
	@Override
	public String toString() {
		return "MMSI [mmsi=" + mmsi + "]";
	}

	public Integer getMMSI() {
	    return Integer.valueOf(mmsi);
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MMSI mmsi1 = (MMSI) o;

        if (mmsi != mmsi1.mmsi) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return mmsi;
    }

    private final int mmsi;
}
