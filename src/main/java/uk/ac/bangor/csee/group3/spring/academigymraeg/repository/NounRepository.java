package uk.ac.bangor.csee.group3.spring.academigymraeg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Noun;
import uk.ac.bangor.csee.group3.spring.academigymraeg.model.User;

public interface NounRepository extends JpaSpecificationExecutor<Noun>, CrudRepository<Noun, String> {
	
	@Transactional
	@Modifying
	@Query("update Noun n set n.cyNoun = :cyNoun, n.enNoun = :enNoun, n.cyGender= :cyGender where n.id = :id")
	void updateNoun(@Param("cyNoun") String cyNoun, @Param("enNoun") String enNoun, @Param("cyGender") String cyGender,  @Param("id") String id);
	
	Optional<Noun> findByCyNoun(String cyNoun);
}
