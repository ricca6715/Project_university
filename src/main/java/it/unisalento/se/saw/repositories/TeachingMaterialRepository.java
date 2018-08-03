package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Teachingmaterial;

@Repository
public interface TeachingMaterialRepository extends JpaRepository<Teachingmaterial, Integer> {
	
	@Query("select tm from Teachingmaterial tm "
			+ "where tm.lecture.idLecture=:idLecture")
	public List<Teachingmaterial> getTeachingMaterialByIdLecture(@Param("idLecture") int idLecture);
	
	@Query("select tm from Teachingmaterial tm where tm.idTeachingMaterial=:idTeachingmaterial")
	public Teachingmaterial getTeachingMaterialById(@Param("idTeachingmaterial") int idTeachingmaterial);

}
