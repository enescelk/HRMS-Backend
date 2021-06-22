package kodlamaio.hrms.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import kodlamaio.hrms.entities.concretes.ForeignLangueage;

public interface ForeignLanguageDao extends JpaRepository<ForeignLangueage, Integer>{

}
