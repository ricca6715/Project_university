package it.unisalento.se.saw.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import it.unisalento.se.saw.domain.Classroom;
import it.unisalento.se.saw.domain.Lecture;
import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.repositories.LectureRepository;

@RunWith(MockitoJUnitRunner.class)
public class LectureServiceTest {

    @Mock
    private LectureRepository lectureRepositoryMock;
    @InjectMocks
    private LectureService lectureServiceMock;
	
    @Test
    public void getAllTest() throws Exception {
    	
    	Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setTeaching(new Teaching(null, "Database", 9, 1, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setTeaching(new Teaching(null, "Computer Vision", 9, 1, null, null, null, null));
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));
		
		
		when(lectureRepositoryMock.findAll()).thenReturn(Arrays.asList(l1, l2));
		
		List<Lecture> lectures = lectureServiceMock.getAll();
		assertEquals(new Integer(1), lectures.get(0).getIdLecture());
		assertEquals("y1", lectures.get(0).getClassroom().getName());
		assertEquals(d, lectures.get(0).getDate());
		assertEquals("15-15", lectures.get(0).getStarttime());
		assertEquals("18-15", lectures.get(0).getEndtime());
		
		assertEquals(new Integer(2), lectures.get(1).getIdLecture());
		assertEquals("y2", lectures.get(1).getClassroom().getName());
		assertEquals(d, lectures.get(1).getDate());
		assertEquals("15-15", lectures.get(1).getStarttime());
		assertEquals("18-15", lectures.get(1).getEndtime());
		
    }
    
    @Test
    public void getLecturesByDateTest() throws Exception {
    	
    	Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setTeaching(new Teaching(null, "Database", 9, 1, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setTeaching(new Teaching(null, "Computer Vision", 9, 1, null, null, null, null));
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));
		
		
		when(lectureRepositoryMock.getLecturesByDate(d)).thenReturn(Arrays.asList(l1, l2));
		
		List<Lecture> lectures = lectureServiceMock.getLecturesByDate(d);
		assertEquals(new Integer(1), lectures.get(0).getIdLecture());
		assertEquals("y1", lectures.get(0).getClassroom().getName());
		assertEquals(d, lectures.get(0).getDate());
		assertEquals("15-15", lectures.get(0).getStarttime());
		assertEquals("18-15", lectures.get(0).getEndtime());
		
		assertEquals(new Integer(2), lectures.get(1).getIdLecture());
		assertEquals("y2", lectures.get(1).getClassroom().getName());
		assertEquals(d, lectures.get(1).getDate());
		assertEquals("15-15", lectures.get(1).getStarttime());
		assertEquals("18-15", lectures.get(1).getEndtime());
		
    }
    
