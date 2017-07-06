

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.ShipType;

public interface StaticDataReport extends DataReport {

	String getCallsign();
	String getShipName();
	ShipType getShipType();
	Integer getToBow();
	Integer getToStern();
	Integer getToStarboard();
	Integer getToPort();

}
