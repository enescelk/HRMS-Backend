package kodlamaio.hrms.api.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.entities.concretes.Candidate;

@RestController
@RequestMapping("/api/candidates")
@CrossOrigin
public class CandidatesController {
	
	private CandidateService candidateService;
	
	public CandidatesController(CandidateService candidateService) {
		this.candidateService = candidateService;
	}
	
	@GetMapping("/getall")
	public ResponseEntity<?> getAll(){
		var result = this.candidateService.getAll();
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	};
	
	@GetMapping("/get")
	public ResponseEntity<?> get(int id){
		
		var result = this.candidateService.get(id);
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	};
	
	@PostMapping("/imageupload")
	public ResponseEntity<?> imageUpload(@RequestParam int candidateId, MultipartFile file){
		var result = candidateService.imageUpload(candidateId, file);
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		};
		
		return ResponseEntity.ok(result);
	};

	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Candidate candidate) {;
		
		var result = this.candidateService.add(candidate);
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	};
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete(Candidate candidate) {;
		
		var result = this.candidateService.delete(candidate);
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	};
	
	@PostMapping("/update")
	public ResponseEntity<?> update(Candidate candidate) {;
		
		var result = this.candidateService.update(candidate);
		
		if(!result.isSuccess()) {
			return ResponseEntity.badRequest().body(result);
		}
		
		return ResponseEntity.ok(result);
	};
	
	
}
