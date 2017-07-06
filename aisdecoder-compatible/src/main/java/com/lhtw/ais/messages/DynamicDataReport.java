

/**
 * 
 */
package com.lhtw.ais.messages;

public interface DynamicDataReport extends DataReport {
	Float getLatitude();
	Float getLongitude();
	Float getSpeedOverGround();
	Float getCourseOverGround();
}
