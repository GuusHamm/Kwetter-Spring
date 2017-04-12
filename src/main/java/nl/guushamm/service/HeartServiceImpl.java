package nl.guushamm.service;

import nl.guushamm.domain.Heart;
import nl.guushamm.repository.HeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by guushamm on 23-2-17.
 */
@Service
@Transactional
public class HeartServiceImpl implements HeartService {
	@Autowired
	private HeartRepository heartRepository;

	@Override
	public List<Heart> findAll() {
		return heartRepository.findAll();
	}

	@Override
	public void save(Heart heart) {
		heartRepository.save(heart);
	}

	@Override
	public Heart findOne(Long id) {
		return heartRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		heartRepository.delete(id);
	}
}
