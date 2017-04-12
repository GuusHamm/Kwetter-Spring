package nl.guushamm.service;

import nl.guushamm.domain.Heart;

import java.util.List;

/**
 * Created by guushamm on 22-2-17.
 */
public interface HeartService {
	/**
	 * Finds all hearts in the datastore.
	 * @return List<Heart> List of all hearts in datastore.
	 */
	List<Heart> findAll();

	/**
	 * Saves an heart to the datastore.
	 * @param heart The Heart to be saved.
	 */
	void save(Heart heart);

	/**
	 * Finds a specific heart in the datastore.
	 *
	 * @param id Id of the heart that is desired.
	 * @return Heart The found heart.
	 */
	Heart findOne(Long id);

	/**
	 * Deletes an heart from the datastore.
	 *
	 * @param id Id of the to be deleted heart.
	 */
	void delete(Long id);
}
