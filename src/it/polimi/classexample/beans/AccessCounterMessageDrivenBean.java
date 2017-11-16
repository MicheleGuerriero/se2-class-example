package it.polimi.classexample.beans;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(mappedName = "jms/testQueue", activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue") })
public class AccessCounterMessageDrivenBean implements MessageListener {

	private final Logger logger = Logger.getLogger("test");

	public void onMessage(Message msg) {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			if (msg instanceof TextMessage) { 
				logger.log(Level.INFO,
				"MESSAGE BEAN: Message received: " + msg.getBody(String.class) + " for method " + msg.getStringProperty("method")
				); 
				
				if(msg.getStringProperty("method").equals("POST")) {
					logger.log(Level.INFO,
							"MESSAGE BEAN: Addition operands: " + msg.getIntProperty("first") + ", " + msg.getIntProperty("second")
							); 	
				}
			}
			else {
				logger.log(
				Level.WARNING, 
				"Message of wrong type: " + msg.getClass().getName()); 
			}
		} catch (JMSException e) { 
			logger.log(
			Level.SEVERE,
			"SimpleMessageBean.onMessage: JMSException: " + e.toString()); 
		} 
	}
}