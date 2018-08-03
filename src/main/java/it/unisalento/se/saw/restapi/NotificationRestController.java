package it.unisalento.se.saw.restapi;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessagingException;

import it.unisalento.se.saw.Iservices.INotificationService;
import it.unisalento.se.saw.Iservices.IUserService;
import it.unisalento.se.saw.models.NotificationModel;

@CrossOrigin
@RestController() //contiene due annotation, Controller e response body
@RequestMapping("/notification")
public class NotificationRestController {
	
	@Autowired
	INotificationService notificationService;
	
	@Autowired
	IUserService userService;

	public NotificationRestController() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotificationRestController(INotificationService notificationService, IUserService userService) {
		super();
		this.notificationService = notificationService;
		this.userService = userService;
	}
	
	@PostMapping(
			value="/toUser",
			consumes =  MediaType.APPLICATION_JSON_VALUE)
	public void sendNotificationToUser(@RequestBody NotificationModel model) throws FirebaseMessagingException, IOException {
		System.out.println("User");
		System.out.println(model.getHead());
		System.out.println(model.getBody());
		System.out.println(model.getToken_topic());
		String token = userService.getUserByMail(model.getToken_topic()).getFcmtoken();
		if (token != null) {
			notificationService.SingleNotification(model.getHead(), model.getBody(), token);
		}
	}
	
	@PostMapping(
			value="/toTopic",
			consumes =  MediaType.APPLICATION_JSON_VALUE)
	public void sendNotificationToTopic(@RequestBody NotificationModel model) throws FirebaseMessagingException, IOException {

		System.out.println("Topic");
		System.out.println(model.getHead());
		System.out.println(model.getBody());
		model.setToken_topic(model.getToken_topic().replace(' ', '_'));
		System.out.println(model.getToken_topic());
		notificationService.TopicNotification(model.getHead(), model.getBody(), model.getToken_topic());
		
	}

}
