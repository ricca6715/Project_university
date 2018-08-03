package it.unisalento.se.saw.models;

public class MaterialSatFactory implements SatisfactionFactory {

	@Override
	public LecturesatisfactionModel createLectureFactory() {
		return null;
	}

	@Override
	public MaterialsatisfactionModel createMaterialFactory() {
		return new MaterialsatisfactionModel();
	}

}
