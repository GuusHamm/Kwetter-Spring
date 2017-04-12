package nl.guushamm.repository;

import nl.guushamm.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by guushamm on 1-4-17.
 */
@RepositoryRestResource(collectionResourceRel = "heart", path = "heart")
public interface HeartRepository extends JpaRepository<Heart, Long> {
}
