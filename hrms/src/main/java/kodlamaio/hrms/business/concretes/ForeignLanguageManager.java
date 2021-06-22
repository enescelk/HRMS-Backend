package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.ForeignLanguageService;
import kodlamaio.hrms.core.utilities.results.DataResult;
import kodlamaio.hrms.core.utilities.results.Result;
import kodlamaio.hrms.core.utilities.results.SuccessDataResult;
import kodlamaio.hrms.core.utilities.results.SuccessResult;
import kodlamaio.hrms.dataAccess.abstracts.ForeignLanguageDao;
import kodlamaio.hrms.entities.concretes.ForeignLangueage;

@Service
public class ForeignLanguageManager implements ForeignLanguageService {

	private ForeignLanguageDao foreignLanguageDao;
	
	@Autowired
	public ForeignLanguageManager(ForeignLanguageDao foreignLanguageDao) {
		this.foreignLanguageDao = foreignLanguageDao;
	}
	
	@Override
	public DataResult<List<ForeignLangueage>> getAll() {
		return new SuccessDataResult<List<ForeignLangueage>>(this.foreignLanguageDao.findAll(), "Data listelendi");
		}

	@Override
	public Result add(ForeignLangueage foreignLangueage) {
		this.foreignLanguageDao.save(foreignLangueage);
		return new SuccessResult("Yabanci dil eklendi");
	}

}
