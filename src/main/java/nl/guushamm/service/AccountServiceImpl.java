package nl.guushamm.service;

import nl.guushamm.domain.Account;
import nl.guushamm.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by guushamm on 23-2-17.
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Override
	public List<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public void save(Account account) {
		accountRepository.save(account);
	}

	@Override
	public Account findOne(Long id) {
		return accountRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		accountRepository.delete(id);
	}

	@Override
	public List<Account> findByUsername(String username) {
		return accountRepository.findByUsername(username);
	}

	@Override
	public Account findOrSave(Account account) {
		List<Account> accounts  = findByUsername(account.getUsername());
		if (accounts.size() == 0) {
			save(account);
		} else {
			account = accounts.get(0);
		}
		return account;
	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}
}
