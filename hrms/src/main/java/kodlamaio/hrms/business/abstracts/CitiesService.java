package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Citie;

public interface CitiesService {
	
	DataResult<List<Citie>> getAll();
	
	Result add(Citie cities);
	
}
