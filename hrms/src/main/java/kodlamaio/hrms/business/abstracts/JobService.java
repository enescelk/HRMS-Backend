package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.JobPosition;

public interface JobService {
	List<JobPosition> getAll();
	DataResult<JobPosition> add(JobPosition jobPositions);
}
