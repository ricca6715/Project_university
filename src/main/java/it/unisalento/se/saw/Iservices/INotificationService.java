package it.unisalento.se.saw.Iservices;

import java.io.IOException;

import com.google.firebase.messaging.FirebaseMessagingException;

public interface INotificationService {
	
	public void TopicNotification(String head, String body, String topic) throws IOException, FirebaseMessagingException;
	public void SingleNotification(String head, String body, String token) throws IOException, FirebaseMessagingException;

}
