package nl.guushamm.service;

import nl.guushamm.domain.Account;
import nl.guushamm.repository.AccountRepository;

import java.util.List;

/**
 * Created by guushamm on 22-2-17.
 */
public interface AccountService {
	/**
	 * Finds all accounts in the datastore.
	 * @return List<Account> List of all accounts in datastore.
	 */
	List<Account> findAll();

	/**
	 * Saves an account to the datastore.
	 * @param account The Account to be saved.
	 */
	void save(Account account);

	/**
	 * Finds a specific account in the datastore.
	 *
	 * @param id Id of the account that is desired.
	 * @return Account The found account.
	 */
	Account findOne(Long id);

	/**
	 * Deletes an account from the datastore.
	 *
	 * @param id Id of the to be deleted account.
	 */
	void delete(Long id);

	/**
	 * Finds accounts based on the username
	 *
	 * @param username Username of the account associated with the account.
	 * @return List<Account> Accounts by the specified username.
	 */
	List<Account> findByUsername(String username);

	Account findOrSave(Account account);

	/**
	 * Sets the accountRepository to a specific accountRepository. Should only be needed in testing.
	 *
	 * @param accountRepository The accountRepository to be set.
	 */
	void setAccountRepository(AccountRepository accountRepository);
}
