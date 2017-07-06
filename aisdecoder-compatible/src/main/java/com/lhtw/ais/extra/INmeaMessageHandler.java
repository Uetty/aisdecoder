package com.lhtw.ais.extra;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;
import java.util.logging.Logger;

import com.lhtw.ais.messages.AISMessage;
import com.lhtw.ais.messages.Metadata;

import dk.tbsalling.aismessages.nmea.NMEAMessageHandler;
import dk.tbsalling.aismessages.nmea.messages.NMEAMessage;

public class INmeaMessageHandler implements IConsumer{

	private static final Logger LOG = Logger.getLogger(NMEAMessageHandler.class.getName());

    private final String source;
    private final ArrayList<NMEAMessage> messageFragments = new ArrayList<>();
    private final List<Consumer<? super AISMessage>> aisMessageReceivers = new LinkedList<>();

    public INmeaMessageHandler(String source, Consumer<? super AISMessage>... aisMessageReceivers) {
    	this.source = source;
        for (Consumer<? super AISMessage> aisMessageReceiver : aisMessageReceivers) {
            addAisMessageReceiver(aisMessageReceiver);
        }
    }
    
    /**
     * Receive a single NMEA amoured AIS string
     * @param nmeaMessage the NMEAMessage to handle.
     */
    @Override
    public void accept(NMEAMessage nmeaMessage,String headMessage) {
//		LOG.finer("Received for processing: " + nmeaMessage.getRawMessage());
		
		if (! nmeaMessage.isValid()) {
			LOG.warning("NMEA message is invalid: " + nmeaMessage.toString());
			return;
		}
		
		// by Override
		long seconds = 0l;
		try{
			String timeMsg = headMessage.split(",")[2];
			seconds = Long.parseLong(timeMsg.substring(timeMsg.indexOf(":")+1, timeMsg.indexOf("*")));
		}catch(Exception e){
			return;
		}
		Instant instant = Instant.ofEpochSecond(seconds);
		
		int numberOfFragments = nmeaMessage.getNumberOfFragments();
		if (numberOfFragments <= 0) {
//			LOG.warning("NMEA message is invalid: " + nmeaMessage.toString());
			messageFragments.clear();
		} else if (numberOfFragments == 1) {
//			LOG.finest("Handling unfragmented NMEA message");
			
			// by Override
            AISMessage aisMessage = AISMessage.create(new Metadata(source,instant), nmeaMessage);
            
            sendToAisMessageReceivers(aisMessage);
			messageFragments.clear();
		} else {
			int fragmentNumber = nmeaMessage.getFragmentNumber();
//			LOG.finest("Handling fragmented NMEA message with fragment number " + fragmentNumber);
			if (fragmentNumber < 0) {
//				LOG.warning("Fragment number cannot be negative: " + fragmentNumber + ": " + nmeaMessage.getRawMessage());
				messageFragments.clear();
			} else if (fragmentNumber > numberOfFragments) {
//				LOG.fine("Fragment number " + fragmentNumber + " higher than expected " + numberOfFragments + ": " + nmeaMessage.getRawMessage());
				messageFragments.clear();
			} else {
				int expectedFragmentNumber = messageFragments.size() + 1;
//				LOG.finest("Expected fragment number is: " + expectedFragmentNumber + ": " + nmeaMessage.getRawMessage());
				
				if (expectedFragmentNumber != fragmentNumber) {
//					LOG.fine("Expected fragment number " + expectedFragmentNumber + "; not " + fragmentNumber + ": " + nmeaMessage.getRawMessage());
					messageFragments.clear();
				} else {
					messageFragments.add(nmeaMessage);
//					LOG.finest("nmeaMessage.getNumberOfFragments(): " + nmeaMessage.getNumberOfFragments());
//					LOG.finest("messageFragments.size(): " + messageFragments.size());
					if (nmeaMessage.getNumberOfFragments() == messageFragments.size()) {
						
						// by Override
                        AISMessage aisMessage = AISMessage.create(new Metadata(source,instant), messageFragments.toArray(new NMEAMessage[messageFragments.size()]));
                        
                        sendToAisMessageReceivers(aisMessage);
						messageFragments.clear();
					} else
						LOG.finest("Fragmented message not yet complete; missing " + (nmeaMessage.getNumberOfFragments() - messageFragments.size()) + " fragment(s).");
				}
			}
		}
	}

    /** Send encoded AIS message to all interested receivers. */
    private void sendToAisMessageReceivers(final AISMessage aisMessage) {
        aisMessageReceivers.forEach(r -> r.accept(aisMessage));
    }

    /**
     * Add a consumer of encoded AIS messages.
     * @param aisMessageReceiver The consumer to add.
     */
    @SuppressWarnings("unused")
    public void addAisMessageReceiver(Consumer<? super AISMessage> aisMessageReceiver) {
        aisMessageReceivers.add(aisMessageReceiver);
    }

    /**
	 * Empty buffer of unhandled messages and return those not handled.
     * @return List of unhandled NMEAMessages.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<NMEAMessage> flush() {
		ArrayList<NMEAMessage> unhandled = (ArrayList<NMEAMessage>) messageFragments.clone();
		messageFragments.clear();
		return unhandled;
	}
}
