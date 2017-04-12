package nl.guushamm.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

/**
 * Created by guushamm on 30-3-17.
 */
@Projection(name = "details", types = Account.class)
public interface AccountProjection {
	String getUsername();

	String getFirstname();

	String getLastname();

	String getId();

	Kweet[] getKweets();

	@Value("#{target.kweets.size()}")
	int getKweetSize();

	@Value("#{target.kweets[target.kweets.size() -1]}")
	Kweet getLatestKweet();

	@Value("#{target.followers.size()}")
	int getFollowers();

	@Value("#{target.following.size()}")
	int getFollowing();
}
