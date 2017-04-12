package nl.guushamm.service;

import nl.guushamm.domain.Trend;

import java.util.List;

/**
 * Created by guushamm on 22-2-17.
 */
public interface TrendService {
	/**
	 * Finds all trends in the datastore.
	 * @return List<Trend> List of all trends in datastore.
	 */
	List<Trend> findAll();

	/**
	 * Saves an trend to the datastore.
	 * @param trend The Trend to be saved.
	 */
	void save(Trend trend);

	/**
	 * Finds a specific trend in the datastore.
	 *
	 * @param id Id of the trend that is desired.
	 * @return Trend The found trend.
	 */
	Trend findOne(Long id);

	/**
	 * Deletes an trend from the datastore.
	 *
	 * @param id Id of the to be deleted trend.
	 */
	void delete(Long id);

	List<Trend> findByKweetCount();

	Trend findOrCreateByName(String name);
}
