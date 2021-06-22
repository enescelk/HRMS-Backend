package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.WorkTimeService;
import kodlamaio.hrms.entities.concretes.WorkTime;

@RestController
@RequestMapping("/api/worktime")
@CrossOrigin
public class WorkTimesController {

	private WorkTimeService workTimeService;
	
	@Autowired
	public WorkTimesController(WorkTimeService workTimeService){
		this.workTimeService = workTimeService;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		var result = this.workTimeService.getAll();
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody WorkTime workTime){
		var result = this.workTimeService.add(workTime);
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody WorkTime workTime){
		var result = this.workTimeService.delete(workTime);
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	}
}
