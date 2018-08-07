package it.unisalento.se.saw.restapi;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.support.ServletContextLiveBeansView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.unisalento.se.saw.Iservices.IUserService;
import it.unisalento.se.saw.domain.Studycourse;
import it.unisalento.se.saw.domain.User;
import it.unisalento.se.saw.domain.Usertype;
import it.unisalento.se.saw.exceptions.UserNotFoundException;
import it.unisalento.se.saw.models.TokenFormModel;

@RunWith(MockitoJUnitRunner.class)
public class UserRestControllerTest {
	
	private MockMvc mockMvc;
	
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(), 
			MediaType.APPLICATION_JSON.getSubtype(),
			Charset.forName("utf8")
			);
	@Mock
	private IUserService userServiceMock;
	
	@Before
	public void setUp() {
		
		mockMvc = MockMvcBuilders.standaloneSetup(new UserRestController(userServiceMock))
					.setViewResolvers(viewResolver())
					.build();
	}
	
	@Test
	public void findUserByIdTest() throws Exception {
		
		User user = new User();
		
		user.setName("riccardo");
		user.setSurname("contino");
		user.setEmail("riccardo@gmail.com");
		user.setPassword("riccardo");
		user.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		
		when(userServiceMock.getById(1)).thenReturn(user);
		
		mockMvc.perform(get("/user/getById/{id}", 1))
			.andExpect(status().isOk())
			.andExpect(content().contentType(APPLICATION_JSON_UTF8))
			.andExpect(jsonPath("$.name", is("riccardo")))
			.andExpect(jsonPath("$.surname", is("contino")))
			.andExpect(jsonPath("$.email", is("riccardo@gmail.com")))
			.andExpect(jsonPath("$.password", is("riccardo")))
			.andExpect(jsonPath("$.studycourse.name", is("Ingegneria dell'informazione")));
		
		verify(userServiceMock, times(1)).getById(1);
		verifyNoMoreInteractions(userServiceMock);
	}
	
	@Test
	public void StudentloginTest() throws Exception {
		User user = new User();
		
		user.setName("riccardo");
		user.setSurname("contino");
		user.setEmail("riccardo@gmail.com");
		user.setPassword("riccardo");
		
		user.setUsertype(new Usertype("student", null));
		user.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		
		when(userServiceMock.getUserByMail_Pwd("riccardo@gmail.com", "riccardo")).thenReturn(user);
		
		mockMvc.perform(get("/user/getUserByMail_Pwd/{mail}/{password}", "riccardo@gmail.com", "riccardo"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.name", is("riccardo")))
		.andExpect(jsonPath("$.surname", is("contino")))
		.andExpect(jsonPath("$.email", is("riccardo@gmail.com")))
		.andExpect(jsonPath("$.password", is("riccardo")))
		.andExpect(jsonPath("$.studycourse.name", is("Ingegneria dell'informazione")));
	
		verify(userServiceMock, times(1)).getUserByMail_Pwd("riccardo@gmail.com", "riccardo");
		verifyNoMoreInteractions(userServiceMock);
		
	}
	
	@Test
	public void Prof_SecrloginTest() throws Exception {
		User user = new User();
		
		user.setName("riccardo");
		user.setSurname("contino");
		user.setEmail("riccardo@gmail.com");
		user.setPassword("riccardo");
		user.setUsertype(new Usertype("professor", null));
		
		when(userServiceMock.getUserByMail_Pwd("riccardo@gmail.com", "riccardo")).thenReturn(user);
		
		mockMvc.perform(get("/user/getUserByMail_Pwd/{mail}/{password}", "riccardo@gmail.com", "riccardo"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(APPLICATION_JSON_UTF8))
		.andExpect(jsonPath("$.name", is("riccardo")))
		.andExpect(jsonPath("$.surname", is("contino")))
		.andExpect(jsonPath("$.email", is("riccardo@gmail.com")))
		.andExpect(jsonPath("$.password", is("riccardo")));
	
		verify(userServiceMock, times(1)).getUserByMail_Pwd("riccardo@gmail.com", "riccardo");
		verifyNoMoreInteractions(userServiceMock);
		
	}
	
	
	@Test
	public void loginErrorTest() throws Exception {
		User user = new User();
		
		user.setName("test");
		user.setSurname("contino");
		user.setEmail("test@gmail.com");
		user.setPassword("test");
		
		user.setUsertype(new Usertype("student", null));
		user.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		
		when(userServiceMock.getUserByMail_Pwd(Mockito.anyString(), Mockito.anyString())).thenThrow(new UserNotFoundException());
		
		mockMvc.perform(get("/user/getUserByMail_Pwd/{mail}/{password}", "test@gmail.com", "test"))
		.andExpect(status().isNotFound());
	
		verify(userServiceMock, times(1)).getUserByMail_Pwd(user.getEmail(), user.getPassword());
		verifyNoMoreInteractions(userServiceMock);
		
	}
	@Test
	public void getUsersEnrolledTeachingTest() throws Exception {
		User user1 = new User();
		User user2 = new User();
		
		
		user1.setName("riccardo");
		user1.setSurname("contino");
		user1.setEmail("riccardo@gmail.com");
		user1.setPassword("riccardo");
		
		user1.setUsertype(new Usertype("student", null));
		user1.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		
		
		user2.setName("andrea");
		user2.setSurname("della monaca");
		user2.setEmail("andrea@libero.it");
		user2.setPassword("andrea");
		
		user2.setUsertype(new Usertype("student", null));
		user2.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		

		
		when(userServiceMock.getUserEnrolledTeaching("Software Engineering")).thenReturn(Arrays.asList(user1, user2));
		
		mockMvc.perform(get("/user/getUserEnrolledTeaching/{nameTeaching}", "Software Engineering"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is("riccardo")))
		.andExpect(jsonPath("$[0].surname", is("contino")))
		.andExpect(jsonPath("$[0].email", is("riccardo@gmail.com")))
		.andExpect(jsonPath("$[0].studycourse.name", is("Ingegneria dell'informazione")))
		.andExpect(jsonPath("$[1].name", is("andrea")))
		.andExpect(jsonPath("$[1].surname", is("della monaca")))
		.andExpect(jsonPath("$[1].email", is("andrea@libero.it")))
		.andExpect(jsonPath("$[1].studycourse.name", is("Ingegneria dell'informazione")));
	
		verify(userServiceMock, times(1)).getUserEnrolledTeaching("Software Engineering");
		verifyNoMoreInteractions(userServiceMock);
		
	}

	@Test
	public void getProfessorByNameTeachingTest() throws Exception {
		
		User user3 = new User();
		user3.setName("luca");
		user3.setSurname("mainetti");
		user3.setEmail("luca@gmail.com");
		user3.setPassword("luca");
		user3.setUsertype(new Usertype("professor", null));
		user3.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		
		when(userServiceMock.getProfessorByNameTeaching("Software Engineering")).thenReturn(user3);
		
		mockMvc.perform(get("/user/getProfessorByNameTeaching/{nameTeaching}", "Software Engineering"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is("luca")))
		.andExpect(jsonPath("$.surname", is("mainetti")))
		.andExpect(jsonPath("$.email", is("luca@gmail.com")))
		.andExpect(jsonPath("$.studycourse.name", is("Ingegneria dell'informazione")));
	
		verify(userServiceMock, times(1)).getProfessorByNameTeaching("Software Engineering");
		verifyNoMoreInteractions(userServiceMock);
		
	}
	
	
	@Test
	public void getByMailTest() throws Exception {
		
		User user3 = new User();
		user3.setName("luca");
		user3.setSurname("mainetti");
		user3.setEmail("luca@gmail.com");
		user3.setPassword("luca");
		user3.setUsertype(new Usertype("professor", null));
		user3.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		
		when(userServiceMock.getUserByMail("luca@gmail.com")).thenReturn(user3);
		
		mockMvc.perform(get("/user/userByMail/{mail}/", "luca@gmail.com"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is("luca")))
		.andExpect(jsonPath("$.surname", is("mainetti")))
		.andExpect(jsonPath("$.email", is("luca@gmail.com")))
		.andExpect(jsonPath("$.studycourse.name", is("Ingegneria dell'informazione")));
	
		verify(userServiceMock, times(1)).getUserByMail("luca@gmail.com");
		verifyNoMoreInteractions(userServiceMock);
		
	}
	
	@Test
	public void getUsersByTeachingNameTest() throws Exception {
		User user1 = new User();
		User user2 = new User();
		
		user1.setName("riccardo");
		user1.setSurname("contino");
		user1.setEmail("riccardo@gmail.com");
		user1.setPassword("riccardo");
		user1.setUsertype(new Usertype("student", null));
		user1.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		
		user2.setName("andrea");
		user2.setSurname("della monaca");
		user2.setEmail("andrea@libero.it");
		user2.setPassword("andrea");
		user2.setUsertype(new Usertype("student", null));
		user2.setStudycourse(new Studycourse("Ingegneria dell'informazione", "test", null, null, null));
		
		List<User> users = new ArrayList<User>();
		users.add(user1);
		users.add(user2);
		when(userServiceMock.getUserEnrolledTeaching("Software Engineering")).thenReturn(users);
		
		User user3 = new User();
		user3.setName("luca");
		user3.setSurname("mainetti");
		user3.setEmail("luca@gmail.com");
		user3.setPassword("luca");
		user3.setUsertype(new Usertype("professor", null));
		
		when(userServiceMock.getProfessorByNameTeaching("Software Engineering")).thenReturn(user3);
		
		mockMvc.perform(get("/user/getUsersByTeachingName/{nameTeaching}", "Software Engineering"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[0].name", is("riccardo")))
		.andExpect(jsonPath("$[0].surname", is("contino")))
		.andExpect(jsonPath("$[0].email", is("riccardo@gmail.com")))
		.andExpect(jsonPath("$[0].studycourse.name", is("Ingegneria dell'informazione")))
		.andExpect(jsonPath("$[1].name", is("andrea")))
		.andExpect(jsonPath("$[1].surname", is("della monaca")))
		.andExpect(jsonPath("$[1].email", is("andrea@libero.it")))
		.andExpect(jsonPath("$[1].studycourse.name", is("Ingegneria dell'informazione")))
		.andExpect(jsonPath("$[2].name", is("luca")))
		.andExpect(jsonPath("$[2].surname", is("mainetti")))
		.andExpect(jsonPath("$[2].email", is("luca@gmail.com")));
	
		verify(userServiceMock, times(1)).getUserEnrolledTeaching("Software Engineering");
		verify(userServiceMock, times(1)).getProfessorByNameTeaching("Software Engineering");
		verifyNoMoreInteractions(userServiceMock);
	}
	
	@Test
	public void setTokenTest() throws Exception {
		
		User user = new User();
		user.setName("luca");
		user.setSurname("mainetti");
		user.setEmail("luca@gmail.com");
		user.setPassword("luca");
		user.setUsertype(new Usertype("professor", null));
		
		when(userServiceMock.getById(1)).thenReturn(user);
		
		User usernew = new User();
		usernew.setName("luca");
		usernew.setSurname("mainetti");
		usernew.setEmail("luca@gmail.com");
		usernew.setPassword("luca");
		usernew.setUsertype(new Usertype("professor", null));
		usernew.setFcmtoken("prova");
		
		when(userServiceMock.saveUser(Mockito.any(User.class))).thenReturn(usernew);
		
		TokenFormModel tfm = new TokenFormModel();
		tfm.setIdUser(1);
		tfm.setToken("prova");
		
		mockMvc.perform(post("/user/setToken")
				.contentType(APPLICATION_JSON_UTF8)
				.content(new ObjectMapper().writeValueAsString(tfm)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is("luca")))
		.andExpect(jsonPath("$.surname", is("mainetti")))
		.andExpect(jsonPath("$.email", is("luca@gmail.com")))
		.andExpect(jsonPath("$.fcmtoken", is("prova")));
	
		
		ArgumentCaptor<User> uCaptor = ArgumentCaptor.forClass(User.class);

		verify(userServiceMock, times(1)).getById(1);
		verify(userServiceMock, times(1)).saveUser(uCaptor.capture());
		verifyNoMoreInteractions(userServiceMock);
		
	}
	
	@Test
	public void saveUserTest() throws  Exception {

		User user = new User();
		user.setName("luca");
		user.setSurname("mainetti");
		user.setEmail("luca@gmail.com");
		user.setPassword("luca");
		user.setUsertype(new Usertype("professor", null));
		user.setStudycourse(new Studycourse("Computer Engineering","test",null,null,null));
		
		
		when(userServiceMock.saveUser(Mockito.any(User.class))).thenReturn(user);
		
		System.out.println(new ObjectMapper().writeValueAsString(user));
        mockMvc.perform(
                post("/user/save")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(user))
        )
        .andExpect(status().isOk())
		.andExpect(jsonPath("$.name", is("luca")))
		.andExpect(jsonPath("$.surname", is("mainetti")))
		.andExpect(jsonPath("$.email", is("luca@gmail.com")))
		.andExpect(jsonPath("$.password", is("luca")));
	
		
		ArgumentCaptor<User> uCaptor = ArgumentCaptor.forClass(User.class);

		verify(userServiceMock, times(1)).saveUser(uCaptor.capture());
		verifyNoMoreInteractions(userServiceMock);
		
		
	}
	
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/templates/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

}
