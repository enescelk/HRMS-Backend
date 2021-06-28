package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobAdvertisementService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.JobAdvertisementDao;
import kodlamaio.hrms.entities.concretes.JobAdvertisement;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDtos.JobAdvertisementAddDto;
import kodlamaio.hrms.entities.dtos.JobAdvertisementDtos.JobAdvertisementDeleteDto;

@RestController
@RequestMapping("/api/jobadvertisements")
@CrossOrigin
public class JobAdvertisementsController {
	
	private JobAdvertisementService jobAdvertisementService;
	private JobAdvertisementDao jobAdvertisementDao;
	
	@Autowired
	public JobAdvertisementsController(JobAdvertisementService jobAdvertisementService, JobAdvertisementDao jobAdvertisementDao) {
		this.jobAdvertisementService = jobAdvertisementService;
		this.jobAdvertisementDao = jobAdvertisementDao;
	}
	
	@GetMapping("/getall")
	public DataResult<List<JobAdvertisement>> getAll(){
		return this.jobAdvertisementService.getAll();
		
	};

	@GetMapping("/getallbyactive")
	public DataResult<List<JobAdvertisement>> getAllSortedByActive(){;
		return this.jobAdvertisementService.getAllSortedByActive();
	}
	
	@GetMapping("/getallbyactivefalse")
	public DataResult<List<JobAdvertisement>> getAllSortedByActiveFalse(){;
		return this.jobAdvertisementService.getAllSortedByActiveFalse();
	}
	
	@GetMapping("/getallbydate")
	public DataResult<List<JobAdvertisement>> getAllSortedByDate(){
		return this.jobAdvertisementService.getAllSortedByDate();
	};
	
	@PostMapping("/add")
	public Result add(@RequestBody JobAdvertisementAddDto jobAdvertisementAddDto) {
		return this.jobAdvertisementService.add(jobAdvertisementAddDto);
	};
	
	/*
	 * @PostMapping("/update") public Result update(@RequestBody JobAdvertisement
	 * jobAdvertisement) { return
	 * this.jobAdvertisementService.update(jobAdvertisement); };
	 */
	
	@PostMapping("/jobAdvertisementAcitvete")
	public Result activate(@RequestParam int activeteId) {
		JobAdvertisement jobAdvertisement = this.jobAdvertisementDao.findById(activeteId).get();
		jobAdvertisement.setActive(true);
		
		this.jobAdvertisementDao.save(jobAdvertisement);
		
		return new SuccessResult("Is ilani basa");
	};
	
	@DeleteMapping("/delete")
	public Result delete(@RequestBody JobAdvertisementDeleteDto jobAdvertisementDeleteDto) {
		return this.jobAdvertisementService.delete(jobAdvertisementDeleteDto);
	};
	
}