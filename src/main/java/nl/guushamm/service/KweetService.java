package nl.guushamm.service;

import nl.guushamm.domain.Kweet;
import nl.guushamm.repository.KweetRepository;

import java.util.List;

/**
 * Created by guushamm on 22-2-17.
 */
public interface KweetService {
	/**
	 * Finds all kweets in the datastore.
	 *
	 * @return List<Kweet> List of all kweets in datastore.
	 */
	List<Kweet> findAll();

	/**
	 * Saves a kweet to the datastore.
	 *
	 * @param kweet The kweet that has to be saved.
	 */
	void save(Kweet kweet);

	/**
	 * Finds a specific kweet in the datastore.
	 *
	 * @param id Id of the kweet that is desired.
	 * @return Kweet The found kweet.
	 */
	Kweet findOne(Long id);

	/**
	 * Deletes a kweet from the datastore.
	 *
	 * @param id Id of the to be deleted kweet.
	 */
	void delete(Long id);

	/**
	 * Finds kweets based on message
	 * @param message
	 * @return
	 */
	List<Kweet> findByMessageContaining(String message);

	/**
	 * Finds kweets based on the username of the account that is associated with them.
	 *
	 * @param username Username of the account associated with the kweet.
	 * @return List<Kweet> Kweets by the specified username.
	 */
	List<Kweet> findByAccountUsername(String username);

	List<Kweet> findAllByIdDesc();

	/**
	 * Sets the kweetRepository to a specific kweetRepository. Should only be needed in testing.
	 *
	 * @param kweetRepository The kweetRepository to be set.
	 */
	void setKweetRepository(KweetRepository kweetRepository);

	List<Kweet> findByTrendName(String trend);
}
