package com.lhtw.ais.extra;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Consumer;

import com.lhtw.ais.messages.AISMessage;

public class IAisInputStreamReader {

	public IAisInputStreamReader(InputStream inputStream, Consumer<? super AISMessage> aisMessageConsumer) {
        this.nmeaMessageHandler = new INmeaMessageHandler("SRC", aisMessageConsumer);
        this.nmeaMessageInputStreamReader = new INmeaMessageInputStreamReader(inputStream, this.nmeaMessageHandler::accept);
	}

	public final synchronized void requestStop() {
		this.stopRequested = true;
	}

    public final synchronized boolean isStopRequested() {
        return stopRequested;
    }

    public void run() throws IOException {
        this.nmeaMessageInputStreamReader.run();
	}

	private boolean stopRequested = false;
    private final INmeaMessageHandler nmeaMessageHandler;
	private final INmeaMessageInputStreamReader nmeaMessageInputStreamReader;

}
