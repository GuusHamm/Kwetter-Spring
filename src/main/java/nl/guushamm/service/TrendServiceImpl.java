package nl.guushamm.service;

import nl.guushamm.domain.Trend;
import nl.guushamm.repository.TrendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by guushamm on 23-2-17.
 */
@Service
@Transactional
public class TrendServiceImpl implements TrendService {
	@Autowired
	private TrendRepository trendRepository;

	@Override
	public List<Trend> findAll() {
		return trendRepository.findAll();
	}

	@Override
	public void save(Trend trend) {
		trendRepository.save(trend);
	}

	@Override
	public Trend findOne(Long id) {
		return trendRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		trendRepository.delete(id);
	}

	@Override
	public List<Trend> findByKweetCount() {
		return trendRepository.findByKweetCount();
	}

	@Override
	public Trend findOrCreateByName(String name) {
		Trend trend = trendRepository.findByNameIgnoreCase(name);
		if (trend == null){
			trend  = trendRepository.save(new Trend(name));
		}
		return trend;
	}
}
