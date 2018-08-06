package it.unisalento.se.saw.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.unisalento.se.saw.Iservices.ILectureService;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.exceptions.LectureNotFoundException;
import it.unisalento.se.saw.repositories.LectureRepository;

@Service
public class LectureService implements ILectureService {

	@Autowired
	LectureRepository lectureRepository;
	
	
	
	@Transactional(readOnly=true)
	public List<Lecture> getLecturesByDate(Date date) {
		
		return lectureRepository.getLecturesByDate(date);
	}
	
	
	
	@Transactional(readOnly=true)
	public List<Lecture> getAll(){
		return lectureRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Lecture getLectureById(int id) throws LectureNotFoundException {
		Lecture l =  lectureRepository.getLectureById(id);
		if(l == null) {
			throw new LectureNotFoundException();
		}
		
		return l;
	}



	@Transactional(readOnly=true)
	public List<Lecture> getLecturesByClassroom(int idClassroom) {
		return lectureRepository.getLecturesByClassroom(idClassroom);
	}



	@Transactional(readOnly=true)
	public List<Lecture> getLecturesByIdTeaching(int idTeaching) {
		
		return lectureRepository.getLecturesByIdTeaching(idTeaching);
	}



	@Override
	public List<Lecture> getDailyLectureByIdProfAndDate(int idUser, Date date) {
		return lectureRepository.getDailyLectureByIdProfAndDate(idUser, date);
	}



	@Override
	public List<Lecture> getDailyLectureByIdTeachingAndDate(int idTeaching, Date date) {
		return lectureRepository.getDailyLectureByIdTeachingAndDate(idTeaching, date);
	}

}
