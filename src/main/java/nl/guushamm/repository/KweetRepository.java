package nl.guushamm.repository;

import nl.guushamm.domain.Kweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by guushamm on 22-2-17.
 */
@RepositoryRestResource(collectionResourceRel = "kweet", path = "kweet")
public interface KweetRepository extends JpaRepository<Kweet, Long> {

	List<Kweet> findByAccountUsername(@Param("username") String username);
}
