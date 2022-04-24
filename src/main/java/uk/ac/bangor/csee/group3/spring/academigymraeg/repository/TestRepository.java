package uk.ac.bangor.csee.group3.spring.academigymraeg.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.Test;

public interface TestRepository extends JpaSpecificationExecutor<Test>, CrudRepository<Test, String> {

}
