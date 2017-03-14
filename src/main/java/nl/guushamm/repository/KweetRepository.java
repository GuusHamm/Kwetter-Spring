package nl.guushamm.repository;

import nl.guushamm.domain.Kweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

/**
 * Created by guushamm on 22-2-17.
 */
@RepositoryRestResource(collectionResourceRel = "kweet", path = "kweet")
public interface KweetRepository extends JpaRepository<Kweet, Long> {

	@Override
	@PreAuthorize("#kweet.account.username == authentication.name or hasAuthority('moderator')")
	Kweet save(@Param("kweet") Kweet kweet);

	@Override
	@PreAuthorize("#kweet.account.username == authentication.name or hasAuthority('moderator')")
	void delete(@Param("kweet") Kweet kweet);

	List<Kweet> findByAccountUsername(@Param("username") String username);
}
