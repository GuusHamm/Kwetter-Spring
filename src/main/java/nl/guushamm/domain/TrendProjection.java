package nl.guushamm.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by guushamm on 1-4-17.
 */
@Projection(name = "details", types = Trend.class)
public interface TrendProjection {
	String getName();

	@Value("#{target.kweets.size()}")
	int getNumberOfKweets();
}
