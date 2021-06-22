package kodlamaio.hrms.business.abstracts;

import java.util.List;

import kodlamaio.hrms.core.entities.User;

public interface UserService {
	List<User> getAll();
	User add(User user);
	
	User getByEmail(String email);
}
