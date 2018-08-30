package it.unisalento.se.saw.models;

public class FactoryProducer {
	
	public static SatisfactionFactory getSatisfactionFactory(String satisfactionType) {
		if(satisfactionType.equals("lecture"))
			return new LectureSatFactory();
		if(satisfactionType.equals("material"))
			return new MaterialSatFactory();
		
		return null;
	}
}
