package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.ForeignLangueage;

public interface ForeignLanguageService {
	
	DataResult<List<ForeignLangueage>> getAll();
	
	Result add(ForeignLangueage foreignLangueage);
}
