package kodlamaio.hrms.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.EmailVerificationService;
import kodlamaio.hrms.core.utilities.results.GenerateRandomCode;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.dataAccess.abstracts.EmailVerificationDao;
import kodlamaio.hrms.entities.concretes.EmailVerification;

@Service
public class EmailVerificationManager implements EmailVerificationService{

	private EmailVerificationDao emailVerificationDao;
	
	@Autowired
	public EmailVerificationManager(EmailVerificationDao emailVerificationDao) {
		this.emailVerificationDao = emailVerificationDao;
	}

	@Override
	public void generateCode(EmailVerification code, Integer id) {
		EmailVerification code_ = code; 
		code.setCode(null);
		code.setEmailVerification(false);
		if(code.isEmailVerification() == false) {
			GenerateRandomCode generator = new GenerateRandomCode();		
			String code_create = generator.create();
			code.setCode(code_create);
			code.setUserId(id);
			
			emailVerificationDao.save(code);
			
		}
	}

	@Override
	public Result verify(String verificationCode, Integer id) {
		EmailVerification ref = emailVerificationDao.getOne(id);
		if(ref.getCode().equals(verificationCode)) {
			ref.setEmailVerification(true);
			return new SuccessDataResult<EmailVerification>(this.emailVerificationDao.save(ref),"Basarili");
		}
		
		return null;
	}

}
