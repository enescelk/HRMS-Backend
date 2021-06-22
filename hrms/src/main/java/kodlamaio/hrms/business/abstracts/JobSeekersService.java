package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.JobSeeker;
public interface JobSeekersService {
	DataResult<JobSeeker> add(JobSeeker jobSeekers);
	List<JobSeeker> getAll();
}
