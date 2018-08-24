package it.unisalento.se.saw.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.ICalendarService;
import it.unisalento.se.saw.domain.Calendar;
import it.unisalento.se.saw.repositories.CalendarRepository;

@Service
public class CalendarService implements ICalendarService {
	
	@Autowired
	CalendarRepository calRepository;

	@Transactional
	public Calendar save(Calendar calendar) {
		return calRepository.save(calendar);
	}
	
	@Transactional
	public List<Calendar> getAll(){
		return calRepository.findAll();
	}

}