    @Test
    public void getLectureByIdTest() throws Exception {
    	
    	Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setTeaching(new Teaching(null, "Database", 9, 1, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		
		
		
		when(lectureRepositoryMock.getLectureById(Mockito.anyInt())).thenReturn(l1);
		
		Lecture lecture = lectureServiceMock.getLectureById(1);
		assertEquals(new Integer(1), lecture.getIdLecture());
		assertEquals("y1", lecture.getClassroom().getName());
		assertEquals(d, lecture.getDate());
		assertEquals("15-15", lecture.getStarttime());
		assertEquals("18-15", lecture.getEndtime());

		
    }
    
    @Test
    public void getLecturesByClassroomTest() throws Exception {
    	
    	Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setTeaching(new Teaching(null, "Database", 9, 1, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setTeaching(new Teaching(null, "Computer Vision", 9, 1, null, null, null, null));
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));
		
		
		when(lectureRepositoryMock.getLecturesByClassroom(Mockito.anyInt())).thenReturn(Arrays.asList(l1, l2));
		
		List<Lecture> lectures = lectureServiceMock.getLecturesByClassroom(1);
		assertEquals(new Integer(1), lectures.get(0).getIdLecture());
		assertEquals("y1", lectures.get(0).getClassroom().getName());
		assertEquals(d, lectures.get(0).getDate());
		assertEquals("15-15", lectures.get(0).getStarttime());
		assertEquals("18-15", lectures.get(0).getEndtime());
		
		assertEquals(new Integer(2), lectures.get(1).getIdLecture());
		assertEquals("y2", lectures.get(1).getClassroom().getName());
		assertEquals(d, lectures.get(1).getDate());
		assertEquals("15-15", lectures.get(1).getStarttime());
		assertEquals("18-15", lectures.get(1).getEndtime());
		
    }
    
    @Test
    public void getLecturesByIdTeachingTest() throws Exception {
    	
    	Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setTeaching(new Teaching(null, "Database", 9, 1, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setTeaching(new Teaching(null, "Computer Vision", 9, 1, null, null, null, null));
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));
		
		
		when(lectureRepositoryMock.getLecturesByIdTeaching(Mockito.anyInt())).thenReturn(Arrays.asList(l1, l2));
		
		List<Lecture> lectures = lectureServiceMock.getLecturesByIdTeaching(1);
		assertEquals(new Integer(1), lectures.get(0).getIdLecture());
		assertEquals("y1", lectures.get(0).getClassroom().getName());
		assertEquals(d, lectures.get(0).getDate());
		assertEquals("15-15", lectures.get(0).getStarttime());
		assertEquals("18-15", lectures.get(0).getEndtime());
		
		assertEquals(new Integer(2), lectures.get(1).getIdLecture());
		assertEquals("y2", lectures.get(1).getClassroom().getName());
		assertEquals(d, lectures.get(1).getDate());
		assertEquals("15-15", lectures.get(1).getStarttime());
		assertEquals("18-15", lectures.get(1).getEndtime());
		
    }
    
    
    @Test
    public void getDailyLectureByIdProfAndDateTest() throws Exception {
    	
    	Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setTeaching(new Teaching(null, "Database", 9, 1, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setTeaching(new Teaching(null, "Computer Vision", 9, 1, null, null, null, null));
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));
		
		
		when(lectureRepositoryMock.getDailyLectureByIdProfAndDate(Mockito.anyInt(), Mockito.any())).thenReturn(Arrays.asList(l1, l2));
		
		List<Lecture> lectures = lectureServiceMock.getDailyLectureByIdProfAndDate(1,d);
		assertEquals(new Integer(1), lectures.get(0).getIdLecture());
		assertEquals("y1", lectures.get(0).getClassroom().getName());
		assertEquals(d, lectures.get(0).getDate());
		assertEquals("15-15", lectures.get(0).getStarttime());
		assertEquals("18-15", lectures.get(0).getEndtime());
		
		assertEquals(new Integer(2), lectures.get(1).getIdLecture());
		assertEquals("y2", lectures.get(1).getClassroom().getName());
		assertEquals(d, lectures.get(1).getDate());
		assertEquals("15-15", lectures.get(1).getStarttime());
		assertEquals("18-15", lectures.get(1).getEndtime());
		
    }
    
    @Test
    public void getDailyLectureByIdTeachingAndDateTest() throws Exception {
    	
    	Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setTeaching(new Teaching(null, "Database", 9, 1, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		Lecture l2 = new Lecture();
		l2.setIdLecture(2);
		l2.setDate(d);
		l2.setStarttime("15-15");
		l2.setEndtime("18-15");
		l2.setTeaching(new Teaching(null, "Computer Vision", 9, 1, null, null, null, null));
		l2.setClassroom(new Classroom("y2", "classroom y2", null, null, null, null, null));
		
		
		when(lectureRepositoryMock.getDailyLectureByIdTeachingAndDate(Mockito.anyInt(), Mockito.any())).thenReturn(Arrays.asList(l1, l2));
		
		List<Lecture> lectures = lectureServiceMock.getDailyLectureByIdTeachingAndDate(1,d);
		assertEquals(new Integer(1), lectures.get(0).getIdLecture());
		assertEquals("y1", lectures.get(0).getClassroom().getName());
		assertEquals(d, lectures.get(0).getDate());
		assertEquals("15-15", lectures.get(0).getStarttime());
		assertEquals("18-15", lectures.get(0).getEndtime());
		
		assertEquals(new Integer(2), lectures.get(1).getIdLecture());
		assertEquals("y2", lectures.get(1).getClassroom().getName());
		assertEquals(d, lectures.get(1).getDate());
		assertEquals("15-15", lectures.get(1).getStarttime());
		assertEquals("18-15", lectures.get(1).getEndtime());
		
    }
    
    @Test
    public void saveTest() throws Exception {
    	
    	Lecture l1 = new Lecture();
		l1.setIdLecture(1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = sdf.parse("2012-12-21");
		l1.setDate(d);
		l1.setStarttime("15-15");
		l1.setEndtime("18-15");
		l1.setTeaching(new Teaching(null, "Database", 9, 1, null, null, null, null));
		l1.setClassroom(new Classroom("y1", "classroom y1", null, null, null, null, null));
		
		when(lectureRepositoryMock.save(Mockito.any(Lecture.class))).thenReturn(l1);
		
		Lecture lback = lectureServiceMock.save(l1);
		
		assertEquals(new Integer(1), lback.getIdLecture());
		assertEquals("y1", lback.getClassroom().getName());
		assertEquals(d, lback.getDate());
		assertEquals("15-15", lback.getStarttime());
		assertEquals("18-15", lback.getEndtime());
		
    	
    }
	
}
