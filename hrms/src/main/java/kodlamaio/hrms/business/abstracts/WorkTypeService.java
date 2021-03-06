package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.WorkType;

public interface WorkTypeService {

	DataResult<List<WorkType>> getAll();
	
	Result add(WorkType workType);
	
	Result delete(WorkType workType);
	
}
