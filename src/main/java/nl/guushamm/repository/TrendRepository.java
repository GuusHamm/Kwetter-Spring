package nl.guushamm.repository;

import nl.guushamm.domain.Trend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

/**
 * Created by guushamm on 1-4-17.
 */
@RepositoryRestResource(collectionResourceRel = "trend", path = "trend")
public interface TrendRepository extends JpaRepository<Trend, Long>{

	@Query(value = "Select trend from Trend trend order by trend.kweets.size desc")
	public List<Trend> findByKweetCount();

	public Trend findByNameIgnoreCase(@Param("name") String name);
}
