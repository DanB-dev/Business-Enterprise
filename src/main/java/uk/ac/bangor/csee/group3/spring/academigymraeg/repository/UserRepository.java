package uk.ac.bangor.csee.group3.spring.academigymraeg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import uk.ac.bangor.csee.group3.spring.academigymraeg.model.User;


public interface UserRepository extends CrudRepository<User, String>, JpaSpecificationExecutor<User> {
	@Transactional
	@Modifying
	@Query("update User u set u.username= :username, u.admin = :admin, u.power= :power, u.user= :user where u.id = :id")
	void updateDetailsWithoutPassword(@Param("username") String username, @Param("admin") Boolean admin, @Param("power") Boolean power, @Param("user")Boolean user, @Param("id") String id);
	
	@Transactional
	@Modifying
	@Query("update User u set u.username= :username, u.admin = :admin, u.power= :power, u.user= :user,u.password= :password where u.id = :id")
	void updateDetailsWithPassword(@Param("username") String username, @Param("admin") Boolean admin, @Param("power") Boolean power, @Param("user")Boolean user, @Param("password") String password, @Param("id") String id);
	
	Optional<User> findByUsername(String username);
}
