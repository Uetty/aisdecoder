

package com.lhtw.ais.messages;

import dk.tbsalling.aismessages.ais.messages.types.TransponderClass;

/**
 * AIS messages with a payload of ship static or voyage related data
 * or position reports implement this interface.
 */
public interface DataReport {
	TransponderClass getTransponderClass();
}
