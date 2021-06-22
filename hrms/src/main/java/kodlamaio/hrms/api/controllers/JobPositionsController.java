package kodlamaio.hrms.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.JobService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.entities.concretes.JobPosition;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/jobPosition")
@NoArgsConstructor
@CrossOrigin
public class JobPositionsController {
	
	private JobService jobService;
	
	@Autowired
	public JobPositionsController(JobService jobService) {
		super();
		this.jobService = jobService;
	}
	
	@GetMapping("/getAll")
	public List<JobPosition> getAll(){
		return this.jobService.getAll();
	}
	
	@PostMapping("/add")
	public DataResult<JobPosition> add(@RequestBody JobPosition jobPositions){
		return this.jobService.add(jobPositions);
	}
}
