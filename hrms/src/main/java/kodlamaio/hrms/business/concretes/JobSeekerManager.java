package kodlamaio.hrms.business.concretes;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.business.abstracts.JobSeekersService;
import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.ErrorDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.nationalIdValidation;
import kodlamaio.hrms.dataAccess.abstracts.JobSeekerDao;
import kodlamaio.hrms.entities.concretes.EmailVerification;
import kodlamaio.hrms.entities.concretes.JobSeeker;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class JobSeekerManager implements JobSeekersService{

	private JobSeekerDao jobSeekersDao;
	private EmailVerificationService emailVerificationService;
	private UserService userService;
	
	@Autowired
	public JobSeekerManager(JobSeekerDao jobSeekersDao, EmailVerificationService emailVerificationService, UserService userservice) {
		this.jobSeekersDao = jobSeekersDao;
		this.emailVerificationService = emailVerificationService;
		this.userService = userservice;
	}
	
	@Override
	public DataResult<JobSeeker> add(JobSeeker jobSeekers) {
		if(!firstNameChecker(jobSeekers)) {
			return new ErrorDataResult<JobSeeker>(null,"Lutfen adinizi giriniz !");
		}
		else if(!lastNameChecker(jobSeekers)) {
			return new ErrorDataResult<JobSeeker>(null,"Lutfen soyadinizi giriniz !");
		}
		else if(!nationalIdValidation.isRealPerson(jobSeekers.getNationalId())){
			return new ErrorDataResult<JobSeeker>(null,"Kimlik dogrulanamadi !");
		}
		else if(jobSeekers.getNationalId().isBlank()) {
			return new ErrorDataResult<JobSeeker>(null,"Tc Kimlik Alani bos birakilamaz !");
		}
		else if(!birthDateChecker(jobSeekers)) {
			return new ErrorDataResult<JobSeeker>(null,"Lutfen dogum tarihinizi giriniz !");
		}
		else if(!emailNullChecker(jobSeekers)) {
			return new ErrorDataResult<JobSeeker>(null,"Lutfen email alanini giriniz !");
		}
		else if(!isRealEmail(jobSeekers)) {
			return new ErrorDataResult<JobSeeker>(null,"Email adresiniz yalnis !");
		}
		else if(!passwordNullChecker(jobSeekers)) {
			return new ErrorDataResult<JobSeeker>(null,"Lutfen sifrenizi giriniz !");
		}
		else if(jobSeekersDao.findAllByEmail(jobSeekers.getEmail()).stream().count() != 0) {
			return new ErrorDataResult<JobSeeker>(null,"Email Zaten Kayitli");
		}
		else if(jobSeekersDao.findAllByNationalId(jobSeekers.getNationalId()).stream().count() != 0) {
			return new ErrorDataResult<JobSeeker>(null,"Tc numarasi Zaten kayitli");
		}
		User savedUser = this.userService.add(jobSeekers);
		this.emailVerificationService.generateCode(new EmailVerification(),savedUser.getId());
		return new SuccessDataResult<JobSeeker>(this.jobSeekersDao.save(jobSeekers),"Is arayan Hesabi eklendi, Dogrulama kodu gonderildi:"+jobSeekers.getId());
	}

	@Override
	public List<JobSeeker> getAll() {
		return this.jobSeekersDao.findAll();
	}
	
	
	// Business Codes
	
	private boolean firstNameChecker(JobSeeker jobSeekers) {
		if(jobSeekers.getFirstName().isBlank() || jobSeekers.getFirstName().equals(null)) {
			return false;
		}
		return true;
	}
	
	private boolean lastNameChecker(JobSeeker jobSeekers) {
		if(jobSeekers.getLastName().isBlank() || jobSeekers.getLastName().equals(null)) {
			return false;
		}
		return true;
	}

	private boolean birthDateChecker(JobSeeker jobSeekers) {
		if(jobSeekers.getBirthOfDate().equals(null)) {
			return false;
		}
		return true;
	}

	private boolean isRealEmail(JobSeeker jobSeekers) {
		 String regex = "^(.+)@(.+)$";
	     Pattern pattern = Pattern.compile(regex);
	     Matcher matcher = pattern.matcher(jobSeekers.getEmail());
	     if(!matcher.matches()) {
	    	 return false;
	     }
	     return true;
	     
	}

	private boolean emailNullChecker(JobSeeker jobSeekers) {
		if(jobSeekers.getEmail().isBlank() || jobSeekers.getEmail().equals(null)) {
			return false;
		}
		return true;
	}

	private boolean passwordNullChecker(JobSeeker jobSeekers) {
		if(jobSeekers.getPassword().isBlank() || jobSeekers.getPassword().equals(null)) {
			return false;
		}
		return true;
	}
}
