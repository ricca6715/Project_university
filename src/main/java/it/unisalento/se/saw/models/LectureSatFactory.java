package it.unisalento.se.saw.models;

public class LectureSatFactory implements SatisfactionFactory {

	@Override
	public LecturesatisfactionModel createLectureFactory() {
		// TODO Auto-generated method stub
		return new LecturesatisfactionModel();
	}

	@Override
	public MaterialsatisfactionModel createMaterialFactory() {
		// TODO Auto-generated method stub
		return null;
	}



}
