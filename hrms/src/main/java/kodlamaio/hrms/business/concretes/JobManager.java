package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.entities.concretes.JobPosition;

@Service
public class JobManager implements JobService{

	private JobPositionDao jobDao;
	
	@Autowired
	public JobManager(JobPositionDao jobDao) {
		this.jobDao = jobDao;
	}

	@Override
	public List<JobPosition> getAll() {
		return this.jobDao.findAll();
	}

	@Override
	public DataResult<JobPosition> add(JobPosition jobPositions) {
		if(jobDao.findAllByPositionName(jobPositions.getPositionName()).stream().count() !=0) {
			return new ErrorDataResult<JobPosition>(null,"Boyler bir is pozisyonu kayitli");
		}
		return new SuccessDataResult<JobPosition>(this.jobDao.save(jobPositions),"Basariyla is pozisyonu eklendi");
	}
	
	
	
	
}