package it.unisalento.se.saw.models;

import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Lecturesatisfaction;
import it.unisalento.se.saw.domain.Materialsatisfaction;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.domain.User;

public interface SatisfactionFactory {

	public LecturesatisfactionModel createLectureFactory(Integer idlectureSatisfaction, Lecture lecture, User user, Integer level,
			String note);
	public MaterialsatisfactionModel createMaterialFactory(Integer idMaterialSatisfaction, Teachingmaterial teachingmaterial,
			User user, Integer level, String note);
	public Lecturesatisfaction createLectureDomainFactory(LecturesatisfactionModel lsm);
	public Materialsatisfaction createMaterialDomainFactory(MaterialsatisfactionModel msm);
}
