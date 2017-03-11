package nl.guushamm.repository;

import nl.guushamm.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by guushamm on 22-2-17.
 */
@RepositoryRestResource(collectionResourceRel = "account", path = "account")
public interface AccountRepository extends JpaRepository<Account, Long> {

	List<Account> findByUsername(@Param("username") String username);
}
