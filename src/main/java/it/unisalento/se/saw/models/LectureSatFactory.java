package it.unisalento.se.saw.models;

public class LectureSatFactory implements SatisfactionFactory {

	@Override
	public LecturesatisfactionModel createLectureFactory() {
		return new LecturesatisfactionModel();
	}

	@Override
	public MaterialsatisfactionModel createMaterialFactory() {
		return null;
	}



}
