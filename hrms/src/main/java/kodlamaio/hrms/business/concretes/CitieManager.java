package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.CitiesService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CitieDao;
import kodlamaio.hrms.entities.concretes.Citie;

@Service
public class CitieManager implements CitiesService{

	private CitieDao citiesDao;
	
	@Autowired
	public CitieManager(CitieDao citiesDao) {
		this.citiesDao = citiesDao;
	}
	
	@Override
	public DataResult<List<Citie>> getAll() {
		return new SuccessDataResult<List<Citie>>(this.citiesDao.findAll(), "Tum sehirler listelendi");
	}

	@Override
	public Result add(Citie cities) {
		this.citiesDao.save(cities);
		return new SuccessResult("Sehir eklendi");
	}

}
