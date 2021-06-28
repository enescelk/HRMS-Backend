package kodlamaio.hrms.business.concretes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CitieDao;
import kodlamaio.hrms.dataAccess.abstracts.EmployerDao;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.dataAccess.abstracts.JobPositionDao;
import kodlamaio.hrms.dataAccess.abstracts.WorkTimeDao;
import kodlamaio.hrms.dataAccess.abstracts.WorkTypeDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDtos.JobAdvertisementAddDto;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDtos.JobAdvertisementDeleteDto;

@Service
public class JobAdvertisementManager implements JobAdvertisementService{

	private JobAdvertisementDao jobAdvertisementDao;
	private CitieDao cityDao;
	private EmployerDao employerDao;
	private WorkTypeDao workTypeDao;
	private WorkTimeDao workTimeDao;
	private JobPositionDao jobPositionDao;
	

	@Autowired
	public JobAdvertisementManager(JobAdvertisementDao jobAdvertisementDao, CitieDao cityDao, EmployerDao employerDao,
			WorkTypeDao workTypeDao, WorkTimeDao workTimeDao, JobPositionDao jobPositionDao) {
		super();
		this.jobAdvertisementDao = jobAdvertisementDao;
		this.cityDao = cityDao;
		this.employerDao = employerDao;
		this.workTypeDao = workTypeDao;
		this.workTimeDao = workTimeDao;
		this.jobPositionDao = jobPositionDao;
	}

	
	@Override
	public DataResult<List<JobAdvertisement>> getAll() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(), "Is ilanlari listelendi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllSortedByActive() {
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.getByIsActive(), "Aktiflige gore listelendi");
	}

	@Override
	public DataResult<List<JobAdvertisement>> getAllSortedByDate() {
		Sort sort = Sort.by(Sort.Direction.ASC,"applicationDeadline");
		return new SuccessDataResult<List<JobAdvertisement>>(this.jobAdvertisementDao.findAll(sort),"Tarihe gore listelendi");
	}

	
	@Override
	public Result add(JobAdvertisementAddDto jobAdvertisementAddDto) {
		JobAdvertisement jobAdvertisement = new JobAdvertisement();
		jobAdvertisement.setActive(false);
		jobAdvertisement.setApplicationDeadline(LocalDate.parse((jobAdvertisementAddDto.getApplicationDeadline().toString())));
		jobAdvertisement.setCity(this.cityDao.findById(jobAdvertisementAddDto.getCityId()).get());
		jobAdvertisement.setEmployer(this.employerDao.findById(jobAdvertisementAddDto.getEmployerId()).get());
		jobAdvertisement.setWorkTime(this.workTimeDao.findById(jobAdvertisementAddDto.getWorkTimeId()).get());
		jobAdvertisement.setWorkType(this.workTypeDao.findById(jobAdvertisementAddDto.getWorkTypeId()).get());
		jobAdvertisement.setJobPositions(this.jobPositionDao.findById(jobAdvertisementAddDto.getJobPositionId()).get());
		jobAdvertisement.setMinSalary(jobAdvertisementAddDto.getMinSalary());
		jobAdvertisement.setMaxSalary(jobAdvertisementAddDto.getMaxSalary());
		System.out.println(jobAdvertisementAddDto.getMinSalary());
		jobAdvertisement.setNumberOfOpenPosition(jobAdvertisementAddDto.getNumberOfOpenPosition());
		jobAdvertisement.setJobDescription(jobAdvertisementAddDto.getJobDescription());
		jobAdvertisement.setCreationDate(LocalDate.now());
		
		this.jobAdvertisementDao.save(jobAdvertisement);
		return new SuccessResult("Is ilani basariyla eklendi.");
	}

	/*
	 * @Override public Result update(JobAdvertisementAddDto jobAdvertisementAddDto)
	 * { this.jobAdvertisementDao.save(jobAdvertisementAddDto); return new
	 * SuccessResult("Is ilani basariyla guncellendi."); }
	 */

	@Override
	public Result delete(JobAdvertisementDeleteDto jobAdvertisementDeleteDto) {
		JobAdvertisement jobAdvertisement = new JobAdvertisement();
		jobAdvertisement.setId(jobAdvertisementDeleteDto.getId());
		this.jobAdvertisementDao.delete(jobAdvertisement);
		return new SuccessResult("Is ilani basariyla silindi.");
	}

}
