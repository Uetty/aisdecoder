package com.lhtw.ais.extra;

import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

public interface IConsumer {
	void accept(NMEAMessage message,String headMessage);
}
