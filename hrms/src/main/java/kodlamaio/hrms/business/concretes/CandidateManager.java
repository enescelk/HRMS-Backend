package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kodlamaio.hrms.business.abstracts.CandidateService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.business.abstracts.ImageService;
import kodlamaio.hrms.core.utilities.helpers.BusinessRule;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.ErrorResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.CandidateDao;
import kodlamaio.hrms.entities.concretes.Candidate;

@Service
public class CandidateManager implements CandidateService{

	private CandidateDao candidateDao;
	private UserService userService;
	private ImageService imageService;
	
	@Autowired
	public CandidateManager(CandidateDao candidateDao, UserService userService, ImageService imageService) {
		this.candidateDao = candidateDao;
		this.userService = userService;
		this.imageService = imageService;
	}
	
	@Override
	public DataResult<List<Candidate>> getAll() {
		return new SuccessDataResult<List<Candidate>>(candidateDao.findAll(), "Data Listelendi");
	}

	@Override
	public DataResult<Candidate> get(int id) {
		return new SuccessDataResult<Candidate>(candidateDao.findById(id).get(), "Data getirildi");
	}

	@Override
	public DataResult<Candidate> imageUpload(int candidateId, MultipartFile file) {
		var candidate = this.candidateDao.getById(candidateId);
		var result = BusinessRule.run(uploadImageToCloudinary(file), checkCandidateExists(candidate));
		
		if(!result.isSuccess()) {
			return new ErrorDataResult<Candidate>(null, result.getMessage());
		}
		return null;
	}

	@Override
	public Result add(Candidate candidate) {
		var isValid = BusinessRule.run(
				checkIfCandidateValid(candidate), checkIfEmailExists(candidate.getEmail()), checkIfNationalIdentityExists(candidate.getNationalIdentity())
		);
		
		if(!isValid.isSuccess()) {
			return new ErrorDataResult<>(null, isValid.getMessage());
		}
				
		
		
		this.candidateDao.save(candidate);
		return new SuccessResult("Aday eklendi");
	}

	@Override
	public Result delete(Candidate candidate) {
		this.candidateDao.delete(candidate);
		return new SuccessResult("Aday silindi.");
	}

	@Override
	public Result update(Candidate candidate) {
		this.candidateDao.save(candidate);
		return new SuccessResult("Aday bilgileri guncellendi");
	}
	
	
	// BUSINESS RULES
	
    private  Result uploadImageToCloudinary( MultipartFile file){
        var result = this.imageService.save(file);
        return new SuccessResult();
    }
    
    private Result checkCandidateExists(Candidate candidate){
        if (candidate == null){
            return new ErrorResult("Candidate doesn't exists");
        }
        return  new SuccessResult();
    }
	
    private Result checkIfCandidateValid(Candidate candidate){
        if(candidate.getFirstName() == null || candidate.getFirstName().isBlank()
            || candidate.getLastName() == null || candidate.getLastName().isBlank() ||
                candidate.getNationalIdentity() == null ||candidate.getNationalIdentity().isBlank() ||
                candidate.getDateOfBirth() == null || candidate.getEmail() ==null ||candidate.getEmail().isBlank() ||
                candidate.getPassword() == null ||candidate.getPassword().isBlank()
        ){
            return new ErrorResult("All fields are required");
        }
        return new SuccessResult();
    }
    
    private Result checkIfEmailExists(String email){
        var user = this.userService.getByEmail(email);
        if(user != null){
            return new ErrorResult("Email is already exists");
        }
        return new SuccessResult();
    }
    
    private Result checkIfNationalIdentityExists(String nationalIdentity){
        var user = this.candidateDao.getByNationalIdentity(nationalIdentity);
        if(user != null){
            return new ErrorResult("National identity is already exists");
        }
        return new SuccessResult();
    }

}
