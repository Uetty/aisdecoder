

package dk.tbsalling.aismessages.ais.messages.types;

import java.io.Serializable;

public abstract class CommunicationState implements Serializable {

	protected CommunicationState(SyncState syncState) {
		this.syncState = syncState;
	}

	@SuppressWarnings("unused")
	public SyncState getSyncState() {
		return syncState;
	}

	private final SyncState syncState;
}
