package uk.ac.bangor.csee.group3.spring.academigymraeg.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Noun;

public interface NounRepository extends JpaSpecificationExecutor<Noun>, CrudRepository<Noun, String> {

}
