package it.unisalento.se.saw.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.unisalento.se.saw.domain.Materialsatisfaction;

@Repository
public interface MaterialSatisfactionRepository extends JpaRepository<Materialsatisfaction, Integer> {
	
	@Query("select avg(ms.level) from Materialsatisfaction ms"
			+ " where ms.teachingmaterial.idTeachingMaterial=:idTeachingMaterial")
	public Double getAverageRatingByIdMaterial(@Param("idTeachingMaterial") int idTeachingMaterial );
	
	@Query("select ms from Materialsatisfaction ms" + 
			" where ms.teachingmaterial.idTeachingMaterial=:idTeachingMaterial")
	public List<Materialsatisfaction> getMaterialSatisfactionByIdMaterial(@Param("idTeachingMaterial") int idTeachingMaterial );

}
