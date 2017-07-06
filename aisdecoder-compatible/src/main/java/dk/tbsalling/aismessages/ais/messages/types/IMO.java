

package dk.tbsalling.aismessages.ais.messages.types;

import java.io.Serializable;

public class IMO implements Serializable {

	public IMO(int imo) {
		this.imo = imo;
	}
	
	public static IMO valueOf(int imo) {
		return new IMO(imo);
	}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IMO imo1 = (IMO) o;

        if (imo != imo1.imo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return imo;
    }

    @Override
	public String toString() {
		return "IMO [imo=" + imo + "]";
	}

    public Integer getIMO() {
	    return imo;
	}

	private final int imo;
}
