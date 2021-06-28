package kodlamaio.hrms.entities.concretes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "curriculum_vitaes")
public class CurriculumVitae  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "github_adress")
	private String githubAdress;
	
	@Column(name = "linkedin_address")
	private String linkedinAddress;
	
	@Column(name = "abilities")
	private String abilities;
	
	@Column(name = "cover_letter")
	private String coverLetter;
	
	@Column(name = "creation_date", updatable = false)
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date CreationDate;
	
	@OneToMany(mappedBy = "curriculumVitae")
	@JsonIgnore
	private List<JobExperience> jobExperiences;
	
	@OneToMany(mappedBy = "curriculumVitae")
	@JsonIgnore
	private List<School> schools;
	
	@OneToMany(mappedBy = "curriculumVitae")
	@JsonIgnore
	private List<ForeignLangueage> foreignLangueages;
	
	
	
}
