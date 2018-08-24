package it.unisalento.se.saw.Iservices;

import java.util.List;

import it.unisalento.se.saw.domain.Calendar;

public interface ICalendarService {

	public Calendar save(Calendar calendar);

	public List<Calendar> getAll();
}
