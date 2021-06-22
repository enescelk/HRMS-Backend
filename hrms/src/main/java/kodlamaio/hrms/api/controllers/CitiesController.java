package kodlamaio.hrms.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kodlamaio.hrms.business.abstracts.CitiesService;
import kodlamaio.hrms.entities.concretes.Citie;

@RestController
@RequestMapping("/api/citiess")
@CrossOrigin
public class CitiesController {
	
	private CitiesService citiesService;
	
	@Autowired
	public CitiesController(CitiesService citiesService) {
		this.citiesService = citiesService;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<?> getAll(){
		var result = this.citiesService.getAll();
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
		
	};
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Citie cities){
		var result = this.citiesService.add(cities);
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	}
}
