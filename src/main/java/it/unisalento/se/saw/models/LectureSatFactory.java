package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.domain.Materialsatisfaction;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.domain.User;

public class LectureSatFactory implements SatisfactionFactory {

	@Override
	public LecturesatisfactionModel createLectureFactory(Integer idlectureSatisfaction, Lecture lecture, User user, Integer level,
			String note) {
		LectureModel lm = new LectureModel();
		lm.setIdLecture(lecture.getIdLecture());
		lm.setDate(lecture.getDate());
		lm.setStarttime(lecture.getStarttime());
		lm.setEndtime(lecture.getEndtime());
		ClassroomModel clsm = new ClassroomModel();
		clsm.setIdClassroom(lecture.getClassroom().getIdClassroom());
		clsm.setDescription(lecture.getClassroom().getDescription());
		clsm.setName(lecture.getClassroom().getName());
		clsm.setLatitude(lecture.getClassroom().getLatitude());
		clsm.setLongitude(lecture.getClassroom().getLongitude());
		lm.setClassroom(clsm);
		TeachingModel tm = new TeachingModel();
		tm.setIdTeaching(lecture.getTeaching().getIdTeaching());
		tm.setName(lecture.getTeaching().getName());
		tm.setCfu(lecture.getTeaching().getCfu());
		tm.setCourseYear(lecture.getTeaching().getCourseYear());
		UserModel um = new UserModel();
		um.setIdUser(lecture.getTeaching().getUser().getIdUser());
		um.setName(lecture.getTeaching().getUser().getName());
		um.setSurname(lecture.getTeaching().getUser().getSurname());
		um.setEmail(lecture.getTeaching().getUser().getEmail());
		um.setPassword(lecture.getTeaching().getUser().getPassword());
		um.setFcmToken(lecture.getTeaching().getUser().getFcmtoken());
		UserTypeModel utm = new UserTypeModel();
		utm.setIdUserType(lecture.getTeaching().getUser().getUsertype().getIdUserType());
		utm.setTypeName(lecture.getTeaching().getUser().getUsertype().getTypeName());
		um.setUsertype(utm);
		if (utm.getTypeName().equals("student")) {
			StudyCourseModel scm = new StudyCourseModel();
			scm.setIdStudyCourse(lecture.getTeaching().getUser().getStudycourse().getIdStudyCourse());
			scm.setName(lecture.getTeaching().getUser().getStudycourse().getName());
			scm.setDescription(lecture.getTeaching().getUser().getStudycourse().getDescription());
			um.setStudycourse(scm);
			um.setCourseYear(lecture.getTeaching().getUser().getCourseYear());
		}
		tm.setUser(um);
		lm.setTeaching(tm);
		UserModel um1 = new UserModel();
		um1.setIdUser(user.getIdUser());
		um1.setName(user.getName());
		um1.setSurname(user.getSurname());
		um1.setEmail(user.getEmail());
		um1.setCourseYear(user.getCourseYear());
		um1.setPassword(user.getPassword());
		um1.setFcmToken(user.getFcmtoken());
		UserTypeModel utm1 = new UserTypeModel();
		utm1.setIdUserType(user.getUsertype().getIdUserType());
		utm1.setTypeName(user.getUsertype().getTypeName());
		um1.setUsertype(utm1);
		if (utm1.getTypeName().equals("student")) {
			StudyCourseModel scm1 = new StudyCourseModel();
			scm1.setIdStudyCourse(user.getStudycourse().getIdStudyCourse());
			scm1.setName(user.getStudycourse().getName());
			scm1.setDescription(user.getStudycourse().getDescription());
			um.setStudycourse(scm1);
		}
		return new LecturesatisfactionModel(idlectureSatisfaction, lm, um1, level, note);
	}

	@Override
	public MaterialsatisfactionModel createMaterialFactory(Integer idMaterialSatisfaction, Teachingmaterial teachingmaterial,
			User user, Integer level, String note) {
		return null;
	}

	@Override
	public Lecturesatisfaction createLectureDomainFactory(LecturesatisfactionModel lsm) {
		Lecturesatisfaction ls = new Lecturesatisfaction();
		if(lsm.getIdlectureSatisfaction() != null)
			ls.setIdlectureSatisfaction(lsm.getIdlectureSatisfaction());
		ls.setLevel(lsm.getLevel());
		Lecture l = new Lecture();
		l.setIdLecture(lsm.getLecture().getIdLecture());
		ls.setLecture(l);
		User user = new User();
		user.setIdUser(lsm.getUser().getIdUser());
		ls.setUser(user);
		ls.setNote(lsm.getNote());
		return ls;
	}

	@Override
	public Materialsatisfaction createMaterialDomainFactory(MaterialsatisfactionModel msm) {
		return null;
	}
}
