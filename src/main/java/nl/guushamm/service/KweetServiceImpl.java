package nl.guushamm.service;

import nl.guushamm.domain.Kweet;
import nl.guushamm.handler.KweetHandler;
import nl.guushamm.repository.KweetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by guushamm on 23-2-17.
 */
@Service
@Transactional
public class KweetServiceImpl implements KweetService {
	@Autowired
	private KweetRepository kweetRepository;

	@Autowired
	private KweetHandler kweetHandler;

	@Autowired
	private AccountService accountService;

	@Override
	public List<Kweet> findAll() {
		return kweetRepository.findAll();
	}

	@Override
	public void save(Kweet kweet) {
		kweetHandler.handleKweetBeforeCreate(kweet);

		kweetRepository.save(kweet);
	}

	@Override
	public Kweet findOne(Long id) {
		return kweetRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		kweetRepository.delete(id);
	}

	@Override
	public List<Kweet> findByMessageContaining(String message) {
		return this.kweetRepository.findByMessageContaining(message);
	}

	@Override
	public void setKweetRepository(KweetRepository kweetRepository) {
		this.kweetRepository = kweetRepository;
	}

	@Override
	public List<Kweet> findByAccountUsername(String username) {
		return kweetRepository.findByAccountUsernameIgnoreCase(username);
	}

	@Override
	public List<Kweet> findAllByIdDesc() {
		return kweetRepository.findAllByOrderByIdDesc();
	}

	@Override
	public List<Kweet> findByTrendName(String trend) {
		return kweetRepository.findByTrendsNameIgnoreCaseOrderByIdDesc(trend);
	}
}
