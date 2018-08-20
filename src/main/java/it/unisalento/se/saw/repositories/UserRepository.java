package it.unisalento.se.saw.repositories;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Teaching;
import it.unisalento.se.saw.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select u from User u "
			+ "where u.email=:mail and u.password=:password")
	public User getUserByMail_Pwd(@Param("mail") String mail , @Param("password") String password);
	
	
	//Get student enrolled in teaching
	@Query("select u from User u JOIN  u.teachings t "
			+ "where t.name=:nameTeaching ")
	public List<User> getUserEnrolledTeaching(@Param("nameTeaching") String teaching);
	
	@Query("select t.user from Teaching t  "
			+ "where t.name=:nameTeaching ")
	public User getProfessorByNameTeaching(@Param("nameTeaching") String nameTeaching);

	@Query("select u from User u where u.email=:mail")
	public User userByMail(@Param("mail") String mail);
	
	@Query("select u from User u where u.usertype.idUserType=2")
	public List<User> getAllProfessors();
	
	
	

	
	
	
	
}


