package it.unisalento.se.saw.services;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;

import it.unisalento.se.saw.Iservices.INotificationService;
import it.unisalento.se.saw.configurations.FirebaseSDK;

@Service
public class NotificationService implements INotificationService {


	

	@Transactional
	public void TopicNotification(String head, String body, String topic) throws IOException, FirebaseMessagingException {
		// TODO Auto-generated method stub
		FirebaseSDK.initializeSDK();
		
		Message message = Message.builder()
			    .putData("title", head)
			    .putData("body", body)
			    .setTopic(topic)
			    .build();

		// Send a message to the devices subscribed to the provided topic.
		String response = FirebaseMessaging.getInstance().send(message);
		System.out.println("Message sent to topic: " + response);
	}

	@Transactional
	public void SingleNotification(String head, String body, String token) throws IOException, FirebaseMessagingException {
		// TODO Auto-generated method stub
		FirebaseSDK.initializeSDK();
		
		Message message = Message.builder()
			    .putData("title", head)
			    .putData("body", body)
			    .setToken(token)
			    .build();

		// Send a message to the device corresponding to the provided registration token.
		String response = FirebaseMessaging.getInstance().send(message);
		System.out.println("Message sent to user: " + response);
	}

}
