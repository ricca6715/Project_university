package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.domain.Materialsatisfaction;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.domain.User;

public class MaterialSatFactory implements SatisfactionFactory {

	@Override
	public LecturesatisfactionModel createLectureFactory(Integer idlectureSatisfaction, Lecture lecture, User user, Integer level,
			String note) {
		return null;
	}

	@Override
	public MaterialsatisfactionModel createMaterialFactory(Integer idMaterialSatisfaction, Teachingmaterial teachingmaterial,
			User user, Integer level, String note) {
		TeachingMaterialModel tmm = new TeachingMaterialModel();
		tmm.setIdTeachingMaterial(teachingmaterial.getIdTeachingMaterial());
		tmm.setLink(teachingmaterial.getLink());
		tmm.setName(teachingmaterial.getName());
		tmm.setType(teachingmaterial.getType());
		UserModel um1 = new UserModel();
		um1.setIdUser(teachingmaterial.getUser().getIdUser());
		um1.setName(teachingmaterial.getUser().getName());
		um1.setSurname(teachingmaterial.getUser().getSurname());
		um1.setEmail(teachingmaterial.getUser().getEmail());
		um1.setPassword(teachingmaterial.getUser().getPassword());
		um1.setFcmToken(teachingmaterial.getUser().getFcmtoken());
		UserTypeModel utm1 = new UserTypeModel();
		utm1.setIdUserType(teachingmaterial.getUser().getUsertype().getIdUserType());
		utm1.setTypeName(teachingmaterial.getUser().getUsertype().getTypeName());
		um1.setUsertype(utm1);
		if (utm1.getTypeName().equals("student")) {
			StudyCourseModel scm = new StudyCourseModel();
			scm.setIdStudyCourse(teachingmaterial.getUser().getStudycourse().getIdStudyCourse());
			scm.setName(teachingmaterial.getUser().getStudycourse().getName());
			scm.setDescription(teachingmaterial.getUser().getStudycourse().getDescription());
			um1.setStudycourse(scm);
			um1.setCourseYear(teachingmaterial.getUser().getCourseYear());
		}
		tmm.setUser(um1);
		LectureModel lm = new LectureModel();
		lm.setIdLecture(teachingmaterial.getLecture().getIdLecture());
		lm.setDate(teachingmaterial.getLecture().getDate());
		lm.setStarttime(teachingmaterial.getLecture().getStarttime());
		lm.setEndtime(teachingmaterial.getLecture().getEndtime());
		ClassroomModel clsm = new ClassroomModel();
		clsm.setIdClassroom(teachingmaterial.getLecture().getClassroom().getIdClassroom());
		clsm.setDescription(teachingmaterial.getLecture().getClassroom().getDescription());
		clsm.setName(teachingmaterial.getLecture().getClassroom().getName());
		clsm.setLatitude(teachingmaterial.getLecture().getClassroom().getLatitude());
		clsm.setLongitude(teachingmaterial.getLecture().getClassroom().getLongitude());
		lm.setClassroom(clsm);
		TeachingModel tm = new TeachingModel();
		tm.setIdTeaching(teachingmaterial.getLecture().getTeaching().getIdTeaching());
		tm.setName(teachingmaterial.getLecture().getTeaching().getName());
		tm.setCfu(teachingmaterial.getLecture().getTeaching().getCfu());
		tm.setCourseYear(teachingmaterial.getLecture().getTeaching().getCourseYear());
		UserModel um = new UserModel();
		um.setIdUser(teachingmaterial.getLecture().getTeaching().getUser().getIdUser());
		um.setName(teachingmaterial.getLecture().getTeaching().getUser().getName());
		um.setSurname(teachingmaterial.getLecture().getTeaching().getUser().getSurname());
		um.setEmail(teachingmaterial.getLecture().getTeaching().getUser().getEmail());
		um.setPassword(teachingmaterial.getLecture().getTeaching().getUser().getPassword());
		um.setFcmToken(teachingmaterial.getLecture().getTeaching().getUser().getFcmtoken());
		UserTypeModel utm = new UserTypeModel();
		utm.setIdUserType(teachingmaterial.getLecture().getTeaching().getUser().getUsertype().getIdUserType());
		utm.setTypeName(teachingmaterial.getLecture().getTeaching().getUser().getUsertype().getTypeName());
		um.setUsertype(utm);
		tm.setUser(um);
		lm.setTeaching(tm);
		tmm.setLecture(lm);
		return new MaterialsatisfactionModel(idMaterialSatisfaction, tmm, um1, level, note);
	}

	@Override
	public Lecturesatisfaction createLectureDomainFactory(LecturesatisfactionModel lsm) {
		return null;
	}

	@Override
	public Materialsatisfaction createMaterialDomainFactory(MaterialsatisfactionModel msm) {
		Materialsatisfaction ms = new Materialsatisfaction();
		if(msm.getIdMaterialSatisfaction() != null)
			ms.setIdMaterialSatisfaction(msm.getIdMaterialSatisfaction());
		ms.setLevel(msm.getLevel());
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(msm.getTeachingmaterial().getIdTeachingMaterial());
		ms.setTeachingmaterial(tm);
		User user = new User();
		user.setIdUser(msm.getUser().getIdUser());
		ms.setUser(user);
		ms.setNote(msm.getNote());
		return ms;
	}

}
