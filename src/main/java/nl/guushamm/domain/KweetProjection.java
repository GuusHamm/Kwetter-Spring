package nl.guushamm.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by guushamm on 30-3-17.
 */
@Projection(name = "details", types = Kweet.class)
public interface KweetProjection {

	String getMessage();

	String getTimestamp();

	Account getAccount();

	@Value("#{target.hearts.size()}")
	int getHearts();

	Trend[] getTrends();
}
