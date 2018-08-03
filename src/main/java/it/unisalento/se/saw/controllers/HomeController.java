package it.unisalento.se.saw.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.unisalento.se.saw.domain.User;

@Controller
public class HomeController {
	

	@Autowired
	public HomeController() {
		super();
	}
	
	
	@RequestMapping(value="/home/user", method= RequestMethod.GET)
	public String users(ModelMap modelMap) {
		
		return "users";
	}

	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(ModelMap modelMap) throws FileNotFoundException {
		
		final String dir = System.getProperty("user.dir");
        System.out.println("current dir = " + dir);
		System.out.println("HOMECONTROLLER");
		return "home";
	}
	
	
}
