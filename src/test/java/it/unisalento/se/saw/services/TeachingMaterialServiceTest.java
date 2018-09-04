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

import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Teachingmaterial;
import it.unisalento.se.saw.exceptions.TeachingMaterialNotFound;
import it.unisalento.se.saw.repositories.TeachingMaterialRepository;

@RunWith(MockitoJUnitRunner.class)
public class TeachingMaterialServiceTest {
	
	@Mock
	private TeachingMaterialRepository tmrep;
	@InjectMocks
	private TeachingMaterialService tmService;
	
	@Test
    public void getTeachingMaterialByIdLectureTest() {
		Teachingmaterial tm1 = new Teachingmaterial();
		Teachingmaterial tm2 = new Teachingmaterial();
		Lecture l = new Lecture();
		l.setIdLecture(1);
		
		tm1.setIdTeachingMaterial(1);
		tm1.setLecture(l);
		tm1.setLink("ProvaProva");
		tm1.setName("material");
		tm1.setType("link");
		tm2.setIdTeachingMaterial(2);
		tm2.setLecture(l);
		tm2.setLink("ProvaProva");
		tm2.setName("material2");
		tm2.setType("link");
		
		when(tmrep.getTeachingMaterialByIdLecture(1)).thenReturn(Arrays.asList(tm1, tm2));
		
		List<Teachingmaterial> tmlback = tmService.getTeachingMaterialByIdLecture(1);
		
		assertEquals(tm1.getIdTeachingMaterial(), tmlback.get(0).getIdTeachingMaterial());
		assertEquals(tm1.getLecture().getIdLecture(), tmlback.get(0).getLecture().getIdLecture());
		assertEquals(tm1.getLink(), tmlback.get(0).getLink());
		assertEquals(tm1.getName(), tmlback.get(0).getName());
		assertEquals(tm1.getType(), tmlback.get(0).getType());
		assertEquals(tm2.getIdTeachingMaterial(), tmlback.get(1).getIdTeachingMaterial());
		assertEquals(tm2.getLecture().getIdLecture(), tmlback.get(1).getLecture().getIdLecture());
		assertEquals(tm2.getLink(), tmlback.get(1).getLink());
		assertEquals(tm2.getName(), tmlback.get(1).getName());
		assertEquals(tm2.getType(), tmlback.get(1).getType());
	}
	
	@Test
    public void removeMaterialTest() {
		Teachingmaterial tm1 = new Teachingmaterial();
		Lecture l = new Lecture();
		l.setIdLecture(1);
		
		tm1.setIdTeachingMaterial(1);
		tm1.setLecture(l);
		tm1.setLink("ProvaProva");
		tm1.setName("material");
		tm1.setType("link");
		boolean expected = true;
		
		boolean result = tmService.removeMaterial(1);
		
		assertEquals(expected, result);
	}
	
	@Test
    public void getTeachingMaterialByIdTest() throws TeachingMaterialNotFound {
		Teachingmaterial tm1 = new Teachingmaterial();
		Lecture l = new Lecture();
		l.setIdLecture(1);
		
		tm1.setIdTeachingMaterial(1);
		tm1.setLecture(l);
		tm1.setLink("ProvaProva");
		tm1.setName("material");
		tm1.setType("link");
		
		when(tmrep.getTeachingMaterialById(1)).thenReturn(tm1);
		
		Teachingmaterial tmback = tmService.getTeachingMaterialById(1);
		
		assertEquals(tm1.getIdTeachingMaterial(), tmback.getIdTeachingMaterial());
		assertEquals(tm1.getLecture().getIdLecture(), tmback.getLecture().getIdLecture());
		assertEquals(tm1.getLink(), tmback.getLink());
		assertEquals(tm1.getName(), tmback.getName());
		assertEquals(tm1.getType(), tmback.getType());
	}
	
	@Test
    public void saveTest() throws TeachingMaterialNotFound {
		Teachingmaterial tm1 = new Teachingmaterial();
		Lecture l = new Lecture();
		l.setIdLecture(1);
		
		tm1.setIdTeachingMaterial(1);
		tm1.setLecture(l);
		tm1.setLink("ProvaProva");
		tm1.setName("material");
		tm1.setType("link");
		
		when(tmrep.save(Mockito.any(Teachingmaterial.class))).thenReturn(tm1);
		
		Teachingmaterial tmback = tmService.saveMaterial(tm1);
		
		assertEquals(tm1.getIdTeachingMaterial(), tmback.getIdTeachingMaterial());
		assertEquals(tm1.getLecture().getIdLecture(), tmback.getLecture().getIdLecture());
		assertEquals(tm1.getLink(), tmback.getLink());
		assertEquals(tm1.getName(), tmback.getName());
		assertEquals(tm1.getType(), tmback.getType());
	}

}
