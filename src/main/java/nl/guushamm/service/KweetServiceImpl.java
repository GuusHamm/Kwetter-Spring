package nl.guushamm.service;

import nl.guushamm.domain.Kweet;
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

	@Override
	public List<Kweet> findAll() {
		return kweetRepository.findAll();
	}

	@Override
	public void save(Kweet kweet) {
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
	public void setKweetRepository(KweetRepository kweetRepository) {
		this.kweetRepository = kweetRepository;
	}

	@Override
	public List<Kweet> findByAccountUsername(String username) {
		return kweetRepository.findByAccountUsername(username);
	}
}
