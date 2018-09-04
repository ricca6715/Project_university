package it.unisalento.se.saw.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import it.unisalento.se.saw.domain.Materialsatisfaction;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.exceptions.MaterialSatisfactionNotFound;
import it.unisalento.se.saw.repositories.MaterialSatisfactionRepository;

@RunWith(MockitoJUnitRunner.class)
public class MaterialsatisfactionServiceTest {
	
	@Mock
	private MaterialSatisfactionRepository msrep;
	@InjectMocks
	private MaterialSatisfactionService msService;
	
	@Test
    public void getAverageRatingByIdMaterialTest() {
		Materialsatisfaction ms1 = new Materialsatisfaction();
		Materialsatisfaction ms2 = new Materialsatisfaction();
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		
		ms1.setIdMaterialSatisfaction(1);
		ms1.setLevel(1);
		ms1.setNote("Bad material");
		ms1.setTeachingmaterial(tm);
		ms2.setIdMaterialSatisfaction(2);
		ms2.setLevel(3);
		ms2.setNote("Nice material");
		ms2.setTeachingmaterial(tm);
		Double avg = (double) 2;
		
		when(msrep.getAverageRatingByIdMaterial(1)).thenReturn((double) 2);
		
		Double result = msService.getAverageRatingByIdMaterial(1);
		
		assertEquals(avg, result);
	}
	
	@Test
    public void getMaterialSatisfactionByIdMaterialTest() {
		Materialsatisfaction ms1 = new Materialsatisfaction();
		Materialsatisfaction ms2 = new Materialsatisfaction();
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		
		ms1.setIdMaterialSatisfaction(1);
		ms1.setLevel(1);
		ms1.setNote("Bad material");
		ms1.setTeachingmaterial(tm);
		ms2.setIdMaterialSatisfaction(2);
		ms2.setLevel(3);
		ms2.setNote("Nice material");
		ms2.setTeachingmaterial(tm);
		
		when(msrep.getMaterialSatisfactionByIdMaterial(1)).thenReturn(Arrays.asList(ms1, ms2));
		
		List<Materialsatisfaction> mslback = msService.getMaterialSatisfactionByIdMaterial(1);
		
		assertEquals(ms1.getIdMaterialSatisfaction(), mslback.get(0).getIdMaterialSatisfaction());
		assertEquals(ms1.getLevel(), mslback.get(0).getLevel());
		assertEquals(ms1.getNote(), mslback.get(0).getNote());
		assertEquals(ms1.getTeachingmaterial().getIdTeachingMaterial(), mslback.get(0).getTeachingmaterial().getIdTeachingMaterial());
		assertEquals(ms2.getIdMaterialSatisfaction(), mslback.get(1).getIdMaterialSatisfaction());
		assertEquals(ms2.getLevel(), mslback.get(1).getLevel());
		assertEquals(ms2.getNote(), mslback.get(1).getNote());
		assertEquals(ms2.getTeachingmaterial().getIdTeachingMaterial(), mslback.get(1).getTeachingmaterial().getIdTeachingMaterial());		
	}
	
	@Test
    public void getMaterialSatisfactionByIdUserAndIdMaterialTest() throws MaterialSatisfactionNotFound {
		Materialsatisfaction ms1 = new Materialsatisfaction();
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		User stud = new User();
		stud.setIdUser(1);
		
		ms1.setIdMaterialSatisfaction(1);
		ms1.setLevel(1);
		ms1.setNote("Bad material");
		ms1.setTeachingmaterial(tm);
		ms1.setUser(stud);
		
		when(msrep.getMaterialSatisfactionByIdUserAndByIdMaterial(1, 1)).thenReturn(ms1);
		
		Materialsatisfaction msback = msService.getMaterialSatisfactionByIdUserAndIdMaterial(1, 1);
		
		assertEquals(ms1.getIdMaterialSatisfaction(), msback.getIdMaterialSatisfaction());
		assertEquals(ms1.getLevel(), msback.getLevel());
		assertEquals(ms1.getNote(), msback.getNote());
		assertEquals(ms1.getTeachingmaterial().getIdTeachingMaterial(), msback.getTeachingmaterial().getIdTeachingMaterial());
		assertEquals(ms1.getUser().getIdUser(), msback.getUser().getIdUser());
	}
	
	@Test
    public void saveTest() {
		Materialsatisfaction ms1 = new Materialsatisfaction();
		Teachingmaterial tm = new Teachingmaterial();
		tm.setIdTeachingMaterial(1);
		User stud = new User();
		stud.setIdUser(1);
		
		ms1.setIdMaterialSatisfaction(1);
		ms1.setLevel(1);
		ms1.setNote("Bad material");
		ms1.setTeachingmaterial(tm);
		ms1.setUser(stud);
		
		when(msrep.save(Mockito.any(Materialsatisfaction.class))).thenReturn(ms1);
		
		Materialsatisfaction msback = msService.saveSatisfaction(ms1);
		
		assertEquals(ms1.getIdMaterialSatisfaction(), msback.getIdMaterialSatisfaction());
		assertEquals(ms1.getLevel(), msback.getLevel());
		assertEquals(ms1.getNote(), msback.getNote());
		assertEquals(ms1.getTeachingmaterial().getIdTeachingMaterial(), msback.getTeachingmaterial().getIdTeachingMaterial());
		assertEquals(ms1.getUser().getIdUser(), msback.getUser().getIdUser());
	}

}
