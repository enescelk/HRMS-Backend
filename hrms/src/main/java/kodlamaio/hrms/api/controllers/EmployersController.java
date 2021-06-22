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

import kodlamaio.hrms.business.abstracts.EmployerService;
import kodlamaio.hrms.entities.concretes.Employer;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/employers")
@NoArgsConstructor
@CrossOrigin
public class EmployersController {
	
	@Autowired
	private EmployerService employerService;
	
	
	public EmployersController(EmployerService employerService) {
		this.employerService = employerService;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?>  getAll(){
		var result = employerService.getAll();
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Employer employer){
		
		var result = this.employerService.add(employer);
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(@RequestBody Employer employer){
		var result = this.employerService.delete(employer);
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	}
}
