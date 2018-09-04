package it.unisalento.se.saw.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import javax.annotation.meta.When;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.models.ClassroomModel;
import it.unisalento.se.saw.repositories.ClassroomRepository;

@RunWith(MockitoJUnitRunner.class)
public class ClassroomServiceTest {
	
	@Mock
	private ClassroomRepository clsrep;
	@InjectMocks
	private ClassroomService clsService;
	
	@Test
    public void getAllTest() {
		Classroom cls1 = new Classroom();
		Classroom cls2 = new Classroom();
		
		cls1.setName("y1");
		cls1.setDescription("test");
		cls1.setLatitude((double) 110);
		cls1.setLongitude((double) 115);
		cls1.setIdClassroom(1);
		cls2.setName("m1");
		cls2.setDescription("test2");
		cls2.setLatitude((double) 210);
		cls2.setLongitude((double) 215);
		cls2.setIdClassroom(2);
		
		when(clsrep.findAll()).thenReturn(Arrays.asList(cls1, cls2));
		
		List<Classroom> clback = clsService.getAll();
		
		assertEquals(cls1.getName(), clback.get(0).getName());
		assertEquals(cls1.getDescription(), clback.get(0).getDescription());
		assertEquals(cls1.getLatitude(), clback.get(0).getLatitude());
		assertEquals(cls1.getLongitude(), clback.get(0).getLongitude());
		assertEquals(cls1.getIdClassroom(), clback.get(0).getIdClassroom());
		assertEquals(cls2.getName(), clback.get(1).getName());
		assertEquals(cls2.getDescription(), clback.get(1).getDescription());
		assertEquals(cls2.getLatitude(), clback.get(1).getLatitude());
		assertEquals(cls2.getLongitude(), clback.get(1).getLongitude());
		assertEquals(cls2.getIdClassroom(), clback.get(1).getIdClassroom());
	}
	
	@Test
    public void saveTest() {
		Classroom cls1 = new Classroom();
		cls1.setName("y1");
		cls1.setDescription("test");
		cls1.setLatitude((double) 110);
		cls1.setLongitude((double) 115);
		cls1.setIdClassroom(1);
		
		when(clsrep.save(Mockito.any(Classroom.class))).thenReturn(cls1);
		
		Classroom clsback = clsService.save(cls1);
		
		assertEquals(cls1.getName(), clsback.getName());
		assertEquals(cls1.getDescription(), clsback.getDescription());
		assertEquals(cls1.getLatitude(), clsback.getLatitude());
		assertEquals(cls1.getLongitude(), clsback.getLongitude());
		assertEquals(cls1.getIdClassroom(), clsback.getIdClassroom());

	}

}
