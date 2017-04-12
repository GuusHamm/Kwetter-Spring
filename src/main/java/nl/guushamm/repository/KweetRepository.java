package nl.guushamm.repository;

import nl.guushamm.domain.Kweet;
import nl.guushamm.domain.KweetProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by guushamm on 22-2-17.
 */
@RepositoryRestResource(collectionResourceRel = "kweet", excerptProjection = KweetProjection.class, path = "kweet")
public interface KweetRepository extends JpaRepository<Kweet, Long> {

	@Override
	@PreAuthorize("#kweet.account.username == authentication.name or hasAuthority('moderator')")
	void delete(@Param("kweet") Kweet kweet);

	List<Kweet> findByMessageContaining(@Param("message") String message);

	List<Kweet> findByAccountUsernameIgnoreCase(@Param("username") String username);

	List<Kweet> findByTrendsNameIgnoreCaseOrderByIdDesc(@Param("trend") String trend);

	List<Kweet> findAllByOrderByIdDesc();
}
