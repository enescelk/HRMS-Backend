package kodlamaio.hrms.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kodlamaio.hrms.business.abstracts.UserService;
import kodlamaio.hrms.core.entities.User;
import kodlamaio.hrms.dataAccess.abstracts.UserDao;
import lombok.NoArgsConstructor;

@Service
public class UserManager implements UserService{

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<User> getAll() {
		return userDao.findAll();
	}

	@Override
	public User add(User user) {
		return userDao.save(user);
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
