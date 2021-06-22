package kodlamaio.hrms.business.abstracts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.entities.concretes.Candidate;

public interface CandidateService {
	
	DataResult<List<Candidate>> getAll();
	DataResult<Candidate> get(int id);
	DataResult<Candidate> imageUpload(int candidateId, MultipartFile file);
	
	Result add(Candidate candidate);
	Result delete(Candidate candidate);
	Result update(Candidate candidate);
}
